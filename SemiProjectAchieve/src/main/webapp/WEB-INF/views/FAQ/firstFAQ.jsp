<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ-login</title>

    <!-- 헤더, 푸터를 위한 css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/FAQ.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    <style>
    .selectBtn:nth-child(1){
    border: 1px solid #0e194e;
    background-color: #0e194e;
    color: white;
    }

    .selectBtn:nth-child(n+2){
    border: 1px solid black;
    background-color: white;
    color: black;
    }

    .selectBtn:nth-child(n+2):hover{
    background-color: #0e194e;
    color: white;   
    }
    </style>

</head>
<body>
    <main>  
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <section class="content">

            <section class="main">
                <div id="FAQ-container">

                    <div id="FAQ-main">
                        <div id="select-area">
                            <button class="selectBtn" onclick="location.href='/SemiProjectAchieve/FAQ/firstFAQ'">자주 묻는 질문</button>
                            <button class="selectBtn" onclick="location.href='/SemiProjectAchieve/FAQ/secondFAQ'">프로젝트 생성/관리</button>
                            <button class="selectBtn" onclick="location.href='/SemiProjectAchieve/FAQ/thirdFAQ'">회원 관련</button>
        
                        </div>
        
                        <ul class="FAQ">

                            <li>
                                <input type="checkbox" id="FAQ-1">
                                <label for="FAQ-1">아이디와 비밀번호를 분실했어요.</label>
                                <div>
                                    <p>아이디는 조회가 가능합니다!</p>
                                    <p>비밀번호는 가입했던 메일을 통해 재설정 해주세요!</p>
                                </div>
                            </li>

                            <li>
                                <input type="checkbox" id="FAQ-2">
                                <label for="FAQ-2">회원가입을 하고 싶어요.</label>
                                <div>
                                    <p>메인 페이지에서 회원 가입 버튼을 클릭해주세요!</p>
                                    <p>간단한 정보 입력으로 가입이 가능합니다!</p>
                                </div>
                            </li>

                        </ul>
        
                        
                </div>

            </section>
        </section>
    </main>


    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    
</body>
</html>