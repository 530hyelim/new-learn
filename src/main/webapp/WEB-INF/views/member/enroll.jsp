<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 - 정보입력</title>

<%-- CSS 파일 경로 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/enroll.css">
</head>
<body>

    <h1 class="page-title">회원가입</h1>

    <div class="progress-indicator">
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">약관동의</div>
        </div>
        <div class="step-line"></div>
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">정보입력</div>
        </div>
        <div class="step-line"></div>
        <div class="step">
            <div class="step-circle"></div>
            <div class="step-label">가입완료</div>
        </div>
    </div>

    <div class="enroll-container">
        <div class="form-header">
            <h2>회원가입</h2>
            <p>회원이 되어 다양한 경험을 해보세요!</p>
        </div>
        
        <form id="enrollForm" action="${pageContext.request.contextPath}/member/enroll" method="post">
            
            <!-- 아이디 -->
            <div class="form-group">
                <label for="userId">아이디</label>
                <div class="input-with-btn">
                    <input type="text" id="userId" name="userId" placeholder="아이디 7~15자">
                    <button type="button" id="idCheckBtn" class="btn-check">중복 확인</button>
                </div>
                <small id="id-feedback" class="form-text"></small>
            </div>

            <!-- 비밀번호 -->
            <div class="form-group">
                <label for="userPw">비밀번호</label>
                <input type="password" id="userPw" name="userPw" placeholder="비밀번호, 문자, 숫자, 특수문자 포함 7~20자">
            </div>

            <!-- 비밀번호 확인 -->
            <div class="form-group">
                <label for="userPwConfirm">비밀번호 확인</label>
                <input type="password" id="userPwConfirm" name="userPwConfirm">
            </div>

            <!-- 이름 -->
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" id="userName" name="userName">
            </div>

            <!-- 주민등록번호 -->
            <div class="form-group">
                <label>주민등록번호</label>
                <div class="ssn-group">
                    <input type="text" name="ssn1" maxlength="6">
                    <span>-</span>
                    <input type="password" name="ssn2" maxlength="7">
                </div>
            </div>

            <!-- 전화번호 -->
            <div class="form-group">
                <label for="phone">전화번호</label>
                <input type="text" id="phone" name="phone" placeholder="휴대폰 번호 입력('-'제외 11자리 입력)">
            </div>

            <!-- 이메일 -->
            <div class="form-group">
                <label>이메일 주소</label>
                <div class="email-group">
                    <input type="text" name="emailId">
                    <span>@</span>
                    <input type="text" name="emailDomain">
                    <button type="button" class="btn-send">인증코드 발송</button>
                </div>
            </div>

            <!-- 인증코드 -->
            <div class="form-group">
                <label>인증코드</label>
                <div class="input-with-btn">
                    <input type="text" name="emailCert">
                    <button type="button" class="btn-check">확인</button>
                </div>
            </div>

            <!-- 다음 버튼 -->
            <div class="form-footer">
                <button type="submit" class="btn-next">다음</button>
            </div>
        </form>
    </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script>
   
    $(document).ready(function(){

        const $idFeedback = $('#id-feedback');
        const $userId = $('#userId');

        // 1. 아이디 글자 수 실시간 검사
        $userId.on('keyup', function(){
            const userId = $userId.val();

            // 글자 수 검사 (7자 미만 또는 15자 초과일경우 NG)
            if (userId.length > 0 && (userId.length < 7 || userId.length > 15)) {
                $idFeedback.text('아이디는 7~15자 사이로 입력해주세요.');
                $idFeedback.css('color', 'red');
            } else {
                $idFeedback.text('');
            }
        });


        // 2. '중복 확인' 버튼 클릭 이벤트
        $('#idCheckBtn').on('click', function(){
            const userId = $userId.val();

            if (userId.length < 7 || userId.length > 15) {
                alert('아이디는 7~15자 사이로 입력해주세요.');
                return; // 함수 종료
            }

            
            $.ajax({
                url: '${pageContext.request.contextPath}/member/idCheck', // 요청을 보낼 주소 추후에 다시 체크
                type: 'GET', 
                data: {
                    checkId: userId 
                },
                success: function(result){
                    // 서버로부터 응답을 성공적으로 받았을 때 실행
                    if(result === 'available'){
                        $idFeedback.text('사용 가능한 아이디입니다.');
                        $idFeedback.css('color', 'green');
                    } else { 
                        $idFeedback.text('이미 사용 중인 아이디입니다.');
                        $idFeedback.css('color', 'red');
                    }
                },
                error: function(){
                    // 서버와의 통신에 실패했을 때 실행
                    alert('서버와의 통신에 실패했습니다.');
                }
            });
        });

    });
</script>




</body>
</html>
