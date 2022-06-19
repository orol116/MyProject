<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    <footer>
        <p>
            <article id="footer">
                <a href="${contextPath}/FAQ/firstFAQ">FAQ</a>
                <span> | </span>                
                <a href="${contextPath}/FAQ/terms/conditions">이용약관</a>
                <span> | </span>
                <a href="${contextPath}/FAQ/personal/info">개인정보처리방침</a>
            </article>
            
            <div>
                Copyright &copy; KH Information Educational Institute A-class DevTeam2
            </div>
        </p>
    </footer>

	<%-- session에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
	<c:if test="${ !empty sessionScope.message }">
		<script>
			alert("${message}");
			// EL 작성 시 scope를 지정하지 않으면
			// page -> request -> session -> application 순서로 검색하여
			// 일치하는 속성이 있으면 출력
		</script>
	
		<%-- message 1회 출력 후 session에서 제거 --%>
		<c:remove var="message" scope="session"/>
	</c:if>