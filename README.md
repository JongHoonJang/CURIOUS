# 🚈Curi@us
Can U Respond In @ Subway emergency?<br>
지하철 내에서 언제 일어날지 모르는 재난 상황을 대비한 상황 체험 및 안전 교육 서비스

![Curious_logo](https://user-images.githubusercontent.com/65770658/202391897-df4806d1-225a-4f35-89ff-930b264fa702.png)

<br>

## 🚀프로젝트 소개

### 📆진행 일정

22.10.10(월) ~ 22.11.18(금) (총 6주)

#### 📅WBS

![WBS](https://user-images.githubusercontent.com/65770658/202602111-b874b84b-66ea-45b7-bda7-fc04a41ad988.jpg)

- Sub pjt1 - 기획 : 프로젝트 아이디어 도출, 주제 구체화, 기능 요구 명세서 작성
- Sub pjt2 - 설계 : 와이어프레임, 프로젝트 구조, 시나리오 구체화, 사용 기술 스택 선정
- Sub pjt3 - 개발 : 기능 구현, 배포, 통합 테스트, UCC, 발표

<br>

### 🤝팀

| 🦘거루<br>(홍주성)                                                                                                | ‍🐱‍🏍히로<br>(김영웅)                                                                                          | 🐸페페<br>(송예림)                                                                                                    | 🦥지크<br>(장종훈)                                                                                            | 🐣뀨<br>(조성규)                                                                                                | 🦅MK<br>(최명권)                                                                                                   |
|--------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| ![거루](https://user-images.githubusercontent.com/65770658/202838499-2ad4e604-be42-4acc-b222-d15e9f0674bc.png) | ![히로](https://user-images.githubusercontent.com/65770658/202628011-c4c202a8-0922-4dcb-a04a-c7a282faf977.png) | ![페페 (2)](https://user-images.githubusercontent.com/65770658/202838938-516c9e1e-c8b0-4ab8-9752-cada9ccf1c78.PNG) | ![지크](https://user-images.githubusercontent.com/65770658/202617102-3ba2aed2-d13a-4b2d-a946-fcae1d1064dd.png) | ![뀨](https://user-images.githubusercontent.com/65770658/202630672-82b8db7c-28d7-404a-99e3-c372491713b2.png) | ![mk](https://user-images.githubusercontent.com/65770658/202616843-59c3a18c-fd56-4ec5-a8b1-372f603b5d7a.png) |
| 팀장,<br>백엔드,<br>프로젝트 관리,<br>유니티 서버                                                                            | 부팀장,<br>배포,<br>메타버스 door movement,<br>상호작용                                                                 | 팀원,<br>백엔드,<br>REST API,<br>3D 모델링,<br>메타버스 대기맵                                                                  | 팀원,<br>프론트엔드 테크리더,<br>회원관리,<br>메타버스 UI,Fire                                                              | 팀원,<br>프론트엔드,<br>메타버스(particle, UI, highlight, 소화기, collider 충돌처리, 상호작용)                                    | 팀원,<br>프론트엔드,<br>디자인,<br>UCC,<br>3D 모델링                                                                         |

<br>

### 🏁목표

- 프로젝트 일정을 Jira를 이용해 체계적으로 관리하여 주어진 업무를 효율적으로 마무리하는 것을 제일의 목표로 합니다!
- 팀원 모두 역할을 균등 분배하여 메타버스 환경을 구축하고자 합니다!

<br>

### 🤔기획 의도

우리 일상 속에서 많은 위기 상황이 일어날 수 있습니다. 화재 대비 소방 훈련은 속한 조직에서 받고 있지만, 다른 상황을 대비한 훈련은 미비할 수 밖에 없습니다.
이를 메타버스를 활용한 체험을 통해 비상 상황에서 대처법을 기르고자 주제로 선정했습니다.

#### 🎯서비스 대상

유치원, 학교 등의 교육기관 및 회사 등의 단체 조직

#### 🎨 UI/UX

서비스 대상이 다양한 나이대이기때문에 누구나 쉽게 사용할 수 있고, 안전 교육 서비스인만큼 간단하고 단순한 UI/UX를 구성하였음

#### ⚙ Technical

##### 🕹유니티

지하철 맵 구성, 비상상황 시 필요한 오브젝트 구현, 상호작용 모션 구현

##### 🖼프론트엔드

카카오 / 네이버 / 구글 소셜로그인, 유니티 배포를 위한 webgl 사용

##### 📡백엔드

카카오 / 네이버 / 구글 소셜로그인, 회원관리 API


<br>

## 🛠기술 스택

![기술스택](https://user-images.githubusercontent.com/65770658/202637299-34483d66-9a79-4dce-be2e-76e6a7404038.png)

- 세부내역
>구분|기술스택|상세내용|버전
>:--|:--|:--|:--
>공통|형상관리|GitLab|-
>&nbsp;|&nbsp;|PlasticSCM|-
>&nbsp;|이슈관리|Jira|-
>&nbsp;|커뮤니케이션|Mattermost|-
>&nbsp;|&nbsp;|Notion|-
>FrontEnd|HTML5|
>&nbsp;|CSS3|
>&nbsp;|JavaScript(ES6)|
>&nbsp;|Vue|Vue|3.2.13
> &nbsp;|pinia|
>&nbsp;|IDE|Visual Studio Code|1.70.1
>BackEnd|Java|OpenJDK|11
>&nbsp;|Build|Gradle|7.5
>&nbsp;|Spring|Boot|2.7.4
>&nbsp;|&nbsp;|Security|2.7.4
> &nbsp;|&nbsp;|OAuth2|2.7.4
>&nbsp;|API Docs|Swagger2|2.9.2
>&nbsp;|DB|Mysql|8.0.30
>&nbsp;|&nbsp;|Spring-Data-jpa|2.1.10
>&nbsp;|IDE|IntelliJ|22.1.3
>Server|AWS EC2|GNU/Linux|5.4.0-1018
>&nbsp;|배포|Docker|20.10.17
>&nbsp;|&nbsp;|Jenkins|2.346.2
>&nbsp;|WebServer|Nginx|1.18.0

### 📬배포 방법
>- [[링크 참조](/exec/1_2_TechStack.md)]

<br>

## 💼기획/설계

### 📑기능 요구 명세서

페이지별로 기능을 구체화하여 유저 스토리를 토대로 기능을 작성하고 기능 세부사항과 조건, 특이사항, 구현 우선순위를 지정하였음

![페이지별 기능 구체화](https://user-images.githubusercontent.com/65770658/202640859-11cb2081-6e1f-47a6-848f-dbddc1f3e1cb.PNG)

<br>

### 🧩와이어프레임

피그마를 활용하여 작성함

처음 와이어프레임 기획단계에서는 웹 화면으로 구상하였으나, 피드백을 받고 모바일 화면으로 전환함



<br>

### 🎞시나리오 구체화

여러 회의를 거쳐가며 비상상황 시나리오와 오브젝트를 구체화함

![시나리오구체화](https://user-images.githubusercontent.com/65770658/202644972-c0185c94-e366-4049-9fa4-f0220eabccad.jpg)

<br>

### 🧾에셋 리스트

메타버스 환경 구현에 필요한 에셋 리스트들을 작성한 후 회의를 통해 검토함

![에셋리스트](https://user-images.githubusercontent.com/65770658/202645827-1f0c1872-3ceb-4570-9b31-430802d1ec3e.PNG)

## 🛰프로젝트 관리/운영

### 🗂프로젝트 디렉토리

| 🖼프론트엔드                                                  | 📡백엔드                                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![front디렉토리](https://user-images.githubusercontent.com/65770658/202648743-c8da3d6b-8c3f-4b71-94c0-41f9330ea3f7.PNG) | ![back디렉토리](https://user-images.githubusercontent.com/65770658/202648740-2211d3d8-2cde-45a2-9c26-59bc9fcb5c2d.PNG) |

<br>

### 📢Jira

![burndown](https://user-images.githubusercontent.com/65770658/202650329-0734190b-869d-41de-bcdf-522d19fc074a.PNG)

<br>

### 🧾Notion

![notion](https://user-images.githubusercontent.com/65770658/202651568-223210c6-7e46-4b17-813c-543c40476143.jpg)

<br>

### 🙋‍♀️데일리 스크럼

![daily1](https://user-images.githubusercontent.com/65770658/202653381-99cd8ca4-cbb8-43e9-b858-c65e91b80f7d.PNG)

![daily](https://user-images.githubusercontent.com/65770658/202653377-3e26b95b-055d-46a4-bbe7-114c85da617e.jpg)

<br>

### 🔍코드 리뷰

![코드리뷰](https://user-images.githubusercontent.com/65770658/202653701-a96a5032-c114-4fde-a87d-df222f08fe68.PNG)

<br>

## 📱서비스 구현 내용

### 🕹주요 기능

#### 🎡 메타버스 환경



<br>

#### ⛑ 안전교육 학습



<br>

#### 🖼 오브젝트 구현



<br>