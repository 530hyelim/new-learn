<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<style>
* {
	box-sizing: border-box;
}

.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 20px;
	background-color: #575757;
	color: white;
}

.header>div {
	flex: 1;
}

#left {
	text-align: left;
}

#center {
	text-align: center;
}

#right {
	flex: 1;
	text-align: right;
	/* 내부 요소들 세로로 쌓기 */
	display: flex;
	flex-direction: column;
	justify-content: center; /* 세로 가운데 정렬 */
	align-items: flex-end; /* 오른쪽 정렬 */
	gap: 8px; /* 버튼 사이 간격 */
}

.header button {
	padding: 10px 14px;
	border: none;
	border-radius: 6px;
	background-color: #fbc02d;
	cursor: pointer;
	font-weight: 600;
	transition: background-color 0.3s ease;
}

.header button:hover {
	background-color: #f9a825;
}
</style>
</head>

<body>
	<div class="header">
		<div id="left">
			<h1>new Learn();</h1>
		</div>
		<div id="center">
			<c:if test="${not empty mypage}">
				<h2>${mypage.mypageName}</h2>
			</c:if>
			<c:if test="${empty mypage}">
				<h2>${className}</h2>
			</c:if>
			<a href="">관리자 페이지</a>
		</div>
		<div id="right">
			<button id="change-class">클래스룸 변경</button>
			<form action="" method="post">
				<button type="submit">클래스룸 나가기</button>
			</form>
		</div>
	</div>
</body>