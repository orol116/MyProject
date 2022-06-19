<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!-- 헤더, 푸터 위한 main.css -->
<link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/myPage-sidebar.css">
<!-- 사이드바 아이콘 사용을 위한 링크 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- 글꼴 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

<!-- 왼쪽 사이드 메뉴 -->
<section class="myPage-sideMenu">
    <div class="menu">
        <ul>
            <li><a href="${contextPath}/member/myPage/profile" class="item"><div>프로필 수정</div></a></li>
            <li><a href="${contextPath}/member/List?memNo=${memNo}&type=1" class="item"><div>내가 쓴 글</div></a></li>
            <li><a href="${contextPath}/member/List?memNo=${memNo}&type=2" class="item"><div>내가 쓴 댓글</div></a></li>
            <li><a href="${contextPath}/member/myPage/changePw" class="item"><div>비밀번호 변경</div></a></li>
            <li><a href="${contextPath}/member/myPage/secession" class="item"><div>회원 탈퇴</div></a></li>
        </ul>
    </div>
</section>