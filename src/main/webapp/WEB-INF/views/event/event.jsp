<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>new Learn();</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/event.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/calendar.css" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<div class="content">
		<div class="left-side">
			<div class="shared-calendar">
				<jsp:include page="/WEB-INF/views/event/calendar.jsp" />
			</div>
			<h1>${selectedDate}</h1>
			
			<h3>전체 이벤트</h3>
			<!-- 이벤트 리스트 출력 (DB에서 받아온 리스트 사용) -->
			<c:forEach var="event" items="${sharedEvents}">
				<a href="">${event.userNo} | ${event.eventName} (${event.startDate})</a>
			</c:forEach>
			<!-- 새 이벤트 추가 버튼 -->
			<form action="${pageContext.request.contextPath}/event/new" method="get">
				<button type="submit">추가</button>
			</form>
			
			<h3>개인 이벤트</h3>
			<!-- db에서 event 테이블 event_type 컬럼 personal인 데이터 조회 -->
			<button>추가</button>
		</div>
		
		<div class="main-body">
			<!-- 이벤트 폼 -->
			<c:if test="${not empty event}">
				<form:form modelAttribute="event" method="post"
	            action="${pageContext.request.contextPath}/event/new">
                <form:input path="eventName" placeholder="제목" />
		        <form:input path="userNo" placeholder="작성자" />
		        <form:input path="startDate" placeholder="시간" />
		        <form:textarea path="content" placeholder="내용" />
	            <div align="center">
	                <button type="reset">초기화</button>
	                <button type="submit">이벤트 생성</button>
	            </div>
	        </form:form>
        </c:if>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	
</script>
</html>