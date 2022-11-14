package com.ssafy.metroverse.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.metroverse.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	@Modifying
	@Query("update User u set u.refreshToken=null where u.refreshToken=:refreshToken")
	void deleteRefreshToken(@Param("refreshToken") String refreshToken);
}
