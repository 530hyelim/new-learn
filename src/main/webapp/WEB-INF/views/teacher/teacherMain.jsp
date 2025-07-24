<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="description" content="선생님 전용 페이지" />
    <meta name="author" content="한종윤, 조성모" />
	<title>선생님 페이지</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher_main.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="icon" href="https://cdn-icons-png.flaticon.com/16/1998/1998614.png">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<c:set var="classNo" value="1" />

	<div class="container">
		<div class="form-group">
			<label>오늘의 입실코드</label> <input type="text"
				placeholder="오늘의 입실코드를 입력하세요">
			<button>생성</button>
			<button>복사</button>
		</div>

		<div class="form-group">
			<label>클래스명 변경</label> <input type="text" value="KH 자바스터디 G반">
			<button>변경</button>
		</div>

		<div class="form-group">
			<label>클래스룸 초대 코드</label> <input type="text" value="">
			<button>복사</button>
		</div>

		<div class="button-grid">
			<div class="icon-button" id="att-manage-btn">
				<img src="/IMG/출결관리.png" width="40px"><span>출결 관리</span>
			</div>
			<div class="icon-button" id="student-manage-btn">
				<img src="/IMG/학생관리.png" width="40px"><span>학생 관리</span>
			</div>
			<div class="icon-button" id="board-manage-btn">
				<img src="/IMG/게시글 관리.png" width="40px"><span>게시글 관리</span>
			</div>
			<div class="icon-button" id="notice-manage-btn">
				<img src="/IMG/공지사항 관리.png" width="40px"><span>공지사항 관리</span>
			</div>
			<div class="icon-button" id="assignment-manage-btn">
				<img src="/IMG/과제관리.png" width="40px"><span>과제 관리</span>
			</div>
			<div class="icon-button" id="report-manage-btn">
				<img src="/IMG/신고 관리.png" width="40px"><span>신고 관리</span>
			</div>
		</div>
	</div>
</body>
<script>
	// 페이지 이동 버튼들
	$("#att-manage-btn").on("click", function() {
		location.href = "${pageContext.request.contextPath}/teacher/attManage/${classNo}";
	});
	$("#student-manage-btn").on("click", function() {
		location.href = "${pageContext.request.contextPath}/teacher/studentManage";
	});
	$("#board-manage-btn").on("click", function() {
		location.href = "${pageContext.request.contextPath}/teacher/boardManage";
	});
	$("#notice-manage-btn").on("click", function() {
		location.href = "${pageContext.request.contextPath}/teacher/noticeManage";
	});
	$("#assignment-manage-btn").on("click", function() {
		location.href = "${pageContext.request.contextPath}/teacher/assignmentManage";
	});
	$("#report-manage-btn").on("click", function() {
		location.href = "${pageContext.request.contextPath}/teacher/reportManage";
	});
</script>
</html>