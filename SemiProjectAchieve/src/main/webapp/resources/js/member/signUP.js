// 유효성 검사 여부를 기록할 객체 생성
const checkObj = {
    "memberEmail"    :false,
    "memberPw"       :false,
    "memberPwConfirm":false,
    "memberName" :false,
    "memberNickname" :false,
    "memberBirth" :false,
    "memberTel"      :false
};



// 아이디 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("idMessage");

memberEmail.addEventListener("input", function(){
    // 입력이 되지 않은 경우
    if(memberEmail.value.length==0){
        emailMessage.innerText="아이디를 입력해주세요.";
        emailMessage.classList.remove("confirm");
        emailMessage.classList.remove("error");

        checkObj.memberEmail = false; // 유효하지 않음을 기록
        return;
    }

    // 입력된 경우
    const regExp =/^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;
    // + : 1개 이상. {1,}이렇게 사용도 가능
    if(regExp.test(memberEmail.value)){

        // ******** 이메일 중복검사 (ajax) 진행예정
        // ******** 이메일 중복검사 (ajax) 진행예정
        
        // $.ajax({k:v, k:v});// jQuery ajax 기본형태

        // memberEmail.value == 입력된 이메일

        $.ajax({
            url:"emailDupCheck", // 필수 속성 url
            // 현재 주소 : /community2/member/signUp
            // 상대경로 : /community2/member/emailDupCheck
            date : { "memberEmail":memberEmail.value},
            // data 속성 : 비동기 통신 시 서버로 전달할 값을 작성(JS객체 형식)
            // -> 비동기 통신 시 input에 입력된 값을 "memberEmail" 이라는
            // key 값(파라미터)으로 전달

            type : "GET", // 데이터 전달 방식

            success : function(result){
                // 비동기 통신(ajax)가 오류 없이 요청/응답을 성공한 경우

                // 매개변수 result : servlet에서 출력된 result 값이 담겨있다.

                if(result == 1){ // 중복 O

                    emailMessage.innerText="이미 사용 중인 이메일입니다";
                    emailMessage.classList.add("error");
                    emailMessage.classList.remove("confirm");
                    checkObj.memberEmail = false; // 유효함을 기록

                }else{ //중복 x
                    emailMessage.innerText="사용 가능한 이메일입니다. .";
                    emailMessage.classList.add("confirm");
                    emailMessage.classList.remove("error");
                    checkObj.memberEmail = true; // 유효함을 기록
                }
            },

            error : function(){
                // 비동기 통신(ajax)중 오류가 생기는 경우
                console.log("에러발생");
            }   
        });
    }else{
        emailMessage.innerText="이메일 형식이 올바르지 않습니다. .";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        checkObj.memberEmail = false; // 유효하지 않음을 기록
    }
});

// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfrim = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");

memberPw.addEventListener("input",function(){

    if(memberPw.value.length ==0){
        pwMessage.innerText= " 영어, 숫자, 특수문자(!,@,#,-,_) 6~30 글자 사이로 작성해주세요";
        pwMessage.classList.remove("confirm","error");

        checkObj.memberPw = false;
        return;
    }

    const regExp =/^[\w!@#_-]{6,30}$/;

    if(regExp.test(memberPw.value)){ // 비밀번호 유효

        checkObj.memberPw = true;

        if(regExp.test(memberPwConfrim.value.length==0)){ // 확인 작성x
            
            pwMessage.innerText="유효한 비밀번호 형식입니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");

        }else{
            checkPw(); // 비밀번호 일치 검사 함수 호출()
        }


    }else{

        pwMessage.innerText="비밀번호 형식이 올바르지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");

        checkObj.memberPw = false;

    }
});


// 비밀번호 확인 유효성 검사
// 함수명() : 함수 호출(수행)
// 함수명   : 함수에 작성된 코드 반환. 내용이 그대로 반환됨
// -> function 위치에 checkPw라고 작성하면 그 안에 내용이 그대로 반환되서 정상적 작동!!
memberPwConfrim.addEventListener("input",checkPw);


// 비밀번호 / 비밀번호 확인이 같을 경우

function checkPw(){ // 비밀번호 확인 일치검사
    if(memberPw.value==memberPwConfrim.value){

        pwMessage.innerText="비밀번호가 일치합니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");

        checkObj.memberPwConfirm = true;

    }else{
        pwMessage.innerText="비밀번호가 일치하지 않습니다. .";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");

        checkObj.memberPwConfirm = false;
    }
}



// 이름 유효성 검사
const memberName = document.getElementById("memberName");
const nameMessage = document.getElementById("nameMessage");

memberName.addEventListener("input",function(){

    // 입력되지 않은 경우
    if(memberName.value.length==0){
        nameMessage.innerText = "한글 2~5글자 사이로 작성해주세요.";
        nameMessage.classList.remove("confirm");
        nameMessage.classList.add("error");

        checkObj.memberName = false;
        return;
    }

    const regExp = /^[가-힣]{2,5}$/;

    if(regExp.test(memberName.value)){

        nameMessage.innerText="사용가능한 이름입니다. .";
        nameMessage.classList.add("confirm");
        nameMessage.classList.remove("error");
        checkObj.memberName = true;
        
    }else{
        nameMessage.innerText="이름 형식이 올바르지 않습니다. .";
        nameMessage.classList.add("error");
        nameMessage.classList.remove("confirm");
        checkObj.memberName = false;

    }

});

// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNickname.addEventListener("input",function(){

    // 입력되지 않은 경우
    if(memberNickname.value.length==0){
        nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
        nicknameMessage.classList.remove("confirm");
        nicknameMessage.classList.remove("error");

        checkObj.memberNickname = false;
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if(regExp.test(memberNickname.value)){

        // 닉네임 중복 검사(ajax) 진행
        $.ajax({
            url:"nicknameDupCheck", // 필수 작성
            data:{"memberNickname":memberNickname.value}, // 서버로 전달할 값(파라미터)
            type : "GET", // 데이터 전달 방식(기본값 GET)
            success:function(res){ // 비동기 통신 성공 시 (에러 발생 X)
                // 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장됨

                if(res == 0){ // 중복이 아님.

                    nicknameMessage.innerText="사용가능한 닉네임 입니다. .";
                    nicknameMessage.classList.add("confirm");
                    nicknameMessage.classList.remove("error");
                    checkObj.memberNickname = true;

                }else{ // 중복이다.
                    nicknameMessage.innerText="이미 사용중인 닉네임 입니다. .";
                    nicknameMessage.classList.add("error");
                    nicknameMessage.classList.remove("confirm");
                    checkObj.memberNickname = false;
                }
            },
            error : function(){ // 비동기 통신 중 에러가 발생한 경우
                console.log("에러발생")
            }


        })
        
    }else{
        nicknameMessage.innerText="닉네임 형식이 올바르지 않습니다. .";
        nicknameMessage.classList.add("error");
        nicknameMessage.classList.remove("confirm");
        checkObj.memberNickname = false;
    }
});

// 생년월일 유효성 검사

const memberBirth = document.getElementsByName("memberBirth");
const birthMessage = document.getElementById("birthMessage");

for(let i of memberBirth){

    i.addEventListener("input",function(){

        if(i.value.length==0){
            checkObj.memberBirth = false;
            return;
        }
    

        const regExp =/^[0-9]{1,4}$/;

        if(regExp.test(i.value)){
            birthMessage.innerText = "유효한 생년월일 입니다.";
            birthMessage.classList.add("confirm");
            birthMessage.classList.remove("error");
            checkObj.memberBirth = true;
        }else{
            birthMessage.innerText = "생년월일 형식이 유효하지 않습니다.";
            birthMessage.classList.add("error");
            birthMessage.classList.remove("false");
            checkObj.memberBirth = false;
        }

    })
}


// 전화번호 유효성 검사
const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input",function(){
    // 입력이 되지 않은 경우
    if(memberTel.value.length ==0){
        
        telMessage.innerText="전화번호를 입력해주세요.(- 제외)";

        telMessage.classList.remove("confirm");
        telMessage.classList.remove("error");

        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록

        return;
    }

    // 전화번호 정규식
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if(regExp.test(memberTel.value)){ // 유효한 경우

        telMessage.innerText = "유효한 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        checkObj.memberTel = true; // 유효한 상태임을 기록

    }else{ // 유효하지 않은 경우
        
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록
    }
});


// 회원가입 버튼 클릭 시 유효성 검사가 완료 되었는지 확인하는 함수
function signUpValidate(){

    // checkObj에 있는 모든 속성을 반복 접근하여 
    // false가 하나라도 있는 경우에는 form태그 기본 이벤트 제거
    let str;

    for(let key in checkObj){ // 객체용 향상된 for문

        // 현재 접근중인 key 의 value가 false인 경우
        if(!checkObj[key]){

            switch(key){
                case "memberEmail":     str="이메일이"; break;
                case "memberPw":        str="비밀번호가"; break;    
                case "memberPwConfirm": str="비밀번호 확인이"; break;
                case "memberName":      str="이름이"; break;
                case "memberNickname":  str="닉네임이"; break;
                case "memberBirth":     str="생년월일이"; break;
                case "memberTel":       str="전화번호가"; break;
            }

            str +="유효하지 않습니다.";

            alert(str);

            document.getElementById(key).focus();

            return false; // form 태그 기본 이벤트 제거
        }

    }

    return true;
}