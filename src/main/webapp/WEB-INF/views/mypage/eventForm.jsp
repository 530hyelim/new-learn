<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

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