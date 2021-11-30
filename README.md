# MediFree

비대면 원격 의료 프로젝트

대면 진료와 비교했을 때, 비대면 진료 환경의 한계점을 극복하고, 장점을 극대화 할 수 있는 로직.

역할

유태훈: (doctor) 프론트 & 디자인 - 회원 가입, 예약 관리, 문진표, 진료실 , ppt 제작

조성민: (patient) 프론트 & 디자인 - 회원 가입, 예약 조회, 추가, 문진표, 진료실 , 회의록 관리

김찬솔: 백엔드 - Database 관리 함수 (검색,추가,삭제), 함수 적용, 어플리케이션 최적화

박승현: db설계 ,플젝 설계 ,data modeling , 기획, 영상 진료(webrtc), 보안(난독화,리버스 엔지니어링) , 영상제작


https://user-images.githubusercontent.com/62735681/143963509-932d8a41-8f1d-4f79-8854-1ce03a32cfe8.mp4





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
 

### functions 사용  
DB 관련 작업은 **DBManager** 클래스 대신 **Account, Patient, Doctor** 클래스를 이용해주세요.  
관련한 모든 클래스는 **static class**로 사용할 수 있도록 되어있습니다.
함수 사용 실패 시 `null` 혹은 `Empty Array`를 반환하니 반환값에 null 혹은 empty 체크를 한 후에 사용해주세요.  
필요한 기능이 있으면 **함수를 실행하는 주체, input, output과 함수명 규칙을 따른 함수명**을 전달해주세요, input은 필요에 따라 변경될 수 있습니다.  

* #### Account  
`PATIENT`, `DOCTOR` 전역 변수는 `userType` 인자에 사용됩니다.  

* #### Patient  
`MAJOR_**` 전역 변수는 `majorType` 인자에 사용됩니다.  
사용 전 `Patient.setPatient(uid)` 함수로 환자를 설정해주세요.  

* #### Doctor  
사용 전 `Doctor.setDoctor(uid)` 함수로 의사를 설정해주세요.  



