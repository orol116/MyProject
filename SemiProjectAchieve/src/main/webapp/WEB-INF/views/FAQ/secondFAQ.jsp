<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ-create-project</title>

    <!-- 헤더, 푸터를 위한 css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <!-- 사이드바 css 포함 -->
    <link rel="stylesheet" href="${contextPath}/resources/css/FAQ.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">
    
    <style>
    .selectBtn:nth-child(2){
    border: 1px solid #0e194e;
    background-color: #0e194e;
    color: white;
    }

    .selectBtn:nth-child(odd){
    border: 1px solid black;
    background-color: white;
    color: black;

    }

    .selectBtn:nth-child(odd):hover{
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
                                <label for="FAQ-1">프로젝트를 생성하고 싶어요.</label>
                                <div>
                                    <p>로그인 후 프로젝트 만들기 메뉴를 이용해주세요!</p>
                                </div>
                            </li>
        
                            <li>
                                <input type="checkbox" id="FAQ-2">
                                <label for="FAQ-2">어떤 프로젝트가 있는지 보고 싶어요.</label>
                                <div>
                                    <p>로그인 후 프로젝트 조회 메뉴를 이용해주세요!</p>
                                </div>
                            </li>
        
                            <li>
                                <input type="checkbox" id="FAQ-3">
                                <label for="FAQ-3">프로젝트에 가입하고 싶어요.</label>
                                <div>
                                    <p>프로젝트 조회 페이지에서 가입 신청 버튼을 클릭해주세요!</p>
                                    <p>간단한 프로젝트 설명과 함께 가입 신청 버튼이 뜹니다!</p>
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