/**
 * myPage-changePw.js 
 */

// *** 경고 출력, focus(), false 반환
function printAlert(el, message){
    alert(message);
    el.focus();
    return false;
}

// *** 입력 정보(현재 비밀번호, 새로운 비밀번호, 새로운 비밀번호 확인) 유효성 검사 ***

// 비밀번호 series
const currentPw = document.getElementsByName("currentPw")[0];
const newPw = document.getElementsByName("newPw")[0];
const newPwCheck = document.getElementsByName("newPwCheck")[0];

// 비밀번호 유효성 검사를 위한 변수
const regExp = /^[\w!@#_-]{6,30}$/;

function pwUpdateValidate(){

    // 현재 비밀번호
    if(currentPw.value.length == 0){
        return printAlert(currentPw, "현재 비밀번호를 입력해주세요.");
    }

    // 새로운 비밀번호
    if(newPw.value.length == 0){
        return printAlert(newPw, "새로운 비밀번호를 입력해주세요.");
    }

    // 새로운 비밀번호 확인
    if(newPwCheck.value.length == 0){
        return printAlert(newPwCheck, "비밀번호 확인을 입력해주세요.");
    }

    
    // 비밀번호 유효성 검사
    if(!regExp.test(newPw.value)){ // 새 비밀번호가 유효하지 않은 경우
        return printAlert(newPw, "영어, 숫자, 특수문자(!,@,#,-,_) 6~30 글자 사이로 작성해주세요.");

    } else{ // 새 비밀번호가 유효한 경우
        
        if(newPwCheck.value.length == 0){
            return printAlert(newPwCheck, "새로운 비밀번호 확인을 입력해주세요.");
        }
        
        if(newPw.value != newPwCheck.value){
            alert("새로운 비밀번호가 일치하지 않습니다.");
            newPw.value = "";
            newPwCheck.value = "";
            newPw.focus();

        } else{
            return true;
        }       
    }
}