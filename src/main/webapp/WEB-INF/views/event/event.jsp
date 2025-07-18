<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<button>추가</button>
		</div>
		
		<div class="main-body">
			<!-- 이벤트 폼 -->
			<c:if test="${not empty event}">
				<form:form modelAttribute="event" method="post"
	            action="${pageContext.request.contextPath}/event/new">
	                제목<form:input path="eventName" placeholder="이벤트 제목을 입력하세요" required="true"/>
			        장소<form:input path="place" placeholder="어디서 만날까요?" required="true"/>
			        시작시간<form:input path="startDate" type="datetime-local" required="true"/>
			        종료시간<form:input path="endDate" type="datetime-local" required="true"/>
			        모집인원<form:input path="numPpl" type="number" required="true"/>
			        모집기한<form:input path="joinDeadline" type="datetime-local" required="true"/>
			        세부내용<form:textarea path="content" placeholder="뭐 하고 놀까요?" required="true"/>
		            <div align="center">
		            	<c:if test="${event.eventName == null}">
		            		<button type="reset">초기화</button>
		            		<button type="submit">이벤트 생성</button>
		            	</c:if>
		            	<c:otherwise>
		            		<c:set var="isOwner" value="${event.userNo == loginUser}" />
			            	<c:if test="isOwner">
				                <button type="submit">이벤트 수정</button>
			            	</c:if>
		            	</c:otherwise>
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