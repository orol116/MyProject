//로그인 시 이메일(아이디)/ 비밀번호 입력 확인

function loginValidate(){ //로그인 유효성 검사

    const inputEmail = document.getElementsByName("inputEmail")[0];
    
    const inputPw = document.getElementsByName("inputPw")[0];

    //이메일이 입력되지 않은 경우 false를 반환
    if(inputEmail.value.trim().length == 0){

        alert("이메일을 입력해주세요.");

        inputEmail.value = "";
        inputEmail.focus();

        return false; //기본 이벤트 제거
    }

    //비밀번호가 입력되지 않은 경우 false
    if(inputPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        inputPw.value = "";
        inputPw.focus();
        return false; 
    }

    return true;

}

//아이디 저장 체크박스가 체크되었을 때
document.getElementById("saveId").addEventListener("change", function(){

    //체크여부 확인
    // console.log(this.checked)
    //체크박스요소.checked : 체크 여부 반환 (T/F)

    if(this.checked){

        const str = "ID를 저장하시겠습니까?";

        if(!confirm(str)){
            //체크 해제
            this.checked = false;
        }
    }

});