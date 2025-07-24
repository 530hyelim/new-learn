<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mypage.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/guestbook.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mp-calendar.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/calendar.css"
	type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

	<div class="content">
		<div class="left-side">
			<div class="profile">
				<h1>${loginUserName}</h1>
				<h4>상태 메세지~~</h4>
				<button>프로필 수정</button>
				<button>내가 쓴 글</button>
			</div>
			<div class="classroom-list">
				<h3>내 클래스룸</h3>
				<p>KH 자바클래스 G반</p>
				<p>파이썬으로 GPT 혼내주기</p>
				<button>탈퇴</button>
				<button>추가</button>
			</div>
			<button>알림설정</button>
		</div>

		<div class="main-body">
			<!-- 슬라이딩 이미지 -->
			<div class="sliders-container">
				<div class="slider" data-simple-slider>
					<c:forEach var="img" items="${fileList}" begin="2">
						<img src="${pageContext.request.contextPath}/resources/main/${img}"/>
					</c:forEach>
				</div>
				<div class="slider" data-simple-slider>
					<c:forEach var="img" items="${fileList}" begin="1">
						<img src="${pageContext.request.contextPath}/resources/main/${img}"/>
					</c:forEach>
				</div>
				<div class="slider" data-simple-slider>
					<c:forEach var="img" items="${fileList}" begin="0">
						<img src="${pageContext.request.contextPath}/resources/main/${img}"/>
					</c:forEach>
				</div>
			</div>
			<button onclick="loadContent('guestbook')">방명록</button>
			<button onclick="loadContent('calendar')">내 캘린더</button>
			<button onclick="loadContent('storage')">내 저장소</button>
			<div class="container">
				<!-- 방명록 / 캘린더 / 저장소 div -->
			</div>
		</div>
	</div>
</body>
<c:if test="${not empty alertMsg}">
	<script>alert("${alertMsg}");</script>
</c:if>
<script src="https://cdnjs.cloudflare.com/ajax/libs/simple-slider/1.0.0/simpleslider.min.js"></script>
<script>
    document.querySelectorAll('.slider[data-simple-slider]').forEach(el => {
        simpleslider.getSlider(el);
    });
    
    loadContent('guestbook');
    
    function loadContent(type) {
    	fetch('${pageContext.request.contextPath}/mypage/' + type)
    		.then(response => {
    			if (!response.ok) throw new Error('에러 발생');
    			return response.text();
    		})
    		.then(html => {
    			document.querySelector(".container").innerHTML = html;
    		})
    		.catch(error => {
    			document.querySelector(".container").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
    		});
    }
    
	function onDateClick(date) {
		fetch('${pageContext.request.contextPath}/mypage/calendar/' + date)
			.then(response => {
				if (!response.ok) throw new Error('에러 발생');
				return response.text();
			})
			.then(html => {
				document.querySelector(".container").innerHTML = html;
			})
			.catch(error => {
				document.querySelector(".container").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
			});
	}
	
	//$('#edit-btn').click(function() { 수정 버튼이 페이지 로딩 이후 load calendar 함수에서 fetch로 불러와서 
	//동적으로 삽입되고 있기 때문에 이벤트가 등록되지 않음 => 상위 요소에 이벤트 위임
	$(document).on('click', '.modal-btn', function() {
		const btn = $(this);
		const dmlType = btn.data('dmltype');
		const eventNo = btn.data('eventno');
		
		fetch('${pageContext.request.contextPath}/mypage/modal/'+dmlType+'?eventNo='+eventNo)
		.then(response => {
			if (!response.ok) throw new Error('에러 발생');
			return response.text();
		})
		.then(html => {
			document.querySelector(".modal-position").innerHTML = html;
		    new bootstrap.Modal(document.getElementById('exampleModal')).show();
		})
		.catch(error => {
			document.querySelector(".modal-position").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
		});
	});
	
	$(document).on('submit', '#searchForm', function(e) {
		e.preventDefault(); // 기본 동작 방지
		
		fetch('${pageContext.request.contextPath}/mypage/storage/search')
		.then(response => {
			if (!response.ok) throw new Error('에러 발생');
			return response.text();
		})
		.then(html => {
			document.querySelector(".storage-main-body").innerHTML = html;
		})
		.catch(error => {
			document.querySelector(".storage-main-body").innerHTML = '<p>콘텐츠를 불러오지 못했습니다.</p>';
		});
	});
</script>
</html>