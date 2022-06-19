<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board_css/board-write-style.css">

    <script src="${contextPath}/resources/js/board/ckeditor/ckeditor.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

</head>
<body>

    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <form action="#" enctype="multipart/form-data" method="POST" class="board-write"
            onsubmit="return writeValidate()">

            <div class="title-area">
                <!-- 제목 -->
                <h1 class="board-title">
                    <input type="text" name="boardTitle" placeholder="제목을 입력해주세요." value="${detail.boardTitle}">
                </h1>
            </div>

            <div class="content-area">
                
                <div class="add-content">

                    <label for="img0">첨부파일<img class="preview" src="${img0}"></label>
                    <input type="file" class="inputImage" id="img0" name="0">

                    <span id="attachName"></span><span id="attachSize"></span><span class="deleteAttach">&times;</span>

                </div>


                <!-- 내용 -->
                <div class="board-content">
                    <textarea name="boardContent" id="boardContent">${detail.boardContent}</textarea>
                    <script>
                    CKEDITOR.replace('boardContent', {height: 600});
                    </script>
                </div>

                <div class="bottom-area">

                        <select name="board-type" id="board-type">
                            <option value="-1">게시판 선택</option>
                            
                                <c:choose>
                                    <c:when  test="${param.type != 2}">

                                        <c:forEach var="boardType" items="${boardTypeList}">
                                            <c:if test="${boardType.boardCode != 1 && boardType.boardCode != 2}">
                                                <option value="${boardType.boardCode}" id="boardOption">${boardType.boardName}</option>
                                            </c:if>
                                        </c:forEach>

                                    </c:when>

                                    <c:otherwise>
                                        <option value="2" id="boardOption" selected>공지사항</option>
                                    </c:otherwise>

                                </c:choose>
                                
                        </select>

                    <!-- 버튼 영역 -->
                    <div class="board-btn-area">
                        <button type="submit" id="writebtn">등록</button>
                        <button type="button" id="goToListBtn" onc>목록으로</button>
                    </div>

                </div>

                <!-- 숨겨진 값(hidden) -->
                <!-- 동작 구분 -->
                <input type="hidden" name="mode" value="${param.mode}">

                <!-- type은 게시판 구분 -->
                <input type="hidden" name="type" value="${param.type}">

                <!-- 게시글 번호 -->
                <input type="hidden" name="no" value="${param.no}">
                
                <!-- 현재 페이지 -->
                <input type="hidden" name="cp" value="${param.cp}">

                <input type="hidden" name="projectNo" value="${param.projectNo}">

            </div>

            <input type="hidden" name="deleteList" id="deleteList" value="">
        </form>


        
    </main>

    <script src="${contextPath}/resources/js/board/board.js"></script>
    
</body>
</html>