<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공유이벤트 페이지</title>
	
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/event.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/calendar.css" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<div class="content">
		<div class="left-side">
			<div class="shared-calendar">
				<form:form id="dateForm" action="${pageContext.request.contextPath}/event/calendar" method="get">
					<jsp:include page="/WEB-INF/views/event/calendar.jsp" />
				</form:form>
			</div>
			<h1>${selectedDate}</h1>
			
			<h3>전체 이벤트</h3>
			<!-- 이벤트 리스트 출력 (DB에서 받아온 리스트 사용) -->
			<div id="shared-events">
			
			</div>
			<c:forEach var="event" items="${sharedEvents}">
				<a href="${pageContext.request.contextPath}/event/detail?eventNo=${event.eventNo}">
				${event.userName} | ${event.eventName} 
				(<fmt:formatDate value="${event.startDate}" pattern="h : mm a"/>)</a>
			</c:forEach>
			<!-- 새 이벤트 추가 버튼 -->
			<form action="${pageContext.request.contextPath}/event/new" method="get">
				<button type="submit">추가</button>
			</form>
			
			<h3>개인 이벤트</h3>
			<!-- db에서 event 테이블 event_type 컬럼 personal인 데이터 조회 -->
			<c:forEach var="event" items="${personalEvents}">
				<a href="${pageContext.request.contextPath}/event/detail?eventNo=${event.eventNo}">
				${event.userName} | ${event.eventName} 
				(<fmt:formatDate value="${event.startDate}" pattern="h : mm a"/>)</a>
			</c:forEach>
			<!-- 새 이벤트 추가 버튼 -->
			<form action="${pageContext.request.contextPath}/mypage/${loginUserNo}" method="get">
				<input type="hidden" name="from" value="event">
				<button type="submit">추가</button>
			</form>
		</div>
		<div class="main-body">
			<!-- 이벤트 폼 -->
			<c:if test="${not empty event}">
				<form:form modelAttribute="event" method="post"
	            action="${pageContext.request.contextPath}/event/dml">
	            	<form:input path="eventNo" type="hidden"/>
	            	<form:input path="eventType" type="hidden" value="SHARED"/>
	                제목<form:input path="eventName" placeholder="이벤트 제목을 입력하세요" required="true"/>
			        장소<form:input path="place" placeholder="어디서 만날까요?" required="true"/>
			        시작시간<form:input path="startDate" type="datetime-local" required="true"/>
			        종료시간<form:input path="endDate" type="datetime-local" required="true"/>
			        모집인원<form:input path="numPpl" type="number" required="true"/>
			        모집기한<form:input path="joinDeadline" type="datetime-local" required="true"/>
			        세부내용<form:textarea path="content" placeholder="뭐 하고 놀까요?" required="true"/>
		            <div align="center">
		            	<c:choose>
		            		<c:when test="${event.eventName == null}"><!-- 새 이벤트 추가 -->
		            			<form:input path="dmlType" type="hidden" value="insert" />
			            		<button type="reset">초기화</button>
			            		<button type="submit">이벤트 생성</button>
			            	</c:when>
			            	<c:otherwise><!-- 이벤트 상세보기 -->
				            	<c:if test="${event.userNo == loginUserNo}">
				            		<form:input path="dmlType" type="hidden" value="update" />
					                <button type="submit">이벤트 수정</button>
				            	</c:if>
			            	</c:otherwise>
		            	</c:choose>
		            </div>
		        </form:form>
	        </c:if>
		</div>
	</div>
</body>
<c:if test="${not empty eventMsg}">
	<script>alert("${eventMsg}");</script>
</c:if>
<script>
	function onDateClick(date) {
	   	const form = document.getElementById("dateForm");
	    document.getElementById("selectedDate").value = date;
	    form.submit();
	}
</script>
</html>