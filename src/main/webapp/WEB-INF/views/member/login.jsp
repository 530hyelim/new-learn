<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>

    <div class="login-container">
        <h1 class="logo">new Learn();</h1>
        
    <!-- 임시<form action="/member/login" method="post" class="login-form"> -->
    
    	<form id="loginForm" action="/member/login" method="post" class="login-form">
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


</body>
</html>