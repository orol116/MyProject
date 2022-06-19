<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>project-create</title>

    <!-- 헤더, 푸터 위한 main.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/project-create.css">


    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">
  

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>


</head>
<body>
    <main>
        
        <!-- 헤더 -->
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <!-- 프로젝트 생성 페이지 -->
        <section class="classPage-content">
            
            <form action="PJCreate" method="POST" name="PJCreate-form" onsubmit="return createVD()" >




                <section class="classPage-inside">
                    
                    <!-- 프로젝트 관련 정보 입력(왼쪽) -->
                    <section class="classPage-left">
                        <h1 class="left-text" style="margin-bottom : 30px;">프로젝트 이름을 지어주세요.</h1> 
                        <h3 class="left-text">프로젝트 이름</h3>
                        
                        <div class="page-left1">
                            <input type="text" id="projectName" maxlength="11" name="projectName"
                            placeholder="프로젝트 이름 입력" size="30" required>

                            <button id="name-double-check" type="button">중복확인</button>
                        
                        </div>

                        <div class="page-left3">
                            <div class="quota">
                                <h3 class="left-text" style="margin:10px 0;">정원</h3>
                                <input type="number" id="projectQuota" name="projectQuota"
                                    placeholder="0" max="30" min="1" required>
                            </div>
                        </div>


                        <h3 class="left-text">소개글</h3>
                        <div class="page-left">
                            <textarea id="projectIntro" rows="10" cols="45"  name="projectIntro"
                            style="resize: none;"></textarea>
                        </div>
                
                    </section>
                

                     <!-- 프로젝트 공개 여부 -->
                    <section class="classPage-right">

                        <div class="rightPage">

                            <div>
                                <input type="radio" name="openStatus" id="secret-class" value="N" required>
                                <label for="secret-class">비공개 프로젝트</label><br>
                                <p id="select-text" class="select-text">초대를 통해서만 가입할 수 있습니다.</p>
                            </div>

                            <div>
                                <input type="radio" name="openStatus" id="private-class" value="P">
                                <label for="private-class">프로젝트 이름만 공개</label><br>
                                <p id="select-text">검색으로 소개를 볼 수 있지만,</p>
                                <p id="select-text" class="select-text">게시글은 맴버만 볼 수 있습니다.</p>
                            </div>

                            <div>
                                <input type="radio" name="openStatus" id="public-class" value="Y">
                                <label for="public-class">공개 프로젝트</label><br>
                                <p id="select-text"> 누구든 프로젝트를 검색해 찾을 수 있고 </p>
                                <p id="select-text">프로젝트 소개와 게시글을 볼 수 있습니다.</p>
                            </div>

                        </div>  

                    </section>

                </section>
                
                <div class="submit-btn-area">
                    <button id="createBtn" name="createBtn" type="submit">제출</button>
                </div>


            </form>     

        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
    <script src="../resources/js/project-create.js"></script>

</body>
</html>