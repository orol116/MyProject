function selectAll(){ // 회원 전체 조회 함수
    // ajax 코드
    $.ajax({
        url :"selectAllManager",
        data : {"projectNo":projectNo},
        dataType : "json", // 응답 데이터의 형식을 JSON으로 지정
                           // -> 자동으로 JS 객체로 변환됨
            success : function( list ){
                //console.log(list);
                // list == JS 객체 배열 
    
                // 1) #memberList 내용 삭제
                const memberList = document.getElementById("member-list");
    
                memberList.innerHTML = "";

            // 2) list 를 for문을 이용해서 반복 접근

            for(let item of list){
                // item == 회원 1명의 정보가 담긴 JS 객체

                // 3) div 요소 생성
                const div1 = document.createElement("div");
                div1.classList.add("member-list");

                const div2 = document.createElement("div");
                div2.classList.add("member-img");

                const div3 = document.createElement("div");
                div3.classList.add("member-status-name");

                const span1 = document.createElement("span");
                span1.classList.add("status")
                
                const span2 = document.createElement("span");
                span2.classList.add("name")

                const img = document.createElement("img");

               if(item.profileImage != null){
                    
                    img.setAttribute("src",  contextPath+item.profileImage)
                }else{
                    
                    img.setAttribute("src",  contextPath+"/resources/images/user.png")
                } 

                const br = document.createElement("br");

                span1.innerText = "회원번호 : "+item.memberNo+"번"; // 회원 번호

                span2.innerText = "닉네임 : "+item.memberNickname; // 회원 닉네임

                const dropBtn = document.createElement("button");
                const backBtn = document.createElement("button");

                dropBtn.classList.add("dropBtn");
                dropBtn.innerText = "탈퇴";
                backBtn.classList.add("backBtn");
                backBtn.innerText = "가입 중";

                if(item.suspensionFlag=='N'){
                    backBtn.classList.add("back");
                }else{
                    dropBtn.classList.add("drop");
                }

              
                
                    dropBtn.addEventListener("click",function(){
    
                        $.ajax({
                            url : "dropMember",
                            data : {"memberNo":item.memberNo},
                            type : "POST",
                            success : function(result){
                        
                                if(result == 1){
                                    
                                    alert("탈퇴되었습니다.")

                                    dropBtn.classList.add("drop");
                                    backBtn.classList.remove("back");
                                    backBtn.classList.add("none");
                                    dropBtn.classList.remove("none");
                                    

                        
                                }else{
                                    alert("실패.");
                        
                                }
                        
                            },
                            error : function(){
                                console.log("에러발생");
                            }
                        });
    
    
    
                    });

                    backBtn.addEventListener("click",function(){
    
                        $.ajax({
                            url : "backMember",
                            data : {"memberNo":item.memberNo},
                            type : "POST",
                            success : function(result){
                        
                                if(result == 1){
                                    
                                    alert("탈퇴 취소가 완료되었습니다..")

                                    backBtn.classList.add("back");
                                    dropBtn.classList.remove("drop");
                                    dropBtn.classList.add("none");
                                    backBtn.classList.remove("none");

                                    

                                }else{
                                    alert("실패.");
                        
                                }
                        
                            },
                            error : function(){
                                console.log("에러발생");
                            }
                        });
    
    
    
                    });
                



                div3.append(span1,br,span2);
                
                div2.append(img);
                div2.append(div3, dropBtn, backBtn);
                div1.append(div2);

                memberList.append(div1);

         
              
                
            }

        },
        error : function(){

            console.log("에러발생")
        }

    });
}

function selectAllCount(){
    $.ajax({
        url: "selectAllCountManager",
        data : {"projectNo":projectNo},
        success : function(count){

            const memberCount = document.getElementById("member-count");
            memberCount.innerHTML ="";

            const h4 = document.createElement("h4");
            h4.innerText = "프로젝트 구성원"
            
            const span = document.createElement("span");
            span.innerText = "총 " + count + "명";

            memberCount.append(h4,span);

        },
        error : function(){
            console.log("에러발생")
        }
    })
    
}



/* 
function dropMember(){

    $.ajax({
        url : "dropMember",
        data : {"memberNo" : item.memberNo.value},
        type : "GET",
        success : function(result){
    
            if(result == 1){
                
                alert("탈퇴되었습니다.")
    
            }else{
                alert("실패.");
    
            }
    
        },
        error : function(){
            console.log("에러발생");
        }
    });

}
 */






// 일정 시간 마다 회원 목록 조회

// 즉시 실행 함수(속도 빠름, 변수명 중복문제 해결)
(function(){

    selectAllCount();
    selectAll(); // 함수 호출 -> 회원 목록을 1차적으로 먼저 조회

    // window.setInterval(함수, 딜레이(ms))
    setInterval(selectAll, 100000);
    // 함수 이름만 작성하면 함수가 실행되는게 아니라 함수 코드가 대입되는 것이다. 
    // -> 10초마다 selectAll 함수를 수행하게 된다.

    // setInterval()은 지연 -> 수행 -> 지연 -> 수행... 반복
    // -> 처음에 함수가 수행되지 않아서 공백인 상태가 있다. 지연이 시작이기때문에
    // -> 그 공백 상태를 메꿀려면 한번 따로 수행시킨다. 
})();