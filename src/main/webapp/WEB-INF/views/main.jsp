<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>new Learn();</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/calendar.css" type="text/css" />
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
			<form action="${pageContext.request.contextPath}/mypage" method="get">
				<button type="submit">마이페이지</button>
			</form>
			<button>입실</button>
			<div class="friend-list">
				<h4>즐겨찾기</h4>
				<h4>일반</h4>
				<button>친구관리</button>
			</div>
			<div class="chatroom-list">
				<h4>전체채팅</h4>
				<h4>그룹채팅</h4>
				<h4>개인채팅</h4>
				<button>채팅방관리</button>
				<button>새채팅</button>
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
				<button>맥주창고</button>
			</div>
			<div class="board-list">
				<table>
					<thead>
						<tr>
							<th>이미지</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>등록일</th>
							<th>조회</th>
							<th>추천</th>
						</tr>
					</thead>
						<!-- 동적 요소 생성 후 바인딩 -->
					<tbody>
					</tbody>
				</table>
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
			<div class="shared-calendar">
				<jsp:include page="/WEB-INF/views/event/calendar.jsp" />
			</div>
			<div class="upcoming-events">
				<h4>UPCOMING EVENTS</h4>
				<p>7월 10일 목요일 6 : 00 PM</p>
				<pre>세미프로젝트 2조 회식 (6명 참여중...)</pre>
				<button>상세보기</button>
			</div>
			<div class="ai-help">
				<h4>ASK ANYTHING!</h4>
				<pre>ChatGPT     Gemini     Claude</pre>
				<p>질문을 입력하세요</p>
				<button><a href="${pageContext.request.contextPath}/ai/main">AI 사용하기</a></button>
			</div>
		</div>
	</div>
</body>
</html>