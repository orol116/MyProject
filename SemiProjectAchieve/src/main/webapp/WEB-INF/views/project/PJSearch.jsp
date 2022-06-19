<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="pagination" value="${map.pagination}" />
<c:set var="projectList" value="${map.projectList}" />

<c:set var = "cp" value="${map.cp}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>프로젝트 조회</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/index.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/project-search.css">

    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    <script src="https://kit.fontawesome.com/35f111b89d.js" crossorigin="anonymous"></script>

</head>
<body>

    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />
        
       
        
    
            <!-- 우측 프로젝트 목록 검색부분-->
            <section class="content-2">
                
                <div id="project-area">
            
                    <h1 class="project-name">프로젝트 조회</h1>
                    
                    <div class="btn-area">
                       
                    </div>

                    <div class="list-wrapper">
                        <table class="list-table">
        
                            <thead>
                                <tr>                                    
                                    <th>프로젝트</th>
                                    <th>관리자</th>
                                    <th>정원</th>
                                    <th>프로젝트 설명</th>
                                    <th>가입</th>
                                </tr>
                            </thead>
        
                            <tbody>            
                                
                                <c:choose>
                                    <c:when test="${empty projectList}">
                                        <!-- 목록 조회 결과가 빈 경우 -->
                                        <tr>
                                            <th colspan="5">프로젝트가 존재하지 않습니다.</th>
                                        </tr>
                                    </c:when>

                                    <c:otherwise>
                                        <!-- 목록 조회 결과가 비어있지 않다면 -->

                                        <c:forEach var="project" items="${projectList}">
                                            <tr>                                    
                                                <td>

                                                    <%-- 가입이 되어있지 않으면 프로젝트의 메인페이지로 넘어가기 --%>
                                                    <c:if test="${project.participateStatus == 0}">
                                                        <a href="${contextPath}/board/main?type=1&projectNo=${project.projectNo}">${project.projectName}</a>
                                                    </c:if>
                                                    <%-- 가입이 되어있으면 프로젝트의 최신글 페이지 --%>
                                                    <c:if test="${project.participateStatus == 1}">
                                                        <a href="${contextPath}/board/main?type=1&projectNo=${project.projectNo}&cp=1">${project.projectName}</a>
                                                    </c:if>
                                                </td>
                                                <td>${project.projectManagerNickname}</td>
                                                <td>${project.projectQuota}</td>
                                                <td>${project.projectIntro}</td>
                                                <td>
                                                    <c:if test="${project.participateStatus == 0}">
                                                        <div class="btn-area">
                                                            <button id="requestBtn"><a href="${contextPath}/board/main?type=1&projectNo=${project.projectNo}">가입 신청</a></button>
                                                        </div>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </c:otherwise>
                                    
                                </c:choose>


                            </tbody>
                        </table>
                    </div>
        
        

                    <div class="pagination-area">

                        <c:set var="url" value="list?cp="/>
                
                        <ul class="pagination">
                        
                            <li><a href="${url}1">&lt;&lt;</a></li>
        
                            <li><a href="${url}${pagination.prevPage}">&lt;</a></li>
        
                            <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
        
                                <c:choose>
                                    <c:when test="${i == pagination.currentPage}">
                                        <li><a class="current">${i}</a></li>
                                    </c:when>
        
                                    <c:otherwise>
                                        <li><a href="${url}${i}">${i}</a></li>        
                                    </c:otherwise>
                                </c:choose>
        
                            </c:forEach>
                            
                            <li><a href="${url}${pagination.nextPage}">&gt;</a></li>
        
                            <li><a href="${url}${pagination.maxPage}">&gt;&gt;</a></li>
        
                        </ul>
                    </div>

                            
                </div>  


                <!-- 폼태그 검색 제출 -->
                <form action="${contextPath}/project/PJ/PJSearch/list" method="get" id="projectSearch" onsubmit="return searchValidate()">

                    <select name="key">
                        <option value="t">프로젝트명</option>
                        <option value="c">관리자</option>
                    </select>

                    <input type="text" name="query" id="search-query" placeholder="검색어를 입력해주세요.">

                    <button>검색</button>

                </form>

            </section>
        

        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

     <!-- jQuery Library -->
     <script    src="https://code.jquery.com/jquery-3.6.0.min.js"    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="    crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/project-search.js"></script>
    
</body>
</html>