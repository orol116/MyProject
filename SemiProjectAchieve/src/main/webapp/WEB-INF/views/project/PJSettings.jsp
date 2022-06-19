<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>project-settings</title>

    <!-- 헤더, 푸터 위한 main.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    
    <link rel="stylesheet" href="${contextPath}/resources/css/project-settings.css">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">


    </style>

</head>
<body>
    <main>  
        
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

		
        <div class="setting-main">


                    <div name="setting-header" id="infor-first">
                        프로젝트 설정 
                    </div>

                        <div name="setting-body" class="setting-body">
                            참여자 관리 (가입) <button id="member-pass"
                            onclick='location.href="${contextPath}/project/PJSign?projectNo=${param.projectNo}"'>변경</button>
                        </div>

                    
                        <div name="setting-body" class="setting-body">
                            참여자 관리 (차단, 탈퇴) <button id="member-control"
                            onclick='location.href="${contextPath}/member/dropMember?projectNo=${param.projectNo}"'>변경</button>
                        </div>
                  



            
                <div name="setting-body" class="setting-body">
                    프로젝트 공개
                
                    <select name="project-public" id="project-public" onchange="showValue(this)">
                        <option value="Y">공개</option>
                        <option value="P">클래스명 공개</option>
                        <option value="N">비공개</option>

                    </select>
                        
                </div>
            

            
                <div name="setting-body" class="setting-body">
                        

                    클래스 이름 변경 <button id="nameChangeBtn">변경</button>
                    <input type="text" id="PJNameChange" name="PJNameChange"
                        maxlength="20" autocomplete="off">
                </div>
            
            
                <div name="setting-body" class="setting-body">
                    전체 알림 발송 <button id="text-all">발송</button>
                </div>
                <textarea placeholder="전체 알림은 여기에 작성하세요." id="sendAll-text"></textarea>
            


                <div name="setting-body" class="setting-body">
                    소개 수정 <button id="IntroEditBtn">수정</button>
                    
                </div>
                <textarea placeholder="바꿀 소개를 작성하세요." id="projectIntro">개발2팀입니당</textarea>

                <div name="setting-body" class="setting-body">
                    공지 작성 <button id="write-notice" onclick="location.href='${contextPath}/board/write?mode=insert&type=2&projectNo=${param.projectNo}&cp=1'">작성</button>
                </div>



        </div>
           
    

        

    </main>




    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script> 

        const contextPath = "${contextPath}"; 

       
        const boardNo = "${detail.boardNo}"; 

       
        const loginMemberNo = "${loginMember.memberNo}";
        

        var projectNo = "${param.projectNo}";
        

    </script>
 
        

    
    <script src="${contextPath}/resources/js/PJSettings.js"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    

    


    
</body>
</html>