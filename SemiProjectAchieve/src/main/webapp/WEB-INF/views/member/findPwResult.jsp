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
                

                <form action="findPwResult" method="post">
                    <div id="find-container">
    
                        <div class="find-detail-row" id="certiChar">
                            <div>보안문자 입력</div>            
                            <input class="find-detail-input" name="certiChar" type="text" placeholder="보안문자 8자리">
                        </div>
                        
                        <div class="find-detail-row" id="memberPw">
                            <div>새로운 비밀번호 입력</div>            
                            <input class="find-detail-input" name="memberPw" type="password">
                        </div>
    
                        <div class="find-detail-row" id="memberPwCheck">
                            <div>새로운 비밀번호 확인</div>            
                            <input class="find-detail-input" name="memberPwCheck" type="password">
                        </div>
    
    
                        <div class="findPw-button-area">
                            <button id="setNewPwBtn" onclick="">비밀번호 재설정</button>
                        </div>
                    </div>

                </form>


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