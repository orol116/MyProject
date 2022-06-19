<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page - secession</title>

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
                    <h1 id="myPage-title">회원 탈퇴</h1>

                    <form action="secessionSubmit" method="post" name="myPage-form" onsubmit="return secessionValidate()">
                        <!-- onsubmit으로 input이 변화가 없으면 제출X -->
                            <div class="myPage-detail-row" id="memberEmail">
                                <div>${loginMember.memberEmail}</div> 
                            </div>
                            
                            <div class="myPage-detail-row" id="memberName">
                                <div>${loginMember.memberName}</div>            
                                
                            </div>

                            <div class="myPage-detail-row" id="memberNickname">
                                <div>${loginMember.memberNickname}</div>            
                                
                            </div>

                            <div class="myPage-detail-row" id="memberTel">
                                <div>${loginMember.memberTel}</div>            
                                
                            </div>
                            
                            <div class="secession-guide">
                                <ul>
                                    <li class="secession-guide-detail">이 계정의 회원정보 및 서비스 이용기록이 삭제됩니다.</li>
                                    <li class="secession-guide-detail">작성한 게시물과 댓글은 삭제되지 않습니다. 삭제를 원하는 글은 계정 삭제 전 삭제하시기 바랍니다.</li>
                                    <li class="secession-guide-detail">클래스 관리자는 계정을 삭제할 수 없습니다. 관리자 권한 양도 또는 클래스 폐쇄 후 탈퇴하시기 바랍니다.</li>
                                    <li class="secession-guide-detail">향후 계정 정보나 이용 내역을 확인할 수 없습니다.</li>
                                    <li class="secession-guide-detail">계정 삭제 후에는 복구가 불가능합니다.</li>
                                </ul>

                            </div>

                            <!-- 밑줄용 -->
                            <div id="blank"></div>
                            <div id="chkbox">
                                <input type="checkbox" id="secession-agree">
                                <span>모두 확인했습니다.</span>
                            </div>

                            <div class="secession-btn-area">
                                <button type="reset" id="secession-cancel-btn">취소</button>
                                <button id="secession-confirm-btn">회원 탈퇴</button>
                            </div>

                    </form>
                </div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <!-- myPage.js 연결 -->
    <script src="${contextPath}/resources/js/member/myPage-secession.js"></script>
</body>
</html>