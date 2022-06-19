<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>

    <!-- 헤더, 푸터 위한 main.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-sidebar.css">

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    <!-- 사이드바 아이콘 사용을 위한 링크 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>

    <!-- 마이페이지 첫 화면, 개인 정보 보여줌 & 회원 정보 수정 -->

    <main>
        <!-- header -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>


        <!-- 마이페이지 -->
        <section class="myPage-content">

            <!-- 왼쪽 사이드 메뉴 -->
            <jsp:include page="/WEB-INF/views/member/sideMenu.jsp" />

            <!-- 마이페이지 - 메인 내용 -->
            <section class="myPage-main">
                <div id="myPage-detail">
                    <h1 id="myPage-title">내 정보</h1>

                    <form action="update" method="post" name="myPage-form" onsubmit="return infoUpdateValidate()">
                        <!-- onsubmit으로 input이 변화가 없으면 제출X -->
                            <div class="myPage-detail-row" id="memberEmail">
                                <div>${loginMember.memberEmail}</div> 
                            </div>
                            
                            <div class="myPage-detail-row" id="memberName">
                                <div>이름</div>            
                                <input class="myPage-detail-input" type="text" name="memberName" placeholder="${loginMember.memberName}">
                            </div>

                            <div class="myPage-detail-row" id="memberNickname">
                                <div>닉네임</div>            
                                <input class="myPage-detail-input" type="text" name="memberNickname" placeholder="${loginMember.memberNickname}">
                            </div>

                            <div class="myPage-detail-row" id="memberTel">
                                <div>핸드폰 번호</div>            
                                <input class="myPage-detail-input" type="text" name="memberTel" placeholder="${loginMember.memberTel}">
                            </div>

                            <div class="myPage-detail-row">
                                <button id="modify">회원 정보 수정</button>
                            </div>

                    </form>
                </div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <!-- myPage.js 연결 -->
    <script src="${contextPath}/resources/js/member/myPage-info.js"></script>
</body>
</html>