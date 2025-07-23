<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
		<h4>${selectedDate} 나의 개인일정</h4>
		<c:forEach var="event" items="${personalEvents}">
			<div class="event-item">
				<span><fmt:formatDate value="${event.startDate}" pattern="h : mm a"/> - 
				<fmt:formatDate value="${event.endDate}" pattern="h : mm a"/> | 
				${event.eventName} : ${event.content}</span>
				<div class="button-group">
					<button class="modal-btn" data-dmltype="edit" data-eventno="${event.eventNo}">수정</button>
					<form action="${pageContext.request.contextPath}/event/delete?eventNo=${event.eventNo}" method="get">
						<button type="submit">삭제</button>
					</form>
				</div>
			</div>
		</c:forEach>
		<button class="modal-btn" data-dmltype="create" data-eventno="0">새 개인일정 추가</button>
		<div class="modal-position">
			<!-- 이벤트폼 모달창 비동기요청 보내서 이벤트객체 바인딩시켜서 뷰 출력 div -->
		</div>
	</div>
</div>
<div class="bottom-side">
	<h1>${selectedDate}</h1>
</div>