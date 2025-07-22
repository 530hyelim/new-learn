<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="find-id-container">
    <form id="verificationForm" action="${param.actionUrl}" method="post">
        
        <div class="form-group">
            <label for="user-name">이름</label>
            <input type="text" id="user-name" name="userName">
        </div>

        <div class="form-group">
            <label>주민등록번호</label>
            <div class="ssn-group">
                <input type="text" id="ssn1" name="ssn1" maxlength="6">
                <span>-</span>
                <input type="password" id="ssn2" name="ssn2" maxlength="1">
            </div>
        </div>

        <div class="form-group">
            <label for="email-id">이메일 주소</label>
            <div class="email-group">
                <input type="text" id="email-id" name="userEmailId">
                <span>@</span>
                <input type="text" id="email-domain" name="userEmailDomain">
                <button type="button" id="email-cert-btn" class="btn-send">인증코드 발송</button>
            </div>
            <small class="form-text">입력하신 이메일로 인증코드를 발송했습니다.</small>
        </div>

        <div class="form-group">
            <label for="email-cert">인증코드</label>
            <div class="input-with-btn">
                <input type="text" id="email-cert" name="emailCert">
                <button type="button" id="email-cert-check-btn" class="btn-check">확인</button>
            </div>
        </div>

        <div class="form-footer">
            <button type="submit" id="next-btn" class="btn-next" disabled>다음</button>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	
	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajaxSetup({
	beforeSend: function(xhr) {
		if(header && token) {
			xhr.setRequestHeader(header, token);
		}
	}
});
	
    // 이메일 인증 관련 스크립트
    $(document).ready(function(){
        
        let serverCertCode = "";      //  <ㅡ 
        let isEmailCertified = false;

        $('#email-cert-btn').on('click', function(){
            const emailId = $('#email-id').val();
            const emailDomain = $('#email-domain').val();

            if (!emailId || !emailDomain) {
                alert('이메일을 모두 입력해주세요.');
                return;
            }
            
            const userEmail = emailId + '@' + emailDomain;

            $.ajax({
                url: '${pageContext.request.contextPath}/member/emailCert',
                type: 'POST',
                data: { email: userEmail },
                success: function(certCode){
                    alert('입력하신 이메일로 인증코드가 발송되었습니다.');
                    serverCertCode = certCode;    // 서버로 부터 받은 코드를 변수에 저장함
                    console.log("서버로부터 받은 인증코드:", serverCertCode); // 디버깅용
                },
                error: function(){
                    alert('인증코드 발송에 실패했습니다. 다시 시도해주세요.');
                }
            });
        });

        $('#email-cert-check-btn').on('click', function(){
            const userCertCode = $('#email-cert').val().trim();

            if (serverCertCode !== "" && userCertCode === serverCertCode) {
                alert('인증에 성공하였습니다.');
                isEmailCertified = true;
                $('#next-btn').prop('disabled', false);
            } else {
                alert('인증코드가 일치하지 않습니다.');
                isEmailCertified = false;
                $('#next-btn').prop('disabled', true);
            }
        });

        $('#findIdForm').on('submit', function(e){
            if (!isEmailCertified) {
                alert('이메일 인증을 먼저 완료해주세요.');
                e.preventDefault();
            }
        });
    });
</script>
