const inputImage = document.getElementsByClassName("inputImage");
const attach = document.getElementById("img0");
const deleteSet = new Set();
const preview = document.getElementsByClassName("preview");
const deleteAttach = document.getElementsByClassName("deleteAttach")[0];


const manager = document.getElementById("manager");

// (function() {

//     const goToListBtn = document.getElementById("goToListBtn");

//     if(goToListBtn != null) { // 목록으로 버튼이 화면에 있을때만 이벤트 추가
//         goToListBtn.addEventListener("click", function() {

//             const pathname = location.pathname; // 주소상에서 요청 경로 반환

//             let url = pathname.substring(0, pathname.indexOf("/", 1))

//             url += "/board/main?";

//             const params = new URL(location.href).searchParams;
//             const type = "type=" + params.get("type"); // type=1
//             const projectNo = "projectNo=" + params.get("projectNo");

//             let cp;

//             if (params.get("cp") != null) { 
//                 cp = "cp=" + params.get("cp"); 
//             } else {
//                 cp = "cp=1";
//             }

//             url += type + "&" + projectNo + "&" + cp;
//             location.href = url;
//         });
//     }

// })();


// 검색창에 이전 검색기록 반영하기
(function(){
    const select = document.getElementById("search-key");
    const option = document.querySelectorAll("#search-key > option");
    const input = document.getElementById("search-query");

    if (select != null) { 

        const params = new URL(location.href).searchParams;
        const key = params.get("key");
        const query = params.get("query");

        input.value = query;

        for(let op of option) {
            if (op.value == key) {
                op.selected = true;
            }
        }
    }
})();

// 게시글 삭제 
(function(){
    const deleteBtn = document.getElementById("deleteBtn"); // 존재하지 않으면 null

    if(deleteBtn != null){ // 버튼이 화면에 존재할 때

        deleteBtn.addEventListener("click",function(){

            let url = "delete" // 상대경로 형식으로 작성
            
            // 1) 쿼리 스트링에 존재하는 파라미터 모두 얻어오기
            const params = new URL(location.href).searchParams;

            // 2) 원하는 파라미터만 얻어와 변수에 저장
            const no = "?no="+params.get("no"); // ?no=1508

            const type = "&type="+params.get("type"); // &type=1

            const projectNo = "&projectNo="+params.get("projectNo"); // 

            // url에 쿼리스트링 추가
            url+= no + projectNo+ type; // delete?no=1508&type=1
          

            if(confirm("정말로 삭제하시겠습니까?")){
                location.href= url; // get 방식으로 url에 요청
            }

        })
    }

})();




// 게시글 작성 유효성 검사
function writeValidate(){

    const boardTitle = document.getElementsByName("boardTitle")[0];
    const boardContent = document.getElementById("boardContent");
    const boardOption = document.getElementById("boardOption");

    if (boardTitle.value.trim().length == 0) {
        alert("제목을 입력해주세요!");
        boardTitle.value = "";
        boardTitle.focus();
        return false;
    }
    if (CKEDITOR.instances.boardContent.getData().length == 0) {
        alert("내용을 입력해주세요!");
        //CKEDITOR.instances.boardContent.getData() = "";
        boardContent.focus();
        return false;
    }
    if (boardOption.value.trim().length == 0){
        alert("게시판 종류를 선택해주세요!");
        boardOption.value = "";
        boardOption.focus();
        return false;
    }


    return true;
}



// 게시판 첨부파일 변경
const inputFile = document.getElementById("input-file");

if(inputFile != null){ // inputImage 요소가 화면에 존재할 때 

    // input type="file" 요소는 파일이 선택되어 업로드 될 때 change 이벤트가 발생한다.
    inputFile.addEventListener("change", function(){

        // 이벤트가 발생한 요소 == input type="file"
        // files : input type ="file"만 사용 가능한 속성으로 
        //          선택된 파일 목록(배열)을 반환한다. 


        if(this.files[0] != undefined){ // 파일이 선택되었을 때

            const reader = new FileReader();
            // 자바 스크립트의 내장객체 FileReader
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체

            reader.readAsDataURL(this.files[0]);
            // FileReader.readAsDataURL(파일)
            // - 지정된 파일의 내용을 읽기 시작함.
            // - 읽어오는게 완료되면 result 속성 data에 
            // 읽어온 파일의 위치를 나타내는 url이 포함된다. 
            // -> 해당 url을 이용해서 브라우저에 이미지를 볼 수 있다.

            // FileReader.onload = function(){}
            // -FileReader가 파일을 다 읽어온 경우 함수를 수행
            reader.onload = function(e){
                // e : 이벤트 발생 객체
                // e.target : 이벤트가 발생한 요소(객체) -> FileReader
                // e.target.result : FileReader가 읽어온 파일의 URL

                // 프로필 이미지의 src속성의 값을 FileReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");

                profileImage.setAttribute("src",e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓴다.

                const del = document.getElementById("delete");
                // 새로운 이미지가 선택되었기 때문에 1->0 (안눌러짐 상태)으로 변경

            }
        }

    })
}

// 첨부파일 이름, 파일 크기 출력
if(attach != undefined){
    attach.onchange = () => {
        const selectedFile = attach.files[0];
        const attachName = document.getElementById("attachName");
        const attachSize = document.getElementById("attachSize");

        attachName.innerText = selectedFile.name;
        attachSize.innerText = (selectedFile.size) / 1024 + 'KB';
    };
}

(function() {
    if(deleteAttach != undefined){
        deleteAttach.addEventListener("click", function() {

            if (document.getElementById("img0").value != "") {
                document.getElementById("img0").value = "";
                document.getElementById("attachName").innerText = "";
                document.getElementById("attachSize").innerText = "";
            }

        })
    }
})();


(function(){
    $.ajax({
        url : "manager",
        data : {"projectNo":projectNo},
        success: function(managerNo){
            if(managerNo==loginMemberNo){
                manager.classList.remove("display-none")
            }else{
                manager.classList.add("display-none")
            }
        },
        error:function(){
            console.log("에러 발생")
        }


    });
})();




document.getElementById("approveBtn").addEventListener("click", function(){


    $.ajax({
        url : "ApproveBtn",
        data : {"memberNo" : loginMemberNo, "projectNo" : projectNo},
        type : "GET",
        success : function(result){
 
            if(result == 1){
                
                alert("가입 신청에 성공했습니다.");
                location.href = contextPath + "/project/PJ/PJSearch/list";
 
            }else{
                alert("가입 신청 실패");
 
            }
 
        },
        error : function(){
            console.log("에러발생");
        }
    }); 


});



