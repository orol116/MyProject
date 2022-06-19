<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <c:set var="projectCode" value="${projectList}" /> --%>



<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Achieve</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/index.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    <script src="https://kit.fontawesome.com/35f111b89d.js" crossorigin="anonymous"></script>
    
    
</head>
<body>
    
    <main>

        <!-- 내부 접근 절대 경로 -->
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

        <!-- 마이페이지 -->
        <section class="myPage-content">

            <section class="content">
                
                <!-- 왼쪽 회원 사이드 메뉴 -->
                <section class="content-1">

                    <div class="side-content">
                        <c:choose>
                            <c:when test="${empty sessionScope.loginMember}">

                                <form action="member/login" method="post" name="login-form" onsubmit="return loginValidate()">
                                
                                    <!-- 아이디(이메일)/비밀번호/로그인 -->
                                    <fieldset id="id-pw-area">
                                        <section>
                                            <input type="text" name="inputEmail" placeholder="아이디(이메일)" value="${cookie.saveId.value}">
                                            <input type="password" name="inputPw" placeholder="비밀번호">
                                        </section>
                                        
                                        <section>
                                            
                                            <button type="submit">로그인</button>
                                            
                                        </section>
                                    </fieldset>

                                    <!-- 쿠키에 saveId가 있는 경우 -->
                                    <c:if test="${!empty cookie.saveId.value}">
                                        
                                        <!-- chk변수 생성(page scope) -->
                                        <c:set var="chk" value="checked"/>
                                    
                                    </c:if>
                                                            
                                    <label for="saveId">
                                        <input type="checkbox" name="saveId" ${chk} id="saveId"> 아이디 저장
                                    </label>
                        
                                    <!-- 회원가입/ID,PW찾기 -->
                                    <article id="signup-find-area">
                                        
                                        <button type="button"><a href="${contextPath}/member/signUp" id="main-singUp">회원가입</a></button>
                                        <button type="button"><a href="${contextPath}/findId" id="main-find">ID/PW 찾기</a></button>
                                        
                                    </article>
                                </form>
                                        
                            </c:when>

                            <c:otherwise>
                                <article class="login-area">
                                
                                    <!-- 회원 프로필 이미지 -->
                                    <a href="${contextPath}/member/myPage/profile">
                                        <!-- 프로필 이미지 변경하는 페이지 생기면 그쪽으로 -->

                                        <c:if test="${empty loginMember.profileImage}">
                                            <img src="${contextPath}/resources/images/user.png" id="member-profile">
                                        </c:if>

                                        <c:if test="${!empty loginMember.profileImage}">
                                            <img src="${contextPath}${loginMember.profileImage}" id="member-profile">
                                        </c:if>

                                    </a>
                                
                                    <!-- 회원 정보 + 로그아웃 버튼 -->
                                    <div class="my-info">
                                        <div>
                                            <a href="${contextPath}/member/myPage/info" id="nickname">${loginMember.memberNickname}</a>
                                            
                                            <a href="member/logout" id="logout-btn">&times;</a>
                                        
                                        </div>
                                        
                                        <p>
                                            ${loginMember.memberEmail}
                                        </p>
                                        
                                    </div>
                                </article>            
                    
                                <!-- 마이페이지 -->
                                <article id="signup-find-area">
                                    
                                    <button id="myPageBtn"><a href="${contextPath}/member/myPage/info">마이페이지</a></button>
                                    
                                </article>

                                <article id="main-project-area">
                                    
                                    <button type="button"><a href="${contextPath}/project/PJCreate">프로젝트 만들기</a></button><br>
                                    <button type="button"><a href="${contextPath}/project/PJ/PJSearch/list?cp=1">프로젝트 조회</a></button>
            
                                </article>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </section>






                <!-- 오른쪽 프로젝트 추천 부분 -->
                <section class="content-2">

                    <c:choose>
                        
                        <c:when test="${empty sessionScope.loginMember}">
                            <!-- 하단 어취브 홍보 부분 -->
                            <img src="${contextPath}/resources/images/main(2).png" id="main-lobby">
    
                        </c:when>

                        <c:otherwise>
                            
                            <c:forEach var="project" items="${projectList}">
                               
                                    <div class="project-join">
                                        <h2><a href="${contextPath}/board/main?type=1&projectNo=${project.projectNo}&cp=1">${project.projectName}</a></h2>
                                        <h4>${project.projectIntro}</h4>
                                      
                                    </div>
                                    <input type="hidden" name="projectNo" value="${project.projectNo}">

                                
                            </c:forEach>

                        </c:otherwise>

                    </c:choose>
                    

                    
                </section>

            </section>


            



            
        </section>

    </main>

        
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <!-- jQuery Library -->
    <script    src="https://code.jquery.com/jquery-3.6.0.min.js"    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="    crossorigin="anonymous"></script>

    <!-- main.js 연결 절대경로 -->
	<script src="${contextPath}/resources/js/main.js"></script>

</body>
</html>