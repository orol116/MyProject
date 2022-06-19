<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    <header>
        <section>
            <a href="${contextPath}">
                <img src="${contextPath}/resources/images/Achieve_logo.png" id="home-logo">
            </a>
        </section>

        <section>
            <!-- 샘플 이미지 -->

            <c:if test="${!empty sessionScope.loginMember}">
                <img src="${contextPath}/resources/images/header2.png">
            </c:if>
            <c:if test="${empty sessionScope.loginMember}">
                <img src="${contextPath}/resources/images/header.png"  >
                <!-- <img src="${contextPath}/resources/images/header1.png" style="height: 100%;" > -->
            </c:if>

           
            <c:if test="${!empty sessionScope.projectName}">
                
                <article class="project-title">

                    
                </article>
            </c:if>
        </section>
        
        
        <section class="right-header">
            
            <c:if test="${!empty sessionScope.loginMember}">
                
                <a href="${contextPath}/note">
                    <button type="button" id="chat-btn" class="fa-solid fa-bell"></button>
                    
                </a>
            
            </c:if>
            

        </section>

    </header>
