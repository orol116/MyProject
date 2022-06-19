<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 재설정</title>

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">


    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/findId-style.css">


</head>
<body>

    <main>
        <!-- header -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <!-- 아이디, 비번 찾기 -->
        <section class="findIdPw-content">

            <!-- 아이디 찾기 -->
            <section class="find-main">
                <h1 id="find-title">비밀번호 재설정</h1>
                


                <div id="find-container">

                    <div class="find-detail-row" id="memberEmail">
                        <div>아이디(이메일)</div>            
                        <input class="find-detail-input" name="memberEmail" type="text" placeholder="user01@achieve.co.kr">
                    </div>
                    
                    <div class="find-detail-row" id="memberName">
                        <div>이름</div>            
                        <input class="find-detail-input" name="memberName" type="text" placeholder="유저일">
                    </div>

                    <div class="find-detail-row" id="memberBirthday">
                        <div>생년월일</div>            
                        <input class="find-detail-input" name="memberBirthday" type="text" onkeyup="addHypen(this);" placeholder="1992-11-17 ( - 없이 8자리)">
                    </div>


                    <!-- 비밀번호 찾기는 메일 ajax 통한 메일 발송 -->
                    <div class="findPw-button-area">
                        <button type="button" onclick = 'location.href = "${contextPath}/findId"'>뒤로 가기</button>
                        <button id="sendEmailBtn">인증번호 발송(메일)</button>
                    </div>
                </div>


            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <!-- jQuery 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- JS 코드에 필요한 값을 전역 변수로 선언 -->
    <script>
        // 최상위 주소
        const contextPath = "${contextPath}";

    </script>

    <!-- findIdPw.js 연결 -->
    <script src="${contextPath}/resources/js/findIdPw.js"></script>


</body>
</html>