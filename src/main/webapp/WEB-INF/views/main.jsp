<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Project</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="content">
		<div class="content-1">
			<h3>회원 정보 조회</h3>

			<p>아이디를 입력 받아 일치하는 회원 정보를 출력</p>

			아이디 : <input type="text" id="in1">
			<button id="select1">조회</button>
			<div id="result1" style="height: 150px"></div>
		</div>
		
		<!--
	        stomp서비스 실습
	        관리자가 알람내용을 입력 후 "보내기" 버튼 클릭시 모든 사용자에게 알림 내용이 출력되도록 설정
	         - 클라이언트는 전달받은 메시지를 alertify라이브러리를 이용하여 출력
	         - 구독 url은 /topic/notice로 설정
	         - 사용자가 어떤페이지에 있던 알람을 받을 수 있도록 설정
        -->
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="content-2">
				<input type="text" id="notice" placeholder="공지 내용을 입력하세요." />
				<button id="send-notice-btn">공지보내기</button>
			</div>
		</sec:authorize>

	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	const contextPath = '${contextPath}';
	
	document.getElementById("select1").addEventListener("click", function() {
		const in1 = document.getElementById('in1');
		const result1 = document.getElementById('result1');

		$.ajax({
			url : 'member/selectOne',
			data : {
				userId : in1.value
			},
			type : 'POST',
			success : function(result) {
				result1.innerHTML = "";

				// jackson-databind를 사용하지 않았을 경우 자바 객체의 toString() 메서드의 호출결과가 넘어왔을 것.
				console.log(result);
				if (result.USER_ID) {
					//1) ul요소 생성
					const ul = document.createElement("ul"); // <ul></ul>
					//2) li요소 생성 *2개
					const li1 = document.createElement("li");
					li1.innerText = "아이디 : " + result.USER_ID;
					const li2 = document.createElement("li");
					li2.innerText = "이름  :" + result.USER_NAME;
					//3) ul에 li추가
					ul.append(li1, li2);
					//4) ul을 div에 추가
					result1.append(ul);
				}
			}
		})
	})
</script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
<%-- <script type="text/javascript" src="${contextPath }/resources/js/sendNotice.js"></script> --%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<script>
		$(function() {
			const webSocket = new SockJS("${contextPath}/stomp");
			const stompClient = Stomp.over(webSocket);
			
			stompClient.connect({}, function() {
				const sendNoticeBtn = document.querySelector("#send-notice-btn");
				
				sendNoticeBtn.onclick = function() {
				    // 
				    const noticeInput = document.querySelector("#notice");
				
				    stompClient.send("/app/notice/send", {}, noticeInput.value);
				};
			});
			
		});
	</script>
</sec:authorize>
</html>









