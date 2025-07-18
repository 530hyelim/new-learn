<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>
<<<<<<< HEAD
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	
	<div class="modal" id="loginModal" style="display:block;">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Login</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- 
					Spring-security는 "모든" POST 요청에 대해 CSRF 공격 대비 token을 발행하여 인증처리를 하도록 강제한다.
					만약, 사용자의 POST 요청에 CSRF 인증 토큰이 없는 경우 에러를 강제로 발생시킨다.
					form:form 태그는 CSRF 인증 토큰을 내부적으로 input type="hidden"으로 자동 생성해준다.
					
					CSRF(Cross-Site Request Forgery)
					- 로그인된 사용자의 브라우저 세션을 몰래 이용하여 공격자가 사용자인 것처럼 요청을 서버에 보내는 공격 기법.
					- 세션에 인증 정보를 보관하는 경우, 세션은 브라우저 단위로 저장되므로 하나의 브라우저에서 해커의 웹사이트와 정상적인 사이트 동시에 로그인 하는 경우,
					  해커의 웹사이트에서도 정상 사이트의 세션 데이터를 이용할 수 있다.
					- 이를 방지하기 위한 토큰이 CSRF 토큰.
					- CSRF는 서버측에서 생성한 랜덤 토큰으로, 스프링 시큐리티는 이 토큰이 있는 요청만 유효한 요청으로 간주한다.
				 -->
				<form:form action="${pageContext.request.contextPath}/member/login" method="post">
					<div class="modal-body">
						<label for="userId" class="mr-sm-2">ID : </label>
						<input type="text" class="form-controll mb-2 mr-sm-2" placeholder="Enter ID" id="userId" name="userId"> <br>
						<label for="userPwd" class="mr-sm-2">PWD : </label>
						<input type="password" class="form-controll mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd">
					</div>
					
					<div class="modal-footer justify-content-between">
                        <div>
                            <input type="checkbox" class="form-check-input" name="remember-me" id="remember-me"/>
                            <label for="remember-me" class="form-check-label">Remember me</label>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-outline-success">로그인</button>
                            <button type="button" class="btn btn-outline-success" data-dismiss="modal">취소</button>
                        </div>
                    </div>
				</form:form>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
=======

    <div class="login-container">
        <h1 class="logo">new Learn();</h1>
        
    <!-- 임시<form action="/member/login" method="post" class="login-form"> -->
    
    	<form id="loginForm" action="/member/login" method="post" class="login-form">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="input-group">
                <span class="input-icon">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" width="20px" height="20px">
                    <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
                </span>
                
                <!--임시 <input type="text" name="memberId" placeholder="ID" required>  -->
           		<input type="text" name="userId" id="userId" placeholder="ID">
            </div>
            
            <div class="input-group">
                <span class="input-icon">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" width="20px" height="20px">
                    <path d="M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zM9
                    8V6c0-1.66 1.34-3 3-3s3 1.34 3 3v2H9z"/></svg>
                </span>
              
               <!--임시 <input type="password" name="memberPw" placeholder="PW" required> --> 
               <input type="password" name="userPw" id="userPw" placeholder="PW">
            </div>
            
            <div class="options">
                <label class="checkbox-label">                                                                                                                                                                                                                                                                                                                                             
                    <input type="checkbox" name="rememberId"> 로그인 상태 유지
                </label>
                <div class="links">
                    <a href="#">아이디 찾기</a> |
                    <a href="#">비밀번호 찾기</a> |
                    <a href="${pageContext.request.contextPath}/member/enroll">회원가입</a>
                </div>
            </div>
            
            <button type="submit" class="login-btn">LOGIN</button>
        </form>
    </div>

<!-- 모달 창 -->
<div id="validationModal" class="modal-overlay">
    <div class="modal">
        <button class="close-btn" onclick="closeModal()">&times;</button>
        <div class="modal-message" id="modalMessage"></div>
        <button class="confirm-btn" onclick="closeModal()">확인</button>
    </div>
</div>

<script>
    document.getElementById('loginForm').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const userId = document.getElementById('userId').value.trim();
        const userPw = document.getElementById('userPw').value.trim();
        
        if (!userId && !userPw) {
            showModal('아이디 또는 비밀번호를 확인해주세요.');
            return;
        }
        
        if (!userId) {
            showModal('아이디를 입력해주세요.');
            return;
        }
        
        if (!userPw) {
            showModal('비밀번호를 입력해주세요.');
            return;
        }
        
       //  실전에서는 이거 주석 지우고 활성화해야함 this.submit();
        showModal('로그인 성공! (테스트용)');  // alert
    });
    
    function showModal(message) {
        document.getElementById('modalMessage').textContent = message;
        document.getElementById('validationModal').style.display = 'flex';
    }
    
    function closeModal() {
        document.getElementById('validationModal').style.display = 'none';
    }
    
    document.getElementById('validationModal').addEventListener('click', function(e) {
        if (e.target === this) {
            closeModal();
        }
    });
    
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') {
            closeModal();
        }
    });
</script>

>>>>>>> issue-4-Member-및-회원가입

</body>
</html>