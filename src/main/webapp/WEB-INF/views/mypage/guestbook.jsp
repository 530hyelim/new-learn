<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="guestbook" items="${gbList}">
	<div class="guestbook-entry">
		<table class="guestbook-table">
			<thead>
				<tr>
					<th>NO.${guestbook.guestbookNo}</th>
					<th>${guestbook.userName}</th>
					<th>${guestbook.createDate}</th>
					<td class="button-group">
						<!-- guestbook의 mypageNo와 auth의 userNo가 같으면 hide -->
						<c:if test="${guestbook.mypageNo == loginUserNo}">
							<form:form action="${pageContext.request.contextPath}/mypage/guestbook/hide" method="post">
								<input type="hidden" name="guestbookNo" value="${guestbook.guestbookNo}">
								<button type="submit">HIDE</button>
							</form:form>
						</c:if>
						<!-- guestbook의 userNo와 auth의 userNo가 같으면 delete -->
						<c:if test="${guestbook.userNo == loginUserNo}">
							<form:form action="${pageContext.request.contextPath}/mypage/guestbook/delete" method="post">
								<input type="hidden" name="guestbookNo" value="${guestbook.guestbookNo}">
								<button type="submit">DELETE</button>
							</form:form>
						</c:if>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4">${guestbook.content}</td>
				</tr>
			</tbody>
		</table>
		<div class="guestbook-buttons">
			<form action="${pageContext.request.contextPath}/mypage/${guestbook.userNo}" method="get">
				<button type="submit">답장쓰러가기</button>
			</form>
		</div>
	</div>
</c:forEach>

<button type="button" class="btn btn-primary write-button" data-toggle="modal" data-target="#exampleModal">글쓰기</button>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
   		<div class="modal-content">
   			<div class="modal-header">
       			<h5 class="modal-title" id="exampleModalLabel">새 방명록 글쓰기</h5>
       			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
       				<span aria-hidden="true">&times;</span>
       			</button>
   			</div>
   			<form:form method="post" action="${pageContext.request.contextPath}/mypage/guestbook/new">
     			<div class="modal-body">
     				<input type="hidden" name="mypageNo" value="${mypageNo}">
     				<input type="hidden" name="userNo" value="${loginUserNo}">
     				<input type="hidden" name="userName" value="${loginUserName}">
		    		내용 입력 : <textarea name="content" required="required"></textarea>
     			</div>
     			<div class="modal-footer">
         			<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
         			<button type="submit" class="btn btn-primary">확인</button>
     			</div>
   			</form:form>
   		</div>
	</div>
</div>
