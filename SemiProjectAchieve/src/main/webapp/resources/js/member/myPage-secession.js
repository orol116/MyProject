const agree = document.getElementById("secession-agree");

function secessionValidate(){

    if(!agree.checked){ // 체크되어있지 않으면
        alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.");
        return false;
    } else{
        if(confirm("정말 탈퇴하시겠습니까?")){
            return true;
        } else{
            return false;
        }
    }
}