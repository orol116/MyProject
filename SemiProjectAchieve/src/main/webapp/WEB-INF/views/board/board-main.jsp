<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />

<!-- 프로젝트 리스트에 있는 프로젝트 객체를 불러오고 싶음... -->
<c:forEach var="p" items="${projectList}">
    <c:if test="${param.projectNo == p.projectNo}">
        <c:set var="project" value="${p}"/>
    </c:if>
</c:forEach>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${boardName}</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board_css/sideMenu.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board_css/board-main-style.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Hahmlet:wght@100&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    

</head>
<body>

    <main>

		<jsp:include page="/WEB-INF/views/common/header.jsp" />

        <div class="div-sec">

            <!-- 왼쪽 사이드 메뉴 -->
            <section class="myPage-sideMenu">
                <div id="sideMenu-list">

                    <div class="sideMenu-list-row project-main"><a href="${contextPath}/board/main?type=1&projectNo=${param.projectNo}">${project.projectName}</a></div>

                    <c:forEach var="boardType" items="${boardTypeList}">
                        <c:if test="${boardType.boardCode != 99}">
                            <div class="sideMenu-list-row" id="project"><a href="${contextPath}/board/main?type=${boardType.boardCode}&projectNo=${param.projectNo}&cp=1">${boardType.boardName}</a></div>
                        </c:if>
                    </c:forEach>

                    <br>
                    <div class="sideMenu-list-row default-list"><a href="${contextPath}/member/List?memNo=${memberNo}&type=1">내가 쓴 글</a></div>
                    <div class="sideMenu-list-row default-list"><a href="${contextPath}/member/List?memNo=${memberNo}&type=2">내가 쓴 댓글</a></div>
                    <div class="sideMenu-list-row default-list"><a href="${contextPath}/member/myPage/info">마이페이지</a></div>

                    <div class="sideMenu-list-row default-list"><a href="${contextPath}/member/list?projectNo=${param.projectNo}">프로젝트 구성원</a></div>

                    <br>
                    <div class="sideMenu-list-row default-list" id="manager"><a href="${contextPath}/project/PJSettings?projectNo=${param.projectNo}">프로젝트 관리</a></div>
                </div>
            </section>

            <c:if test="${!empty param.key}">
                <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
            </c:if>

            <section class="board-list">
                <c:choose>

                    <%-- 프로젝트에 가입한 사람이 페이지에 들어왔을 때 --%>
                    <c:when test="${!empty project}">

                        <div id="board-area">
        
                            <h1 class="board-name">${boardName}</h1>
        
                            <c:if test="${!empty param.key}">
                                <h3 style="margin-left: 30px;"> "${param.query}" 검색 결과</h3>
                            </c:if>
        
        
                            <div class="btn-area">
                                <c:if test="${param.type != 1 && param.type != 2}">
                                    <button id="insertBtn" onclick="location.href='write?mode=insert&type=${param.type}&projectNo=${param.projectNo}&cp=${param.cp}'">글쓰기</button>
                                </c:if>
                            </div>
        
                            <div class="list-wrapper">
                                <table class="list-table">
                
                                    <thead>
                                        <tr>
                                            <th>글 번호</th>
                                            <th style="text-align: center;">제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>조회수</th>
                                        </tr>
                                    </thead>
                
                                    <tbody>
                                        <c:choose>
                                            <c:when test="${empty boardList}">
                                                <tr>
                                                    <th colspan="5">게시글이 존재하지 않습니다.</th>
                                                </tr>
                                            </c:when>
        
                                            <c:otherwise>
                                                <c:forEach var="board" items="${boardList}">
                                                    <tr>
                                                        <td>${board.boardNo}</td>
                                                        <td>
                                                            <a href="detail?no=${board.boardNo}&cp=${pagination.currentPage}&type=${param.type}&projectNo=${param.projectNo}${sURL}">${board.boardTitle}</a>
                                                        </td>
                                                        <td>${board.memberNickname}</td>
                                                        <td>${board.createDate}</td>
                                                        <td>${board.readCount}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </tbody>
                                </table>
                            </div>
                
                
        
                            <div class="pagination-area">
        
                                <c:set var="url" value="main?type=${param.type}&projectNo=${param.projectNo}&cp="/>
        
                                <ul class="pagination">
        
                                    <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
                                    <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>
                
                                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
        
                                        <c:choose>
                                            <c:when test="${i == pagination.currentPage}">
                                                <li><a class="current">${i}</a></li>
                                            </c:when>
        
                                            <c:otherwise>
                                                <li><a href="${url}${i}${sURL}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
        
                                    </c:forEach>
                
                                    <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>
                                    <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
                                </ul>
                            </div>
                        </div>


                    </c:when>

                    <%-- 가입하지 않은 사람이 프로젝트 페이지에 들어왔을 때 --%>
                    <c:otherwise>

                        <div class="project-signup-content">
                            <div class="project-signup-title">
                                <h1>${projectName}</h1>
                            </div>
    
                            <div class="project-signup-intro">
                                <p>
                                ${projectIntro}
                                </p>
                            </div>
    
                            <div class="project-signup-btn">
                                <button id="cancelBtn" type="button" onclick = 'location.href = "${contextPath}/project/PJ/PJSearch/list" '>취소</button>
                                <button id="approveBtn" type="button" >가입신청</button>
                            </div>
    
                        </div>

                    </c:otherwise>


                </c:choose>
                

    
    
                <form action="#" method="get" id="boardSearch">
                    <input type="hidden" name="type" value="${param.type}">
                    <input type="hidden" name="projectNo" value="${param.projectNo}">
    
                    <select name="key" id="search-key">
                        <option value="t">제목</option>
                        <option value="c">내용</option>
                        <option value="tc">제목 + 내용</option>
                        <option value="w">작성자</option>
                    </select>
    
                    <input type="text" name="query" id="search-query" placeholder="검색어를 입력해주세요.">
    
                    <button>검색</button>
    
                </form>
    
            </section>

        </div>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script> 
        // 댓글 관련 JS 코드에 필요한 값을 전역 변수로 선언

        // jsp 파일 : html, css, js, el, jstl 사용가능
        // js 파일  : js 만 사용 가능. 
        // -> 그렇기에 el을 사용하려면 여기 jsp에서 사용해야된다. 

        //** JS 코드에서 EL/JSTl을 작성하게 된다면 반드시 ""를 양쪽에 추가해야된다.**

        // 최상위 주소
        const contextPath = "${contextPath}"; 

        // 게시글 번호
        const boardNo = "${detail.boardNo}"; // "500"

        // 로그인한 회원 번호
        const loginMemberNo = "${loginMember.memberNo}";
        // -> 로그인 되어있으면 : "10"
        // -> 로그인 안되어있으면 : ""; (빈문자열)

        const projectNo = "${param.projectNo}";
        

    </script>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/js/board/board.js"></script>
    
    
</body>
</html>