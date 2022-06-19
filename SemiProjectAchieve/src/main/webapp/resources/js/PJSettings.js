
// 프로젝트 이름 유효성 검사
const regExp = /^[a-zA-Z0-9가-힣-_/]{2,20}/;

const nameChangeBtn = document.getElementById("nameChangeBtn");

function PJDupCheck(){

    $.ajax({
        url : "PJDupCheck",
        data : {"projectName" : PJNameChange.value, "projectNo" : projectNo},
        type : "GET",
        success : function(result){
    
            if(result == 1){
                
                alert("중복된 이름입니다.");

                return false;
    
            }
    
        },
        error : function(){
            console.log("에러발생");
        }
    });

}  










// 프로젝트 이름변경 확인 O // 유효성검사 추가 중복되는 이름 거르기
nameChangeBtn.addEventListener("click", function(){

   const PJNameChange = document.getElementById("PJNameChange");

   PJNameChange.style.display = "block";

   nameChangeBtn.innerText = "적용";

   nameChangeBtn.addEventListener("click", function(){

    PJDupCheck();

    $.ajax({  
        url : "PJNameChange",
        data : {"projectName" : PJNameChange.value, "projectNo" : projectNo},
        type : "GET",
        success : function(result){
    
            if(result == 1){
                
                alert("프로젝트 이름이 변경되었습니다.");
    
            }else{
                alert("실패.");

            }
    
        },
        error : function(){
            console.log("에러발생");
        }
    });


    

    

    });
   




});


// 전체 쪽지 값 확인 null 값 ok
document.getElementById("text-all").addEventListener("click", function(){



    const sendAll = document.getElementById("sendAll-text");


    if(sendAll.value == ""){
        alert("전송할 내용이 없습니다!");
    }

    if(sendAll.value != ""){

        $.ajax({
            url : "sendAllText",
            data : {"boardContent" : sendAll.value, "projectNo" : projectNo, "loginMemberNo" : loginMemberNo},
            type : "GET",
            success : function(result){
                alert("전체 알림 발송이 완료되었습니다.");
            },
            error : function(){
                console.log("에러발생");
            }
        });


    } 

});





// 소개 변경 확인 소개글 지우기 확인
document.getElementById("IntroEditBtn").addEventListener("click", function(){

    const projectIntro = document.getElementById("projectIntro");

  

    if(projectIntro.value == ""){
    var result = confirm("소개글을 모두 지우시겠습니까?");

    if(result){
        projectIntro.value = "no Intro";

 

        $.ajax({
            url : "IntroEdit",
            data : {"projectIntro" : projectIntro.value, "projectNo" : projectNo},
            type : "GET",
            success : function(result){

                if(result == 1){
                    console.log("성공");  
                }else{
                    console.log("실패");
                }
                
                
    
            },
            error : function(){
                console.log("에러발생");
            }
        });
        
    }


    }


    if(projectIntro.value !== ""){




        $.ajax({
            url : "IntroEdit",
            data : {"projectIntro" : projectIntro.value, "projectNo" : projectNo},
            type : "GET",
            success : function(result){

                if(result == 1){
                    console.log("성공");  
                }else{
                    console.log("실패");
                }
                
                
    
            },
            error : function(){
                console.log("에러발생");
            }
        });


        


    }
    
});







 const showValue = (target) =>{


    if(target.value =="Y"){

        alert("공개로 변경됩니다.");
    }

    if(target.value =="N"){

        alert("비공개로 변경됩니다.");
    }

    if(target.value =="P"){

        alert("클래스명 공개로 변경됩니다.");
    }

    

    

    $.ajax({
        url : "openStatusChange",
        data : {"openStatus" : target.value, "projectNo": projectNo},
        type : "GET",
        success : function(result){

            if(result == 1){
                console.log("성공");  
            }else{
                console.log("실패");
            }

        },
        error : function(){
            console.log("에러발생");
        }
    });

}; 
