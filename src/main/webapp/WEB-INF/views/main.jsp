<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>new Learn();</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<div class="content">
		<div class="sidebar-left" style="background-color: yellow;">
			<div class="mini-profile">
				<!--<sec:authorize access="isAuthenticated()">
					<span><sec:authentication property="principal.username"/></span>				
				</sec:authorize>-->
				<span>님 환영합니다</span>
				<span>입실시간 9 : 03 AM</span>
			</div>
			<button>마이페이지</button>
			<button>입실</button>
			<div class="friend-list">
				<h4>즐겨찾기</h4>
				<h4>일반</h4>
			</div>
			<div class="chatroom-list">
				<h4>전체채팅</h4>
				<h4>그룹채팅</h4>
				<h4>개인채팅</h4>
			</div>
        </div>

		<div class="board" style="background-color: pink;">
			<div class="board-category">
				<ul>
					<li>전체게시판</li>
					<li>공지사항</li>
					<li>자유게시판</li>
					<li>과제제출</li>
					<li>질문게시판</li>
				</ul>
			</div>
			<div class="board-list">
				<!-- 동적 요소 생성 후 바인딩 -->
			</div>
		</div>

		<div class="sidebar-right" style="background-color: skyblue;">
			<div class="notification">
				<h4>새 알림 5건</h4>
				<p>친구 김성겸 님이 늦잠자는중...</p>
				<p>읽지 않은 메세지 12건</p>
				<p>게시판에 새 글이 있습니다</p>
				<p>방명록에 새 글이 있습니다</p>
				<p>제출해야 할 과제가 있습니다</p>
			</div>
		</div>
	</div>
</body>
</html>