<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<span><fmt:formatDate value="${event.startDate}" pattern="h : mm a"/> - 
			<fmt:formatDate value="${event.endDate}" pattern="h : mm a"/> | 
			${event.eventName} : ${event.content}</span>
			<span>수정 | 삭제</span>
		</c:forEach>
		<!-- 새 일정 추가 버튼 -->
		<%-- <form action="${pageContext.request.contextPath}/mypage/calendar/new" method="get"> --%>
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">새 개인일정 추가</button>
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    		<div class="modal-dialog">
        		<div class="modal-content">
        			<div class="modal-header">
            			<h5 class="modal-title" id="exampleModalLabel">새 개인일정 추가</h5>
            			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            				<span aria-hidden="true">&times;</span>
            			</button>
        			</div>
        			<form method="post" action="${pageContext.request.contextPath}/event/new">
	        			<div class="modal-body">
	            			제목 <input type="text" name="eventName" required="required" /><br>
						    장소 <input type="text" name="place" required="required" /><br>
						    시작시간 <input type="datetime-local" name="startDate" required="required" /><br>
						    종료시간 <input type="datetime-local" name="endDate" required="required" /><br>
						    세부내용 <textarea name="content" required="required"></textarea>
	        			</div>
	        			<div class="modal-footer">
	            			<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	            			<button type="submit" class="btn btn-primary">추가</button>
	        			</div>
        			</form>
        		</div>
    		</div>
		</div>
		<%-- </form> --%>
	</div>
</div>
<div class="bottom-side">
	<h1>${selectedDate}</h1>
</div>