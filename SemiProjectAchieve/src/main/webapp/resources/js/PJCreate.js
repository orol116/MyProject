
const projectName = document.getElementById("projectName");

const regExp = /^[a-zA-Z0-9가-힣-_/]{2,20}/;


function PJDupCheck(){

    $.ajax({
        url : "PJDupCheck",
        data : {"projectName" : projectName.value},
        type : "GET",
        success : function(result){
    
            if(result == 1){
                
                alert("중복된 이름입니다.")
    
            }else{
                alert("사용가능한 프로젝트 이름입니다.");
    
            }
    
        },
        error : function(){
            console.log("에러발생");
        }
    });

}  



document.getElementById("name-double-check").addEventListener("click", function(){

     if(regExp.test(projectName.value)){

        PJDupCheck();

        

        

    }else{
        alert("2~20글자만 가능합니다. (특수문자 -,_,/만 가능)");
        projectName.value = "";
                    projectName.focus();

    };


    



});

const secretPJ = document.getElementById("secret-class");

const privatePJ = document.getElementById("private-class");

const publicPJ = document.getElementById("public-class");

secretPJ.addEventListener("click", function(){

    openStatus = secretPJ.value;

    


})

privatePJ.addEventListener("click", function(){

    openStatus = privatePJ.value;
    

})


publicPJ.addEventListener("click", function(){

    openStatus = publicPJ.value;

    
})





const projectQuota = document.getElementById("projectQuota");

const projectIntro = document.getElementById("projectIntro");




document.getElementById("createBtn").addEventListener("click", function(){



    if(projectQuota.value.trim().length == 0){
        projectQuota.value = "";
        projectQuota.focus();

        alert("정원을 입력하세요!");

    }

    if(projectIntro.value.trim().length == 0){

        projectIntro.value = "none intro";

        

    }




    

    


/*     function createVD(){


        for(let key in checkObj){
    
            if(!checkObj[key]){
    
                switch(key){
                    case"projectName": str="닉네임이";break;
                    case"projectQuota": str2="정원이";break;
                    case"openStatus": str2="공개 여부가";break;
                    case"projectIntro": str2="소개가";break;
                }
    
                str += "유효하지 않습니다."
                str2 += "입력되지 않았습니다"
    
                alert(str);
    
                alert(str2);
    
                document.getElementById(key).focus();
    
                return false;
    
    
            }
            
        }
    } */
    

})





