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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mypage.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/guestbook.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mp-calendar.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/calendar.css"
	type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

	<div class="content">
		<div class="left-side">
			<div class="profile">
				<h1>${mypage.userName}</h1>
				<h4>${mypage.statusMessage}</h4>
				<button>프로필 수정</button>
				<button>내가 쓴 글</button>
			</div>
			<div class="classroom-list">
				<h3>내 클래스룸</h3>
				<c:forEach var="classroom" items="${classList}">
					<p>${classroom.className}</p>
				</c:forEach>
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
</body>
<script>
	const contextPath = '${pageContext.request.contextPath}';
	const mypageNo = ${mypageNo};
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/simple-slider/1.0.0/simpleslider.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
<c:choose>
	<c:when test="${to eq 'event'}">
		<c:choose>
			<c:when test="${from eq 'event'}">
				<script>
					loadContent('calendar', function() {
						const calendarBtn = document.querySelector(".new-personal-btn");
			            if (calendarBtn) {
			                calendarBtn.click();
			            } else {
			                console.warn("버튼을 찾을 수 없습니다.");
			            }
					});
				</script>
			</c:when>
			<c:otherwise>
				<script>
					loadContent('calendar');
				</script>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<script>
			loadContent('guestbook');
		</script>
	</c:otherwise>
</c:choose>

<c:if test="${not empty alertMsg}">
	<script>alert("${alertMsg}");</script>
</c:if>
</html>