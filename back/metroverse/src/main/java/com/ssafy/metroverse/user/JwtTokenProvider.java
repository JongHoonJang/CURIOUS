package com.ssafy.metroverse.user;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ssafy.metroverse.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	@Value("Curious107!")
	private String secretKey;
	private final long tokenValidTime = 30 * 1000L; // access 토큰 유효시간 30분
	private final long refreshTokenVaildTime = 7 * 24 * 60 * 60 * 1000L; // refresh 토큰 유효시간 7일
	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	// 객체 초기화, secretKey를 Base64로 인코딩
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	/**
	 * JWT 토큰 생성
	 * @param email 사용자 이메일
	 * @return JWT 토큰
	 */
	public String createToken(String email) {
		// JWT payload에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣음
		Claims claims = Jwts.claims().setSubject(email);
		Date now = new Date();
		return Jwts.builder()
			.setClaims(claims) // 정보 저장
			.setIssuedAt(now) // 토큰 발행 시간 정보
			.setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
			.signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature에 들어갈 secret 값 세팅
			.compact();
	}

	public String createRefreshToken() {
		Date now = new Date();
		return Jwts.builder()
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + refreshTokenVaildTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	/**
	 * JWT 토큰에서 인증 정보 조회
	 * @param token JWT 토큰
	 * @return 인증 정보
	 */
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userService.loadUserByUsername(this.getUserId(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	/**
	 * 토큰에서 회원 정보 추출
	 * @param token JWT 토큰
	 * @return 회원 정보
	 */
	public String getUserId(String token) throws ExpiredJwtException {
		try {
			logger.info(token);
			// logger.info(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
			return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.substring(7)).getBody().getSubject();
		} catch (ExpiredJwtException e) {
			return e.getClaims().getSubject();
		}

	}

	/**
	 * Request의 Header에서 token 값을 가져온다
	 * @param request
	 * @return "Authorization" : "TOKEN값"
	 */
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	/**
	 * 토큰의 유효성 + 만료일자 확인
	 * @param jwtToken JWT 토큰
	 * @return 유효성 여부
	 */
	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
