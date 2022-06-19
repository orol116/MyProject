<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page - profile</title>

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

    <!-- 프로필 사진 수정 -->

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
                    <h1 id="myPage-title">프로필 사진</h1>

                    <form action="profileSubmit" method="post" name="myPage-form"
                    enctype="multipart/form-data" onsubmit="return profileValidate()">
                        <!-- onsubmit으로 input이 변화가 없으면 제출X -->
                            <div class="myPage-detail-row" id="memberEmail">
                                <div>${loginMember.memberEmail}</div> 
                            </div>
                            
                            <div class="profile-image-area">

                                <c:if test="${empty loginMember.profileImage}">
                                    <img src="${contextPath}/resources/images/user.png" id="profile-image">
                                </c:if>
        
                                <c:if test="${!empty loginMember.profileImage}">
                                    <img src="${contextPath}${loginMember.profileImage}" id="profile-image">
                                </c:if>
        
                                <!-- 프로필 이미지 삭제 버튼 -->
                                <span id="delete-image">&times;</span>
                                
                            </div>
        
                            <div class="profile-btn-area">
                                <label for="input-image">이미지 선택</label>
                                <input type="file" name="profileImage" id="input-image" accept="image/*">
        
                                <button type="submit">변경</button>
                            </div>

                            <!-- 삭제 기록용 hidden input -->
                            <input type="hidden" name="delete" id="delete" value="0">
                        
                    </form>
                </div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script>
        const contextPath = "${contextPath}";
    </script>

    <!-- myPage-profile.js 연결 -->
    <script src="${contextPath}/resources/js/member/myPage-profile.js"></script>
</body>
</html>