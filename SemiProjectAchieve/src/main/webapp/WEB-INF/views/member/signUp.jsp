<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/signUp-style.css">

    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">
    

    <script src="https://kit.fontawesome.com/51bf4ad8d5.js" crossorigin="anonymous"></script>

</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
        <h1>회원가입</h1>
        <section class="signUp-content">
            <form action="signUp" method="POST" name="signUp-form" onsubmit="return signUpValidate()">

                <label for="memberEmail">
                    <span class="required">*</span> 아이디
                </label>
                
                <div class="signUp-input-area">
                    <input type="text" id="memberEmail" name="memberEmail"
                            placeholder="이메일 형식으로 작성해주세요." maxlength="30"
                            autocomplete="off" required>
                </div>

                <span class="signUp-message" id="idMessage"></span>


                <label for="memberPw">
                    <span class="required">*</span> 비밀번호
                </label>
                
                <div class="signUp-input-area">
                    <input type="password" id="memberPw" name="memberPw"
                            placeholder="비밀번호" maxlength="30">
                </div>


                <label for="memberPwConfirm">
                    <span class="required">*</span> 비밀번호 확인
                </label>
                
                <div class="signUp-input-area">
                    <input type="password" id="memberPwConfirm" name="memberPwConfirm"
                            placeholder="비밀번호 확인" maxlength="30">
                </div>

                <span class="signUp-message error" id="pwMessage"></span>




                <label for="memberName">
                    <span class="required">*</span> 이름
                </label>
                
                <div class="signUp-input-area">
                    <input type="text" id="memberName" name="memberName"
                            placeholder="이름" maxlength="10">
                </div>

                <span class="signUp-message confirm" id="nameMessage"></span>



                <label for="memberNickname">
                    <span class="required">*</span> 닉네임
                </label>
                
                <div class="signUp-input-area">
                    <input type="text" id="memberNickname" name="memberNickname"
                            placeholder="닉네임" maxlength="10">
                </div>

                <span class="signUp-message confirm" id="nicknameMessage"></span>


                
                
                <label for="memberBirth">
                    <span class="required">*</span>생년월일
                </label>
                
                <div class="signUp-input-area memberBirth">
                    <input type="number" id="memberBirth" name="memberBirth"
                    placeholder="년(4자)" maxlength="4">

                    <input type="number"  name="memberBirth"
                    placeholder="월" maxlength="4">

                    <input type="number" name="memberBirth"
                    placeholder="일" maxlength="2">
                </div>
                <span class="signUp-message" id="birthMessage"></span>


                
                
                <label for="memberTel">
                    <span class="required"></span>휴대전화(선택)
                </label>
                
                <div class="signUp-input-area">
                    <input type="text" id="memberTel" name="memberTel"
                            placeholder="(- 없이 숫자만 입력)" maxlength="11">

                </div>
                <span class="signUp-message" id="telMessage">
                   </span>



                <button type="submit" id="signUp-btn">가입하기</button>

            </form>
            
        </section>

    </main>
    

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/member/signUp.js"></script>
</body>

</html>