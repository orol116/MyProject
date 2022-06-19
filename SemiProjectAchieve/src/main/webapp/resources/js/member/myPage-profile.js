// 프로필 이미지 변경

// 이미지 선택 버튼
const inputImage = document.getElementById("input-image");

if(inputImage != null){
    // inputImage 요소가 화면에 존재할 때 (다른 화면에서 오류나는 것을 막기 위한 if문)

    inputImage.addEventListener("change", function(){

        if(this.files[0] != undefined){ // 파일이 선택되었을 때
            const reader = new FileReader();

            reader.readAsDataURL(this.files[0]);

            reader.onload = function(e){ 

                // 프로필 이미지의 src 속성의 값을 fileReader가 읽어온 파일의 url 로 변경
                const profileImage = document.getElementById("profile-image");

                profileImage.setAttribute("src", e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓰기

                document.getElementById("delete").value = 0;
                // 새로운 이미지가 선택됐기 때문에 1에서 0(안 눌림)으로 변경

            }
        }
    })
}

// 이미지 선택 확인
function profileValidate(){
    const inputImage = document.getElementById("input-image");

    // 삭제 기록용 input 요소
    const del = document.getElementById("delete"); // hidden 타입


    if(inputImage.value == "" && del.value == 0){ 
        // 빈 문자열 == 파일 선택 X && del의 값 0 == x버튼이 눌리지 않음 
        // ==> 아무 변화 없이 변경 버튼 클릭한 경우
        
        alert("이미지 선택한 후 변경 버튼을 클릭해주세요.");
        return false;
    }

    return true;
}


// 프로필 이미지 옆 x버튼 클릭 시
document.getElementById("delete-image").addEventListener("click", function(){

    // 0 : 안 눌림 / 1 : 눌림
    const del = document.getElementById("delete");

    if(del.value == 0){ // 눌러지지 않은 경우
        // 1) 프로필 이미지를 기본 이미지로 변경
        document.getElementById("profile-image").setAttribute("src", contextPath + "/resources/images/user.png");
    
        // 2) input type="file" 에 저장된 값(value)에 "" 대입 -> 변경하기 버튼 동작 막기
        document.getElementById("input-image").value = "";

        del.value = 1; // 눌린 것으로 인식
    }
})
