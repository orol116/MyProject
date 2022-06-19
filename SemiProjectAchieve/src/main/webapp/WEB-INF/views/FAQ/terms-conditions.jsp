<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>terms-conditions</title>

    <!-- 헤더, 푸터를 위한 css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/personal-info.css">
    
    <!-- 사이드바 css 포함 -->
    <link rel="stylesheet" href="${contextPath}/resources/css/FAQ.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100&family=Song+Myung&display=swap" rel="stylesheet">

</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
        
        <section class="content">

            <section class="main">

                <div id="body">
                    <div id="infor-main">
    
                        <div name="infor-header" id="infor-first">
                            이용약관 
                        </div>
    
                        <div name="infor-header" id="start-day">
                            (2022-5-25 시행)
                        </div>
                        <div name="infor-header" class="infor-header">
                            이용자 여러분 반갑습니다!
                        </div>
    
                        <div name="infor-text" class="infor-text">
                            서비스를 이용하시면서 필요하시거나 궁금해하실 기본적인 서비스 이용 관련 정보를 약관에 담아 안내드립니다. 약관을 통해 achieve(이하 ‘회사’)와 회원(이하 ‘회원’ 또는 ‘여러분’)과의 권리, 의무 및 책임사항, 기타 사항을 확인하실 수 있으며 회사는 이 약관의 내용을 여러분이 쉽게 확인할 수 있도록 서비스 내 게시합니다. 회사는 안정적인 서비스를 지속적으로 제공하기 위해 최선을 다해 노력해 나갈 것이며 안내드리는 서비스 약관을 통해 저희 서비스가 여러분께 조금 더 가까이 다가갈 수 있기를 기대합니다.
    
                        </div>
    
                        <div name="infor-header" class="infor-header">
                            약관에 사용되는 용어의 정의와 해석은 다음과 같습니다.
                        </div>
    
                        <div name="infor-text" class="infor-text">
                            서비스는 단말기(PC, TV, 휴대형단말기 등의 각종 유무선 장치를 포함)와 상관없이 이용할 수 있는 모든 서비스를 의미합니다. 여러분은 회사의 서비스에 접속하여 이 약관에 따라 회사와 이용계약을 체결하고 회사가 제공하는 서비스를 이용하는 고객을 말합니다. 콘텐츠는 여러분이 서비스 이용 시 서비스 상에 게시한 부호 · 문자 · 음성 · 음향 · 화상 · 동영상 등의 정보 형태의 글, 사진, 동영상 및 각종 파일과 링크 등을 의미합니다.
                        </div>
    
                        <div name="infor-header" class="infor-header">여러분은 언제든 개인정보를 변경할 수 있습니다.</div>
                        
                        
                        <div name="infor-text" class="infor-text">
                            여러분은 프로필 설정 화면을 통하여 언제든지 본인의 개인정보를 열람하고 수정할 수 있으며 회원가입신청 시 기재한 사항이 변경되었을 경우 프로필을 수정하여 회사에 그 변경사항을 알려야 합니다. 특히, 휴대전화 번호를 변경하거나 비활성화할 경우에는 48시간 내에 회원 계정 정보를 업데이트해야 합니다. 그렇지 않은 경우 여러분의 기존 휴대전화 번호를 습득한 자에게 여러분의 콘텐츠가 전송될 수 있습니다. 변경사항을 회사에 알리지 않아 발생한 불이익에 대해 회사는 책임지지 않습니다.
                        </div>
    
    
    
    
    
                    </div>
    
                </div>
            </section>
            
        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>