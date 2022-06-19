<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>구성원 조회 페이지</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/member-list-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/sidebar.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>

    <main>
    
        <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>


        <section id="board-member">

            <div id="member-content">

                <div id="member-count">
                    <h4>클래스 구성원</h4>
                    <span>총 *명</span>
                </div>
    
    
                <div id="member-list">
                    <!-- 
                    <div class="manager-list">
                        <span class="member-status">프로젝트 관리자</span> <br>

                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            
                            <div class="member-status-name"> 
                                <span class="status">관리자</span> <br>
                                <span class="name">관리자</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <span class="member-status">프로젝트 팀원</span> <br>
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                    <div class="member-list">
                        <div class="member-img">
                            <i class="fa-solid fa-circle fa-2x"></i>
                            <div class="member-status-name"> 
                                <span class="status">팀원</span> <br>
                                <span class="name">팀원1</span>
                            </div>
                        </div>
                    </div>

                     -->

    
                </div>

            </div>



        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
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
    

    <script src="${contextPath}/resources/js/member/dropMember-list.js"></script>

</body>

</html>