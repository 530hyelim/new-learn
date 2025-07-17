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
                <small id="pw-feedback" class="form-text"></small>
            </div>

            <!-- 비밀번호 확인 -->
            <div class="form-group">
                <label for="userPwConfirm">비밀번호 확인</label>
                <input type="password" id="userPwConfirm" name="userPwConfirm">
                <small id="pw-confirm-feedback" class="form-text"></small>
            </div>

            <!-- 이름 -->
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" id="userName" name="userName">
                <small id="name-feedback" class="form-text"></small>
            </div>

            <!-- 주민등록번호 -->
            <div class="form-group">
                <label>주민등록번호</label>
                <div class="ssn-group">
                    <input type="text" name="ssn1" maxlength="6">
                    <span>-</span>
                    <input type="password" name="ssn2" maxlength="1">
                </div>
                <small id="ssn-feedback" class="form-text"></small>
            </div>

            <!-- 전화번호 -->
            <div class="form-group">
                <label for="phone">전화번호</label>
                <input type="text" id="phone" name="phone" placeholder="휴대폰 번호 입력('-'제외 11자리 입력)">
                <small id="phone-feedback" class="form-text"></small>
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

        $('#idCheckBtn').on('click', function(){
            const userId = $userId.val();

            if (userId.length < 7 || userId.length > 15) {
                alert('아이디는 7~15자 사이로 입력해주세요.');
                return;
            }
       
            $.ajax({
                url: '${pageContext.request.contextPath}/member/idCheck', // 요청을 보낼 주소 추후에 다시 체크
                type: 'GET', 
                data: {
                    checkId: userId 
                },
                success: function(result){
                    if(result === 'available'){
                        $idFeedback.text('사용 가능한 아이디입니다.');
                        $idFeedback.css('color', 'green');
                    } else { 
                        $idFeedback.text('이미 사용 중인 아이디입니다.');
                        $idFeedback.css('color', 'red');
                    }
                },
                error: function(){
                    alert('서버와의 통신에 실패했습니다.');
                }
            });
        });

        const $userPw = $('#userPw');
        const $userPwConfirm = $('#userPwConfirm');
        const $pwFeedback = $('#pw-feedback');
        const $pwConfirmFeedback = $('#pw-confirm-feedback');

        $userPw.on('keyup', function(){
            const userPw = $userPw.val();
            
            // 비밀번호 규칙: 영문, 숫자, 특수문자 조합 7~20자
            const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,20}$/;

            if (pwRegex.test(userPw)) {
                $pwFeedback.text('사용 가능한 비밀번호입니다.');
                $pwFeedback.css('color', 'green');
            } else {
                $pwFeedback.text('비밀번호는 영문, 숫자, 특수문자를 포함하여 7~20자여야 합니다.');
                $pwFeedback.css('color', 'red');
            }
        });

        $userPw.add($userPwConfirm).on('keyup', function(){
            const userPw = $userPw.val();
            const userPwConfirm = $userPwConfirm.val();

            if (userPwConfirm.length > 0) { 
                if (userPw === userPwConfirm) {
                    $pwConfirmFeedback.text('비밀번호가 일치합니다.');
                    $pwConfirmFeedback.css('color', 'green');
                } else {
                    $pwConfirmFeedback.text('비밀번호가 일치하지 않습니다.');
                    $pwConfirmFeedback.css('color', 'red');
                }
            } else {

                $pwConfirmFeedback.text('');
            }   

            // 
            const $userName = $('#userName');
            const $nameFeedback = $('#name-feedback');

            $userName.on('keyup', function(){
                
                if ($userName.val().trim().length === 0) {
                    $nameFeedback.text('이름을 입력해주세요.');
                    $nameFeedback.css('color', 'red');
                    
                } else {
                    $nameFeedback.text('');
                }
                
			// 주민번호
            const $ssn1 = $('#ssn1');
            const $ssn2 = $('#ssn2');

            $ssn1.add($ssn2).on('keyup', function(){

            this.value = this.value.replace(/[^0-9]/g, '');
              
            });
            
            $ssn1.on('keyup', function(){
            if ($(this).val().length === 6) {
                $ssn2.focus();
              }
              
            // 전화번호 유효성 검사
            const $phone = $('#phone');
            const $phoneFeedback = $('#phone-feedback');

            $phone.on('keyup', function(){
                this.value = this.value.replace(/[^0-9]/g, '');
            });
            $phone.on('blur', function(){
                const phoneNum = $(this).val();
                
                if (phoneNum.length > 0 && phoneNum.length !== 11) {
                    $phoneFeedback.text('전화번호는 11자리여야 합니다.');
                    $phoneFeedback.css('color', 'red');
                } else {
                    $phoneFeedback.text('');
                }
                      
     });
   
</script>




</body>
</html>
