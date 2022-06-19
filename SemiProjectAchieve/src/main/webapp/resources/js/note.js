
// 댓글을 불러오는 방식처럼 들어가는 함수
//페이지에서 재로딩 하는 방식이 아니라서 포워드 해야함

function noteList(){
    
    $.ajax({
        url : contextPath + "/note",
        data : {"memberNo" : memberNo}, //전달이 안되는거같은
        type : "GET",
        dataType : "JSON", 

        success : function(nList){
            //nList : 반환 받은 알림 목록
            // console.log(nList);

            //화면에 있는 목록 삭제
            const noteList = document.getElementById("noteList");
            noteList.innerHTML = "";

            if(nList != null){

                //nList 에 저장된 요소를 하나씩
                for(let note of nList){
                    
                    //발신자
                    const sender = document.createElement("div");
                                        
                    //알림 내용
                    const noteContent = document.createElement("p");
                    noteContent.innerHTML = note.noteContent; 
                    //br 처럼 줄바꿈요소가 있기때문에 단순 문자열 X
                    

                    //noteList에 자식요소로 추가
                    noteList.append(sender,noteContent);                    
                    
                }
                
            } else {
                //알림없으면 
                
                //없는 내용을 만들어서 자식으로 추가
                noteList.classList.add("noNote");
                const p = document.createElement("p");
                p.innerText = "알림이 없습니다";
                
                noteList.append(p);
            }


        },
        error : function(req,status,error){
            console.log("에러 발생");
            console.log(req.responseText);
        }
    });
}