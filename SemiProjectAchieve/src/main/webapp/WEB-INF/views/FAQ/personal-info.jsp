<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>personal-info</title>

    <!-- 헤더, 푸터를 위한 css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <!-- personal-info 페이지를 위한 css -->
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
                           어취브 개인정보처리방침 
                        </div>
        
                        <div name="infor-header" id="start-day">
                            (2022-5-25 시행)
                        </div>
                        <div name="infor-header" class="infor-header">
                            어취브 개인정보처리방침
                        </div>
        
                        <div name="infor-text" class="infor-text">
                            여러분의 개인정보의 안전한 취급은 어취브에게 있어 가장 중요한 일 중 하나입니다. 여러분의 개인정보는 어취브 서비스의 원활한 제공을 위하여 여러분이 동의한 목적과 범위 내에서만 이용됩니다. 법령에 의하거나 여러분이 별도로 동의하지 아니하는 한 어취브가 여러분의 개인정보를 제3자에게 제공하는 일은 결코 없으므로, 안심하셔도 좋습니다. 개인정보처리방침은 항상 공개되어 있으니 지금 당장이 아니더라도 궁금하실 때는 언제든지 읽어보실 수 있습니다. 개인정보처리방침이 바뀐 때에는 여러분이 언제든지 그 내용과 이유를 쉽게 알 수 있도록 공지사항을 통하여 알려 드리겠습니다.
                        </div>
        
                        <div name="infor-header" class="infor-header">
                            개인정보처리방침의 의의
                        </div>
        
                        <div name="infor-text" class="infor-text">
                            어취브는 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 개인정보보호법, 통신비밀보호법, 전기통신사업법 등 정보통신서비스제공자가 준수하여야 하는 대한민국의 관계법령 및 개인정보보호 규정, 가이드라인을 준수하며, 관련법령에 의거한 개인정보처리방침을 정하여 이용자의 권익 보호에 최선을 다하고 있습니다. 본 개인정보처리방침은 어취브가 제공하는 서비스(이하 '서비스'라 함)에 적용됩니다.
                        </div>
        
                        <div name="infor-header" class="infor-header">수집하는 개인정보</div>
                        
                        
                        <div name="infor-text" class="infor-text">
                            어취브는 여러분이 어취브 서비스에 처음 가입할 때 또는 서비스 이용 과정에서 홈페이지 또는 개별 어플리케이션이나 프로그램 등을 통하여 아래와 같은 서비스 제공을 위해 필요한 최소한의 개인정보를 수집하고 있습니다. 어취브는 개별 서비스 이용 과정에서 추가적으로 개인정보 수집이 발생할 경우 수집하는 개인정보에 대하여 별도로 알려 드리며 이에 따라 여러분의 동의를 받고 서비스를 제공하게 됩니다.
                        </div>
        
                        <div name="infor-text" class="infor-text">
                            [회원 가입 시] 이메일 가입 시(이메일주소, 이름, 비밀번호, 사용자 역할)SNS로 가입 시(이용자 고유 식별자, 이메일주소, 이름, 사용자 역할)휴대전화 번호로 가입 시(휴대전화 번호, 이메일주소, 이름, 비밀번호, 사용자 역할)아이디로 가입 시(아이디, 이메일주소, 이름, 비밀번호, 사용자 역할), 단말기 정보 [만 14세 미만일 시][필수] 보호자의 정보(보호자 이름, 아이디) [클래스 이용 시][필수] 학교명, 학년, 반 [선택] 보호자와의 관계 [상품 결제 시][필수] 주문자 정보(이름, 전화번호, 휴대전화 번호, 이메일주소), 배송지 정보(이름, 전화번호, 휴대전화 번호, 주소) [상품 결제 취소/환불 시] [필수] 예금주, 계좌번호, 은행명 [체험 상품 결제 시][필수] 참가자 정보(이름, 학년, 성별 등) [유료서비스 이용 시] 신용카드 결제 시(카드사명, 카드번호(일부) 등)휴대전화 결제 시(이동전화번호, 통신사, 결제 승인번호 등)상품권 이용 시(상품권 번호) [고객센터 이용 시] 문의 등록(이메일주소, 사용자 역할, 학년 등)
                        </div>
        
        
                        <div name="infor-text" class="infor-text">
                            ※ 고객센터로 문의/신고 유형에 따라 이름, 아이디, 학교 등 여러분이 추가로 입력하는 개인정보가 있을 수 있습니다. 이외에 서비스 이용에 따라 스마트폰 등 단말기 주소록 내에 저장된 연락처 정보(제3자의 전화번호, 이름), 쿠키, 서비스 이용 기록(방문일시, IP, 로그인 시간, 사용자 역할, 국가, 도시 등), 단말기 정보(OS, 화면사이즈, 디바이스 아이디, 폰기종, 단말기 모델명 등), CPU 및 랜카드 정보 등 운영 체제 및 하드웨어 환경 정보 등의 정보가 자동으로 생성되어 수집될 수 있습니다.
                        </div>
        
        
                    </div>
        
                </div>

            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>