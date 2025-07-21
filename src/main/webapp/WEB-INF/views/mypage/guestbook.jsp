<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="guestbook" items="${gbList}">
	<div class="guestbook-entry">
		<table class="guestbook-table">
			<thead>
				<tr>
					<th>NO.${guestbook.guestbookNo}</th>
					<th>${guestbook.userName}</th>
					<th>마이페이지</th><!-- guestbook의 userNo의 마이페이지로 이동 -->
					<th>${guestbook.createDate}</th>
					<th>HIDE</th><!-- guesbook의 mypageNo와 auth의 userNo가 같으면 hide -->
					<th>DELETE</th><!-- guestbook의 userNo와 auth의 userNo가 같으면 delete -->
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="6">${guestbook.content}</td>
				</tr>
			</tbody>
		</table>
		<div class="guestbook-buttons">
			<button>댓글</button>
		</div>
	</div>
</c:forEach>

<button class="write-button">글쓰기</button>
