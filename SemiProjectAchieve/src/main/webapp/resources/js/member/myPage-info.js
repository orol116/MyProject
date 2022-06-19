/**
 *  myPage-info.js
 */


// *** 입력 정보(이름, 닉네임, 전화번호) 유효성 검사 ***

// 이름
const memberName = document.getElementsByName("memberName")[0];

// 닉네임
const memberNickname = document.getElementsByName("memberNickname")[0];

// 전화번호
const memberTel = document.getElementsByName("memberTel")[0];

// *** 경고 출력, 입력값 무효, focus(), false 반환
function printAlert(el, message){
    alert(message);
    el.value = "";
    el.focus();
    return false;
}

function infoUpdateValidate(){
    // 이름 
    if(memberName.value.length == 0){
        return printAlert(memberName, "이름을 입력해주세요.");
    }

    // 닉네임
    if(memberNickname.value.length == 0){
        return printAlert(memberNickname, "닉네임을 입력해주세요.")
    }

    // 전화번호
    if(memberTel.value.length == 0){
        return printAlert(memberTel, "전화번호를 입력해주세요. (- 제외)");
    }

    // 이름, 닉네임 유효성 검사
    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if(!regExp.test(memberName.value)){
        return printAlert(memberName, "이름은 한글 2~10글자 사이로 작성해주세요.");
    }

    if(!regExp.test(memberNickname.value)){
        return printAlert(memberNickname, "닉네임은 영어/숫자/한글 2~10글자 사이로 작성해주세요.");
    }

    // 전화번호 유효성 검사
    const regExp2 = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;
    if(regExp2.test(memberTel.value)){
        return true;
    } else{
        return printAlert(memberTel, "전화번호 형식이 올바르지 않습니다.");
    }  
}