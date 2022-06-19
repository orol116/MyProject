<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Achieve</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/note.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">
    
    <script src="https://kit.fontawesome.com/35f111b89d.js" crossorigin="anonymous"></script>
    
    
</head>
<body>
    
    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />


        <section class="content">                

            <div id="notice">알림</div>
            
            
            <div id="notice-area">                
                        
                <!-- 쪽지는 list로 확인만 가능하게, 링크 연결 X -->
                <c:choose>
                    <c:when test="${empty nList}">
                        <div class="notice-list noNote">                                
                            <p>알림이 없습니다</p>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <c:forEach var="note" items="${nList}">

                            <div class="notice-list" id="noteList">
        
                                <div>${note.sender}</div>
                                <p>${note.noteContent}</p>
        
                            </div>
        
                        </c:forEach>
                    </c:otherwise>

                </c:choose>
    
            </div>                

        </section>


    </main>
        
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script>

        //로그인한 회원 번호
        const memberNo = "${loginMember.memberNo}";
    </script>

    <!-- jQuery Library 추가 -->
    <script    src="https://code.jquery.com/jquery-3.6.0.min.js"    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="    crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/note.js"></script>


</body>
</html>