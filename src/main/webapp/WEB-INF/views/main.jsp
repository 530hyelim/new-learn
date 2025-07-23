<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>new Learn();</title>
	
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/calendar.css" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<div class="content">
		<div class="sidebar-left" style="background-color: yellow;">
			<div class="mini-profile">
				<span>${loginUserName}님 환영합니다</span>
				<span>입실시간 <fmt:formatDate value="${attendance.entryTime}" pattern="h : mm a"/></span>
			</div>
			<form action="${pageContext.request.contextPath}/mypage/${loginUserNo}" method="get">
				<button type="submit">마이페이지</button>
			</form>
			
			<button type="button" data-toggle="modal" data-target="#exampleModal">입실</button>
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
			   		<div class="modal-content">
			   			<div class="modal-header">
			       			<h5 class="modal-title" id="exampleModalLabel">입실코드 입력</h5>
			       			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			       				<span aria-hidden="true">&times;</span>
			       			</button>
			   			</div>
			   			<form:form method="post" action="${pageContext.request.contextPath}/entry">
			     			<div class="modal-body">
					    		<input type="text" name="attEntryCode" required="required"/>
			     			</div>
			     			<div class="modal-footer">
			         			<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			         			<button type="submit" class="btn btn-primary">확인</button>
			     			</div>
			   			</form:form>
			   		</div>
				</div>
			</div>
			
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
				<form:form id="dateForm" action="${pageContext.request.contextPath}/event/calendar" method="get">
					<jsp:include page="/WEB-INF/views/event/calendar.jsp" />
				</form:form>
			</div>
			<div class="upcoming-events">
				<h4>UPCOMING EVENTS</h4>
				<c:forEach var="event" items="${eventWithMemberCnt}">
					<p><fmt:formatDate value="${event.key.startDate}" pattern="M월 d일 EEEE h : mm a"/></p>
					<pre>${event.key.eventName} (${event.value}명 참여중...)</pre>
					<form action="${pageContext.request.contextPath}/event/detail/${event.key.eventNo}" method="get">
						<button type="submit">상세보기</button>
					</form>
				</c:forEach>
			</div>
			<div class="ai-help">
				<h4>ASK ANYTHING!</h4>
				<pre>ChatGPT     Gemini     Claude</pre>
				<p>질문을 입력하세요</p>
			</div>
		</div>
	</div>
</body>
<c:if test="${not empty entryMsg}">
	<script>alert("${entryMsg}");</script>
</c:if>
<script>
	function onDateClick(date) {
	   	const form = document.getElementById("dateForm");
	    document.getElementById("selectedDate").value = date;
	    form.submit();
	}
</script>
</html>