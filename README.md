<h2><img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/477d5789-f58e-41ef-ad66-854f22bc8575" width="20"/> 반려동물 동반 가능 시설을 쉽게 찾을 수 있는 웹사이트 포맵</h2>

목차
----
1. 프로젝트 소개
2. 목표
3. 담당 역할
4. 기술 스택
5. 프로젝트 구조
6. 주요 기능
7. 데모 영상
8. 회고

## 1. 프로젝트 소개

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/93bb4aaf-c721-46ef-8c79-610a7b6e03c5" width="800"/><p>

> 우리나라의 반려가구 수 2022년 기준 552만 가구 (출처: KB경영연구소)

반려동물을 키우는 사람들은 방문하고자 하는 장소에 반려동물 동반이 가능한지 일일이 찾고 알아봐야 함.<p><p>

공공데이터와 사용자의 위치 정보를 활용, 사용자가 다양한 검색 옵션으로 반려동물 동반 가능 시설을 쉽게 찾을 수 있도록 해주는 웹사이트를 개발하고자 함.<p><p>

사이트 이름인 포맵은 paw(동물의 발) + map(지도)를 합쳐 만들었음.

## 2. 목표

- #### Back-end
 
    Spring 프레임워크 학습<br>
    Spring Boot Back-end 프로젝트 구조 이해 및 구현<br>
    REST API 설계<br>
    Oracle DB 설계<br>
    Spring Security 동작 원리 이해 및 구현<br>
    JWT 인증과 인가 구현<br>
    YAML을 사용한 프로젝트 설정<br>
    Gradle을 사용한 프로젝트 빌드<br>
    회원가입 시 이메일 인증 기능 구현

- #### Front-end

    HTML, CSS, JavaScript 학습<br>
    Vue.js를 통한 Front-end 프레임워크 이해 및 화면 구현<br>
    HTTP 요청 메서드 활용<br>
    Bootstrap을 활용한 반응형 웹 구현<br>
    Kakao 지도 API 활용

## 3. 담당 역할

- Spring Boot 프로젝트 구조 설계<br>
- Spring Security 구현<br>
- JWT 인증과 인가 구현<br>
- REST API 설계<br>
- Service 계층 로직 구현<br>
- DB 설계<br>
- Front-end 화면 디자인<br>
- Front-end UI 및 동작 구현

## 4. 기술 스택

- #### Back-end

    Java, Spring Boot, Spring Security, JPA, Oracle, Gradle

- #### Front-end

    HTML, CSS, JavaScript, Vue.js, Bootstrap

- #### Version Control

    Git, Github

## 5. 프로젝트 구조

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/d326d64f-0066-42a4-a82a-e4677615c5f6" width="640"/><p>

> 프로젝트 구성도

### Gradle
Gradle은 Maven과 비교하여 빌드 속도가 빠르고 동적이며 설정 주입 방식으로 재사용에 용이하고 Groovy 언어를 사용하여 구조적이고 가독성이 좋음. Gradle를 사용하여 프로젝트를 빌드 하였고 필요한 라이브러리와 의존성을 관리하였음.

### YAML
YAML 타입은 계층 구조로 가독성이 좋은 장점이 있어 application.yml 파일로 PORT 설정, Oracle DB 연동 설정, SMTP 관련 설정을 하였음.

### Spring Boot
Spring 프레임워크는 Java에 기반하고 있으며 Spring Security를 통해 보안 설정, 인증과 인가, JWT를 쉽게 처리할 수 있도록 해줌. Spring 프레임워크 설정을 편리하게 할 수 있는 Spring Boot로 Back-end 서버를 개발하였음.

### Oracle
공공데이터포털의 한국문화정보원 전국 반려동물 동반 가능 문화시설 위치 데이터를 활용하였으며 View 단의 요구에 맞는 데이터만을 효율적으로 보내기 위해 API가 아닌 CSV 파일을 받아 수정 후 임포트 하여 DB를 구성하였음.

### Vue.js
Vue.js 프레임워크는 사이즈가 가볍고 JavaScript 기반으로 문법이 간단하고 직관적임. Vue.js 프레임워크로 Front-end를 개발하였음.

### Bootstrap
Bootstrap을 활용해 Front-end 화면을 반응형 웹으로 개발하였음.

### Kakao 지도 API
반려동물 동반 가능 시설을 지도에 표시하기 위해 지도 API를 활용하였음. 구글 Maps API는 대한민국 지도를 표시할 때 정확도가 떨어지는 문제가 있었고 네이버 지도 API는 Kakao 지도 API에 비해 활용 관련 정보를 구하기 어려웠기 때문에 Kakao 지도 API를 활용하였음.

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/62e327d2-c078-4ff3-8ce2-3c462169da7d" width="800"/><p>

> ERD

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/968c6ed4-f04d-4f72-b63d-2d21d371faab" width="800"/><p>

> 지도 페이지 관련 REST API

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/bdba7202-4bc6-4dec-8c84-e3b1ea7dc459" width="800"/><p>

> 게시판 페이지 관련 REST API

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/713f7f8a-4638-4ddc-84cd-fd180f216ba2" width="800"/><p>

> 인증과 인가 관련 REST API

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/246702ae-a537-48c0-97d1-056384846311" width="800"/><p>

> 회원 관련 REST API

## 6. 주요 기능

- #### 메인 페이지

    동 이름 검색 시 지도 페이지로 이동하여 목록과 지도에서 해당 시설 확인<br>
    카테고리 버튼 클릭 시 지도 페이지로 이동하여 목록과 지도에서 해당 시설 확인

- #### 지도 페이지

    동 이름 검색 시 목록과 지도에서 해당 시설 확인<br>
    카테고리 선택 시 목록과 지도에서 해당 시설을 현재 접속 위치 기준 가까운 순으로 확인<br>
    Select 메뉴를 통해 카테고리/시도/시군구/읍면동을 조합하여 검색하고 해당 시설을 목록과 지도로 확인<br>
    지도의 마커를 클릭하거나 상세보기 버튼을 클릭하여 해당 시설의 상세정보 및 반려동물 관련 사항을 확인<br>
    북마크 버튼을 클릭하여 해당 시설을 북마크에 추가 (로그인 필요)

- #### 게시판 페이지

    게시글 및 댓글 작성/조회/수정/삭제<br>
    제목+내용, 제목, 내용, 닉네임 중 선택하여 게시글 검색

- #### 로그인 페이지

    로그인 (아이디 저장 기능)

- #### 회원가입 페이지

    회원가입 (입력 데이터 유효성 체크, 이메일 인증)

- #### 마이페이지의 북마크 탭

    회원이 추가한 반려동물 동반 가능 시설 북마크 조회<br>
    체크박스를 통해 한번에 여러 북마크 삭제

- #### 마이페이지의 내 게시글 탭

    회원 본인의 게시글 모두 조회<br>
    제목+내용, 제목, 내용, 닉네임 중 선택하여 게시글 검색<br>
    체크박스를 통해 한번에 여러 게시글 삭제

- #### 마이페이지의 회원정보 탭

    회원 본인의 아이디, 닉네임, 이메일 확인<br>
    비밀번호 변경

- #### 회원 탈퇴 페이지

    회원 탈퇴 (비밀번호 입력)

- #### 관리페이지의 게시글 관리 탭

    게시판 전체 게시글 조회/검색/삭제 (관리자 권한 계정)

- #### 관리페이지의 회원 관리 탭

    전체 회원 정보 조회/검색 (관리자 권한 계정)<br>
    회원 차단 (관리자 권한 계정)

## 7. 데모 영상

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/e5b18f7c-08ca-48b7-a312-0de953180e61"/><p>

> 동 이름 검색

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/dfe5465b-6a26-4611-839a-0f5b27c5f139"/><p>

> 카테고리 버튼 클릭

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/faaa924f-980f-46ec-bb0f-539d584ef68b"/><p>

>  회원가입

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/eee76c66-dff8-42b2-a80e-227f60bb7ab9"/><p>

> 로그인

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/7250c8f9-7cab-4da3-8eaa-6acc64b33c1c"/><p>

> 지도 페이지

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/62b34470-a58d-4f87-b6dc-9b6e81aa378d"/><p>

> 게시글 및 댓글 조회

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/c27edd3c-b77f-4046-93ef-40871c0944da"/><p>

> 게시글 및 댓글 작성/수정/삭제

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/211ed7f8-6ce1-4701-b062-77320e8100a1"/><p>

> 마이페이지의 북마크 탭

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/7e080ec9-689b-418d-8c34-deab4b13fb77"/><p>

> 마이페이지의 내 게시글 탭

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/817339c2-f40c-4269-bd52-4642698a8d1a"/><p>

> 마이페이지의 회원정보 탭

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/39516589-5703-400f-ac43-f02c024aee75"/><p>

> 회원 탈퇴

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/6e66ee30-ff93-4e6b-93cf-cf15cb72d77e"/><p>

> 관리페이지의 게시글 관리 탭

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/880bb5d6-381f-4093-80d6-85c1e4fda664"/><p>

> 관리페이지의 회원 관리 탭

---

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/2639eb7b-6223-4d4a-b52e-d2d65e3df1bd"/><p>

> 부트스트랩을 활용한 반응형 웹 디자인

## 8. 회고

### Back-end

#### Spring Boot
Spring Boot 프로젝트는 Controller, Service, DAO, Repository의 계층 구조로 구성하였으며 Service, DAO, Repository는 인터페이스로 구현하였고 Annotation을 사용하여 DI 하였음. View 단과 리소스를 주고받을 때는 DTO 클래스를 사용하였으며 직접 DB의 데이터를 처리할 때는 Entity 클래스와 JpaRepository 인터페이스를 사용하였음.

#### Spring Security
Spring Security 설정 클래스를 생성하고 HTTP 요청 메서드에 대한 권한 설정 및 JWT 필터를 추가한 SecurityFilterChain 인스턴스를 Bean으로 등록하였음. 또한 CORS 관련 설정을 특정 URL에 적용하는 UrlBasedCorsConfigurationSource 인스턴스, 회원 비밀번호 암호화 및 일치 여부를 확인하는 PasswordEncoder 인스턴스, 인증과 인가를 처리할 AuthenticationManager 인스턴스와 CustomAuthenticationProvider 인스턴스를 모두 Bean으로 등록하였음.

#### JWT
JwtTokenProvider 클래스를 생성하여 Access Token 생성 메서드, Refresh Token 생성 메서드, JWT 유효성 검사 메서드, JWT 인증을 완료한 Authentication 인스턴스를 반환하는 메서드를 구현하였음.<p><p>

#### 인증과 인가
JwtAuthenticationFilter 클래스는 JwtTokenProvider 클래스를 이용하여 HTTP 요청 메서드 Header에 포함된 JWT의 유효성을 검사하고 유효한 토큰인 경우 해당 토큰의 정보를 사용하여 Authenticaiton 인스턴스를 생성하고 인증된 사용자의 정보(아이디, 권한)를 SecurityContextHolder에 저장하도록 하였음.

#### 이메일 인증
회원가입 시 전송되는 이메일 인증 코드는 난수로 생성하며 MimeMessageHelper로 MimeMessage 인스턴스의 속성과 이메일 주소, 제목, 내용 등을 설정한 뒤 JavaMailSender 인터페이스를 통해 사용자의 이메일로 전송하도록 하였음.

#### JWT 비밀 키 설정 문제
JWT를 생성할 때 사용되는 Key 인스턴스는 생성 시 비밀 키(Secret Key)를 사용하는데 이 비밀 키를 단순히 클래스 내의 필드에 String 변수로 선언하여 사용한 것이 아쉬운 점으로 남아있음. 누군가가 이 비밀 키를 탈취하면 이를 통해 JWT를 생성하고 악용할 수 있기 때문인데 필드가 아닌 application.yml 파일에 비밀 키를 설정하여 사용하려고 시도하였으나 Gradle에서 JWT 관련 의존성 주입이 이루어졌음에도 불구하고 application.yml에서 jwt 속성 자체를 인식하지 못하는 문제가 발생하였음.

---

### Front-end

#### 로그인
Back-end 서버로 로그인 요청 시 응답 데이터로 수신한 Access Token과 권한 이름은 로컬 스토리지에 저장하여 새로고침 시에도 유지되도록 하였음. Refresh Token은 Cookie로 수신하는데 Back-end 서버에서 Cookie에 대해 httpOnly를 true로 설정하여 JavaScript로 접근할 수 없도록 하였음.

#### 인증과 인가
권한이 필요한 페이지는 접근 시 Vue Router의 Navigation Guards에서 로컬 스토리지의 Access Token 및 권한 이름을 확인하여 접근을 허용/거부하도록 하였음<p><p>
권한이 필요한 HTTP 요청 메서드는 Header에 Access Token을 포함하여 요청하도록 구현했고 Access Token이 만료된 경우에는 Cookie의 Refresh Token을 통해 Access Token을 재발급 받도록 하였음. Refresh Token도 만료된 경우는 alert로 사용자에게 로그인이 만료되었음을 알리고 강제로 로그아웃 되도록 하였음.<p><p>

#### 리소스 요청 후 렌더링 문제
Back-end 서버에 리소스를 요청한 후 필요한 데이터를 모두 불러오기 전에 화면이 먼저 렌더링 되어 출력 데이터가 누락되는 문제가 있었음. 이를 해결하기 위해 필요한 데이터가 전부 수신되었는지를 체크할 변수를 사용하여 v-if로 해당 변수의 값이 true 일 때만 div 태그를 출력하도록 하여 문제를 해결하였음.

#### 지도 API 성능 문제
지도에 마커를 생성하고 출력할 때 개수가 많은 경우 출력까지 걸리는 시간이 오래 걸리고 지도를 확대/축소하거나 드래그할 때의 속도 또한 크게 저하되는 문제가 있었음. 이 문제를 해결하기 위해 여러 개의 마커를 하나의 큰 마커로 표시하는 마커 클러스터러 기능을 사용해 봤지만 여전히 동일한 문제가 발생하였음.<p><p>
다른 방법으로 처음부터 모든 마커를 출력하는 것이 아니라 현재 출력 중인 지도 범위의 위도/경도 정보를 활용하여 해당 범위 내에 속하는 마커들만 생성/출력하도록 코드를 수정하였음. 지도를 확대/축소하거나 드래그할 때는 기존 생성 마커를 모두 제거하고 새로운 지도 범위 내에 속하는 마커들만 다시 생성/출력하도록 하였으며 지도 확대/축소 레벨을 제한하여 범위 내에 너무 많은 마커를 생성/출력되지 않도록 했음. 이를 통해 결과적으로 속도를 크게 향상시킬 수 있었음. 

#### 이메일 인증 개선방안
회원가입 시 인증 코드를 통해 이메일을 인증하는 과정에서 인증 코드 전송 후 인증까지 제한 시간을 설정하지 않은 것이 아쉬운 점으로 남아있음. Back-end 서버로 인증 코드 전송을 요청한 후 바로 제한 시간을 설정하여 이를 화면에 출력하고 이후 시간이 만료되면 인증 코드를 초기화한 후 사용자에게 다시 인증을 요청하는 방식으로 이메일 인증 절차를 개선할 수 있을 것으로 생각하고 있음.

#### 게시글 작성 개선방안
게시글 작성 페이지에 에디터와 파일 첨부 기능이 없어서 작성자가 게시글을 취향에 맞게 편집할 수 없고 이미지와 같은 파일을 첨부할 수 없는 부분이 아쉬운 점으로 남아있음. Vue.js와 연동되는 에디터 API와 v-file-input 등의 컴포넌트를 활용하여 게시글 편집 기능과 파일 첨부 기능을 추가할 수 있을 것으로 생각하고 있음.
