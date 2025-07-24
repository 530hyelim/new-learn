<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 변경</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/find-id.css">
</head>
<body>

    <h1 class="page-title">비밀번호 찾기</h1>

    <div class="progress-indicator">
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">본인확인</div>
        </div>
        <div class="step-line active"></div>
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">정보변경</div>
        </div>
        <div class="step-line"></div>
        <div class="step">
            <div class="step-circle"></div>
            <div class="step-label">변경완료</div>
        </div>
    </div>

    <div class="find-id-container">
        <form id="resetPwForm" action="${pageContext.request.contextPath}/member/resetPassword" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            
            <div class="form-group">
                <label for="new-password">새 비밀번호</label>
                <input type="password" id="new-password" name="newPassword" placeholder="비밀번호, 문자, 숫자, 특수문자 포함 7~20자">
                <small id="pw-feedback" class="form-text"></small>
            </div>

            <div class="form-group">
                <label for="confirm-password">비밀번호 확인</label>
                <input type="password" id="confirm-password" name="confirmPassword">
                <small id="pw-confirm-feedback" class="form-text"></small>
            </div>

            <div class="form-footer">
                <button type="submit" id="next-btn" class="btn-next">다음</button>
            </div>
        </form>
    </div>
    
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    $(document).ready(function(){
        const $newPw = $('#new-password');
        const $confirmPw = $('#confirm-password');
        const $pwFeedback = $('#pw-feedback');
        const $pwConfirmFeedback = $('#pw-confirm-feedback');

        // 새 비밀번호 유효성 검사
        $newPw.on('keyup', function(){
            const userPw = $newPw.val();
            const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,20}$/;

            if (pwRegex.test(userPw)) {
                $pwFeedback.text('사용 가능한 비밀번호입니다.').css('color', 'green');
            } else {
                $pwFeedback.text('비밀번호는 영문, 숫자, 특수문자를 포함하여 7~20자여야 합니다.').css('color', 'red');
            }
        });

        // 비밀번호 일치 확인
        $newPw.add($confirmPw).on('keyup', function(){
            const newPw = $newPw.val();
            const confirmPw = $confirmPw.val();

            if (confirmPw.length > 0) { 
                if (newPw === confirmPw) {
                    $pwConfirmFeedback.text('비밀번호가 일치합니다.').css('color', 'green');
                } else {
                    $pwConfirmFeedback.text('비밀번호가 일치하지 않습니다.').css('color', 'red');
                }
            } else {
                $pwConfirmFeedback.text('');
            }   
        });
    });
</script>

</body>
</html>