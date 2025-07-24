<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="선생님 전용 페이지" />
<meta name="author" content="한종윤, 조성모" />
<title>출결 관리</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher_attendance_management.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="icon" href="https://cdn-icons-png.flaticon.com/16/1998/1998614.png">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="container">
		<div class="content">
			<div class="title">출결관리</div>
			<div class="date-select">
				<input type="date" name="date">
			</div>

			<div class="status-summary">
				<div>출석 30/30</div>
				<div>결석 0/30</div>
				<div>지각 0/30</div>
				<div>조퇴 0/30</div>
			</div>

			<table>
				<thead>
					<tr>
						<th>이름</th>
						<th>출석현황</th>
						<th>상태</th>
						<th>입실시간</th>
						<th>퇴실시간</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody>
					<!-- 각 학생 행 -->
					<tr class="row">
						<td>조성모</td>
						<td>출석</td>
						<td>온라인</td>
						<td>09:00</td>
						<td>18:10</td>
						<td>
							<button class="manage-button">출석 관리</button>
							<div class="dropdown">
								<div>출석</div>
								<div>결석</div>
								<div>지각</div>
								<div>조퇴</div>
							</div>
						</td>
					</tr>
					<tr class="row">
						<td>심은성</td>
						<td>출석</td>
						<td>온라인</td>
						<td>08:30</td>
						<td>20:55</td>
						<td>
							<button class="manage-button">출석 관리</button>
							<div class="dropdown">
								<div>출석</div>
								<div>결석</div>
								<div>지각</div>
								<div>조퇴</div>
							</div>
						</td>
					</tr>
					<!-- 추가 학생은 여기 계속 복사 -->
				</tbody>
			</table>
		</div>
	</div>

</body>
<script>
	// 각 "출석 관리" 버튼 클릭 시 드롭다운 열기
	document.querySelectorAll('.manage-button').forEach(button => {
	    button.addEventListener('click', function (e) {
	        // 모든 드롭다운 닫기
	        document.querySelectorAll('.dropdown').forEach(menu => {
	            menu.classList.remove('show');
	        });
	
	        // 현재 버튼 옆 드롭다운 열기
	        const dropdown = this.nextElementSibling;
	        dropdown.classList.toggle('show');
	
	        e.stopPropagation();
	    });
	});
	
	// 바깥 클릭 시 드롭다운 닫기
	document.addEventListener('click', () => {
	    document.querySelectorAll('.dropdown').forEach(menu => {
	        menu.classList.remove('show');
	    });
	});
</script>
</html>