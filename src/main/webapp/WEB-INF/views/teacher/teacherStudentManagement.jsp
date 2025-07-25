<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="선생님 전용 페이지" />
<meta name="author" content="한종윤" />
<title>학생 관리</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="icon" href="https://cdn-icons-png.flaticon.com/16/1998/1998614.png">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher_student_management.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="container">
		<div class="content">
			<h1>학생 관리</h1>

			<table>
				<thead>
					<tr>
						<th>회원 번호</th>
						<th>이름</th>
						<th>이메일</th>
						<th>클래스 가입일</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
					<!-- 각 학생 행 -->
					<c:if test="${not empty studentList }">
						<c:forEach var="student" items="${studentList }">
							<tr class="att-row">
								<td>${student.userNo }</td>
								<td>${student.userName }</td>
								<td>${student.email }</td>
								<td><fmt:formatDate value="${student.classJoinDate }" pattern="yyyy-MM-dd"/></td>
								<td>
									<button class="manage-button">보기</button>
									<div class="dropdown">
										<div>마이페이지 보기</div>
										<div>메세지</div>
										<div>추방</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script>
	let classNo = "${classNo}";

	// 각 "보기" 버튼 클릭 시 드롭다운 열기
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

	// 클릭 시 드롭다운 닫기
	document.addEventListener('click', () => {
	    document.querySelectorAll('.dropdown').forEach(menu => {
	        menu.classList.remove('show');
	    });
	});

	// 드랍다운 클릭시 이벤트 실행
	$(document).on('click', '.dropdown > div', function(e) {
	    // 현재 클릭한 드롭다운 메뉴의 tr 찾기
	    let $tr = $(this).closest('tr');
	    // 예시: tr에서 학생 이름 가져오기
	    let userNo = $tr.find('td').eq(0).text();

	    // 클릭한 메뉴(프로필 보기, 메시지, 추방 등) 텍스트
	    let selectedMenu = $(this).text();

		console.log('classNo: ', classNo);
	    console.log('회원번호:', userNo);
	    console.log('selectedMenu:', selectedMenu);

	    // 여기에 원하는 로직 추가!
	    e.stopPropagation();

		if (selectedMenu === "마이페이지 보기") {
			console.log("마이페이지 보기 클릭");
			location.href = "${pageContext.request.contextPath}/mypage/" + userNo;
		} else if (selectedMenu === "메시지") {
			console.log("메시지 클릭");
		} else if (selectedMenu === "추방") {
			console.log("추방 클릭");
			let reallyKick = confirm("정말로 추방하시겠습니까?");

			if (reallyKick) {
				$.ajax({
					url: "${pageContext.request.contextPath}/teacher/studentKick",
					type: "GET",
					data: {
						classNo,
						userNo
					},
					success: function(res) {
						console.log(res);
						alert("삭제되었습니다.");
						location.href = "${pageContext.request.contextPath}/teacher/studentManage/${classNo}";
					},
					error: function(error) {
						console.log(error);
						alert("삭제에 실패했습니다.")
					}
				});
			}
		}
	    
	    // if (attStatus !== attStatusForUpdate) {
		//     $.ajax({
		// 		url: "${pageContext.request.contextPath}/teacher/attendance/update",
		// 		type: "GET",
		// 		data: {
		// 			userNo,
		// 			classNo,
		// 			attStatusForUpdate
		// 		},
		// 		success: function(res) {
		// 			location.href = "${pageContext.request.contextPath}/teacher/attManage/${classNo}?selectedDate=" + selectedDate;
		// 		},
		// 		error: function(error) {
		// 			console.log("error");
		// 			console.log(error);
		// 		}
		// 	});
	    // }
	});
</script>
</html>