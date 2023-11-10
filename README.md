<h2><img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/477d5789-f58e-41ef-ad66-854f22bc8575" width="20"/> 반려동물 동반 가능 시설을 쉽게 찾을 수 있는 웹사이트 포맵</h2>

목차
----
1. 프로젝트 소개
2. 기술 스택
3. 프로젝트 구조
4. 주요 기능
5. 데모 영상

## 1. 프로젝트 소개

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/e4e7cb55-3849-4f1e-9c60-9930d8449bcd" width="640"/><p>
<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/bb0e21e0-040f-45e6-995d-d96bb88852b6" width="640"/><p>

<strong>우리나라의 반려가구 수 2022년 기준 552만 가구 (출처: KB경영연구소)</strong><br>

반려동물을 키우는 사람들은 방문하고자 하는 장소에 반려동물 동반이 가능한지 일일이 찾고 알아봐야 함<br>

공공데이터와 사용자의 위치 정보를 활용, 사용자가 다양한 검색 옵션으로 반려동물 동반 가능 시설을 쉽게 찾을 수 있도록 해주는 웹사이트를 개발하고자 함<br>

사이트 이름인 포맵은 paw(동물의 발) + map(지도)를 합쳐 만들었음

## 2. 기술 스택

- Back-end

> Java, Spring Boot, MySQL, Oracle

- Front-end

> HTML, CSS, JavaScript, Vue, Bootstrap

- Version Control

> Git, Github

## 3. 프로젝트 구조

- 프로젝트 구성도

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/d326d64f-0066-42a4-a82a-e4677615c5f6" width="500"/><br>

- Back-end 개발 환경

> Java Version 11, Spring Tool Suite 4.4.8.0 Release, Spring Boot 2.7.10, Gradle, Spring Data JPA, JDBC API, Oracle Driver, Spring Security, Spring Web, SMTP, YAML, Lombok, ModelMapper 3.0.0

- Front-end 개발 환경

> Vue 2, Bootstrap 5.2.3, Kakao 지도 API, Vue Router 3.6.5, Axios 1.4.0

- 형상 관리

> Git 2.40.0, Github

---

- ERD

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/62e327d2-c078-4ff3-8ce2-3c462169da7d" width="800"/><br>

공공데이터포털의 한국문화정보원 전국 반려동물 동반 가능 문화시설 위치 데이터를 활용하였음<br>

프론트단의 요구에 맞는 데이터만을 효율적으로 보내기 위해 공공데이터포털 API를 이용하는 것이 아니라 CSV 파일을 받아 수정 후 임포트 하여 DB를 구성하고 REST API를 설계하였음

---

- REST API

> 지도 페이지 관련

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/64cf559b-0285-4ad5-a3d1-9045a415a8a1" width="640"/><br>

> 게시판 페이지 관련

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/ffd40d6c-466e-4c33-8f38-f865aae83f2c" width="640"/><br>

> 인증과 인가 관련

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/30383a2e-4f77-4cbb-a172-8b0803af9aa0" width="640"/><br>

> 회원 관련

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/e66d88b1-0881-4b17-a1ef-3e1bfd925cd2" width="640"/>

## 4. 주요 기능

- 메인 페이지 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/1ec5108e-ddbf-4469-bd07-b043fd74aa98" width="640"/><br>

동 이름 검색 시 지도 페이지로 이동하여 해당 읍면동에 있는 반려동물 동반 가능 시설을 지도와 마커, 목록으로 확인할 수 있음<br>

카테고리 버튼 클릭 시 지도 페이지로 이동하여 접속 중인 기기의 현재 위치를 기준으로 주변에 있는 해당 카테고리의 반려동물 동반 가능 시설을 확인할 수 있음

---

- 지도 페이지 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/edd25b29-3dfd-4016-a1f8-133f48b9d12a" width="640"/><br>

동 이름 검색 시 해당 읍면동에 있는 반려동물 동반 가능 시설을 지도와 마커, 목록으로 확인할 수 있음<br>

카테고리 선택 시 접속 중인 기기의 현재 위치를 기준으로 주변에 있는 해당 카테고리의 반려동물 동반 가능 시설을 확인할 수 있음<br>

Select 메뉴를 통해 카테고리, 시도, 시군구, 읍면동을 조합하여 조회할 수 있으며 각 Select에서 항목을 선택할 때마다 선택한 조합에 해당하는 반려동물 동반 가능 시설을 바로바로 지도와 마커, 목록으로 확인할 수 있음<br>

마커를 클릭하거나 목록에서 카드 또는 상세보기 클릭 시 해당 시설의 상세정보 및 반려동물 동반 관련 사항을 확인할 수 있음<br>

북마크 메뉴 클릭 시 해당 시설을 북마크에 추가할 수 있음 (로그인 필요)

---

- 게시판 페이지 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/94ae60fb-de24-4db2-953c-7e6cf244f0b5" width="640"/><br>
<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/2879fcc5-32a1-4190-b068-d4de61dfdcac" width="640"/><br>

게시글 및 댓글 작성을 통해 다른 회원들과 의사소통할 수 있음<br>

게시글과 댓글은 수정, 삭제할 수 있음<br>

하단의 검색 메뉴에서 제목+내용, 제목, 내용, 닉네임 중 하나를 선택하여 원하는 검색어로 게시글을 검색 및 조회할 수 있음

---

- 로그인 페이지 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/b6e79fc8-04e2-462b-8541-3ce415dfb22d" width="640"/><br>
<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/6cbf994e-2f46-418c-abcf-91675e0dfb64" width="640"/><br>

아이디, 비밀번호 입력을 통해 로그인할 수 있으며 아이디 저장 체크박스를 통해 로그아웃 후에도 이전 로그인 시 사용한 아이디를 쉽게 입력할 수 있음

---

- 회원가입 페이지 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/55565c61-8d69-4752-a704-616fc833fc86" width="640"/><br>
<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/14009366-ffef-49b1-82e3-91d917bce160" width="640"/><br>

아이디, 비밀번호, 닉네임, 이메일을 입력하여 회원가입할 수 있음<br>

아이디, 비밀번호, 닉네임, 이메일은 모두 길이 제한, 글자 제한이 있으며 이 제한을 지키지 않을 경우 회원가입이 불가함<br>

아이디와 닉네임은 중복 확인이 필요하고 중복 확인을 하지 않거나 기존 다른 회원과 중복되는 아이디인 경우 회원가입이 불가함<br>

이메일 입력 후 메일 인증 버튼을 눌러 인증번호를 수신한 뒤 이를 입력해야 하며 인증번호를 입력하지 않거나 틀릴 경우 회원가입이 불가함

---

- 마이페이지 북마크 탭 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/8d8487be-73dd-49ba-8790-d3fffd28281d" width="640"/><br>

회원이 지도 페이지에서 추가한 시설 북마크를 조회 및 삭제할 수 있음

---

- 마이페이지 내 게시글 탭 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/d3353994-5cd0-482d-b39b-e85d6baf6e7c" width="640"/><br>

회원이 작성한 본인의 게시글을 모두 조회할 수 있으며 제목+내용, 제목, 내용 중 하나를 선택하여 원하는 검색어로 게시글을 검색 및 조회할 수 있음. 또한 체크박스를 이용하여 한번에 여러 게시글을 삭제할 수 있음

---

- 마이페이지 회원정보 탭 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/8c38d496-0ef5-4c79-89e8-2682145cecc5" width="640"/><br>

회원이 본인의 아이디, 닉네임, 이메일을 확인할 수 있으며 비밀번호를 변경할 수 있음

---

- 회원탈퇴 페이지 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/e652520d-e20d-4415-abae-9c5131a0a01d" width="640"/><br>

마이페이지의 회원정보 탭에서 회원탈퇴 페이지로 이동할 수 있으며 비밀번호 입력을 통해 회원탈퇴할 수 있음

---

- 관리페이지 게시글 관리 탭 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/858340fa-3db2-45b3-8f36-976087ce34dc" width="640"/><br>

관리자 계정으로 로그인했을 때만 이용할 수 있으며 게시판의 전체 게시글을 조회, 검색, 삭제할 수 있음

---

- 관리페이지 회원 관리 탭 주요 기능

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/588639f5-6e07-4aa5-8a0a-37612b3630e9" width="640"/><br>

관리자 계정으로 로그인했을 때만 이용할 수 있으며 전체 회원의 정보를 조회 및 검색할 수 있고 특정 회원을 차단할 수 있음

---

- 부트스트랩을 활용한 반응형 웹 디자인

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/9cac85a7-1c63-4b3e-8b0a-472545a1ed0f" width="210"/>
<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/2d14b722-4418-4610-9a0d-dc7fb9a22387" width="210"/>
<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/385ed585-c12c-42e3-bb15-0d1acb757fe8" width="210"/>

## 5. 데모 영상

- 메인 페이지

> 동 이름 검색

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/e5b18f7c-08ca-48b7-a312-0de953180e61"/><br>

> 카테고리 버튼 클릭

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/dfe5465b-6a26-4611-839a-0f5b27c5f139"/><br>

---

- 회원가입 페이지

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/faaa924f-980f-46ec-bb0f-539d584ef68b"/><br>

---

- 로그인 페이지

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/eee76c66-dff8-42b2-a80e-227f60bb7ab9"/><br>

---

- 지도 페이지

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/7250c8f9-7cab-4da3-8eaa-6acc64b33c1c"/><br>

---

- 게시판 페이지

> 게시글 및 댓글 조회

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/62b34470-a58d-4f87-b6dc-9b6e81aa378d"/><br>

> 게시글 및 댓글 작성, 수정, 삭제

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/c27edd3c-b77f-4046-93ef-40871c0944da"/><br>

---

- 마이페이지

> 북마크 탭

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/211ed7f8-6ce1-4701-b062-77320e8100a1"/><br>

> 내 게시글 탭

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/7e080ec9-689b-418d-8c34-deab4b13fb77"/><br>

> 회원정보 탭

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/817339c2-f40c-4269-bd52-4642698a8d1a"/><br>

> 회원탈퇴

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/39516589-5703-400f-ac43-f02c024aee75"/><br>

---

- 관리페이지

> 게시글 관리 탭

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/6e66ee30-ff93-4e6b-93cf-cf15cb72d77e"/><br>

> 회원 관리 탭

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/880bb5d6-381f-4093-80d6-85c1e4fda664"/><br>

---

- 부트스트랩을 활용한 반응형 웹 디자인

<img src="https://github.com/KyeonghoJeong/pawmap_front-end/assets/128965451/2639eb7b-6223-4d4a-b52e-d2d65e3df1bd"/>

---
