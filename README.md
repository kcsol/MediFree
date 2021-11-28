# MediFree

비대면 원격 의료 프로젝트

대면 진료와 비교했을 때, 비대면 진료 환경의 한계점을 극복하고, 장점을 극대화 할 수 있는 로직.

역할

유태훈: (doctor) 프론트 & 디자인 - 회원 가입, 예약 관리, 문진표, 진료실 

조성민: (patient) 프론트 & 디자인 - 회원 가입, 예약 조회, 추가, 문진표, 진료실

김찬솔: 백엔드 - Database 관리 함수 (검색,추가,삭제), 함수 적용

박승현: db설계, 프로젝트 기획,설계, webrtc, 보안(난독화 및 리버스 엔지니어링)



for mobile app program project

compileSdk 31

minSdk 25

targetSdk 31


// id 명
patient -> p_ 
doctor -> d_ 들어가도록

기본적으로 user(ex. p_) + kt(ex. quest) + type(ex.btn) 양식으로



// 변수명


//함수명
규칙: 동작(add,search) + doctor or patient(생략 가능) + field or document name 

 ex) searchDoctorSchedule
 
 
 //난독화
 테스트 할 때, 난독화 관련 설정 꺼두고 진행하길 바랍니다. 오래걸리고, 로그 분석이 힘들어집니다.
 
 외부 라이브러리 사용 시, 난독화 때문에 문제가 생길 수 있습니다. 사용하는 외부 라이브러리 얘기해주세요.
 




