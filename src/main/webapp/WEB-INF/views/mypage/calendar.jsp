<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div class="top-side">
	<div class="shared-calendar">
		<jsp:include page="/WEB-INF/views/event/calendar.jsp" />
	</div>
	<div class="right-side">
		<div class="monthly-attendance">
			<h3>7월 출석률 : 81%</h3>
			<p>8일 입실시간 9 : 03 AM</p>
			<p>8일 퇴실시간 5 : 53 PM</p>
		</div>
		<div class="send-notifications">
			<h3>알림 설정</h3>
			<p>모두</p>
			<p>친구만</p>
		</div>
		<!-- db에서 event 테이블 event_type 컬럼 personal인 데이터 조회 -->
		<h4>${selectedDate} 일정</h4>
		<c:forEach var="event" items="${personalEvents}">
			<p>${event.userName} | ${event.eventName} 
			(<fmt:formatDate value="${event.startDate}" pattern="h : mm a"/>)</p>
		</c:forEach>
		<!-- 새 일정 추가 버튼 -->
		<form action="${pageContext.request.contextPath}/mypage/calendar/new" method="get">
			<button type="submit">추가</button>
		</form>
	</div>
</div>
<div class="bottom-side">
	<h1>${selectedDate}</h1>
</div>