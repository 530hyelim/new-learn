<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mypage.css"
	type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

	<div class="content">
		<div class="left-side">
			<div class="profile">
				<h1>사용자</h1>
				<h4>상태 메세지~~</h4>
				<button>프로필 수정</button>
				<button>내가 쓴 글</button>
			</div>
			<div class="classroom-list">
				<h3>내 클래스룸</h3>
				<p>KH 자바클래스 G반</p>
				<p>파이썬으로 GPT 혼내주기</p>
				<button>탈퇴</button>
				<button>추가</button>
			</div>
			<button>알림설정</button>
		</div>

		<div class="main-body">
			<!-- 슬라이딩 이미지 -->
			<div class="sliders-container">
				<div class="slider" data-simple-slider>
					<c:forEach var="img" items="${fileList}" begin="2">
						<img src="${pageContext.request.contextPath}/resources/main/${img}"/>
					</c:forEach>
				</div>
				<div class="slider" data-simple-slider>
					<c:forEach var="img" items="${fileList}" begin="1">
						<img src="${pageContext.request.contextPath}/resources/main/${img}"/>
					</c:forEach>
				</div>
				<div class="slider" data-simple-slider>
					<c:forEach var="img" items="${fileList}" begin="0">
						<img src="${pageContext.request.contextPath}/resources/main/${img}"/>
					</c:forEach>
				</div>
			</div>
			<button onclick="loadContent('guestbook')">방명록</button>
			<button onclick="loadContent('calendar')">내 캘린더</button>
			<button onclick="loadContent('storage')">내 저장소</button>
			<div class="container">
				<!-- 방명록 / 캘린더 / 저장소 div -->
			</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/simple-slider/1.0.0/simpleslider.min.js"></script>
	<script>
	    document.querySelectorAll('.slider[data-simple-slider]').forEach(el => {
	        simpleslider.getSlider(el);
	    });
	    
	    loadContent('guestbook');
	    
	    function loadContent(type) {
	    	fetch('${pageContext.request.contextPath}/mypage/' + type)
	    		.then(response => {
	    			if (!response.ok) throw new Error('에러 발생');
	    			return response.text();
	    		})
	    		.then(html => {
	    			document.querySelector(".container").innerHTML = html;
	    		})
	    		.catch(error => {
	    			document.querySelector(".container").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
	    		});
	    }
    </script>
</body>
</html>