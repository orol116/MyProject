
const projectName = document.getElementById("projectName");

const regExp = /^[a-zA-Z0-9가-힣-_/]{2,20}/;

const checkObj = {

"projectName" : false,
"projectQuota" : false,
"projectIntro" : false,
"secretPJ" : false

}



document.getElementById("name-double-check").addEventListener("click", function(){

     if(regExp.test(projectName.value)){

        $.ajax({
            url : "PJDupCheck",
            data : {"projectName" : projectName.value},
            type : "GET",
            success : function(result){

                if(result == 1){
                    
                    alert("중복된 이름입니다.");

                    checkObj.projectName = false;

                   

                }else{
                    alert("사용가능한 프로젝트 이름입니다.");

                    checkObj.projectName = true;

                }

            },
            error : function(){
                console.log("에러발생");
            }
        });

        

        

    }else{
        alert("2~20글자만 가능합니다. (특수문자 -,_,/만 가능)");
        projectName.value = "";
                    projectName.focus();

                    checkObj.projectName = false;

    };


    



});

const secretPJ = document.getElementById("secret-class");

const privatePJ = document.getElementById("private-class");

const publicPJ = document.getElementById("public-class");

secretPJ.addEventListener("click", function(){

    openStatus = secretPJ.value;

    
    checkObj.secretPJ = true;

})

privatePJ.addEventListener("click", function(){

    openStatus = privatePJ.value;

    checkObj.secretPJ = true;

})


publicPJ.addEventListener("click", function(){

    openStatus = publicPJ.value;

    checkObj.secretPJ = true;
    
})





const projectQuota = document.getElementById("projectQuota");

const projectIntro = document.getElementById("projectIntro");




document.getElementById("createBtn").addEventListener("click", function(){



    if(projectQuota.value.trim().length == 0){
        projectQuota.value = "";
        projectQuota.focus();

        alert("정원을 입력하세요!");

        checkObj.projectQuota = false;

    }else{
        checkObj.projectQuota = true;

    }

    if(projectIntro.value.trim().length == 0){

        projectIntro.value = "none intro";
        
        checkObj.projectIntro = true;

    }else{

        checkObj.projectIntro = true;
    }



    function createVD(){

        let str;


        for(let key in checkObj){
    
            if(!checkObj[key]){
    
                switch(key){
                    case"projectName": str="닉네임이";break;
                    case"projectQuota": str="정원이";break;
                    case"openStatus": str="공개 여부가";break;
                    case"projectIntro": str="소개가";break;
                }
    
                str += "유효하지 않습니다."
                
    
                alert(str);
    
               
    
                document.getElementById(key).focus();
    
                return false;
    
    
            }
            
        }

        return true;
    } 
    
    

})





