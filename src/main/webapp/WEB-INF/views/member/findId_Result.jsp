<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 찾기 결과</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/find-id-result.css">
</head>
<body>

	<h1 class="page-title">아이디 찾기</h1>

    <div class="progress-indicator">
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">본인확인</div>
        </div>
        <div class="step-line active"></div>
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">확인완료</div>
        </div>
    </div>


	<div class="complete-container">
        <c:choose>
            <%-- 아이디를 찾았을 경우 --%>
            <c:when test="${not empty foundId}">
                <div class="icon-wrapper">
                    <svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                        <circle class="checkmark-circle" cx="26" cy="26" r="25" fill="none"/>
                        <path class="checkmark-check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
                    </svg>
                </div>
                <h2 class="complete-title">본인 확인 완료</h2>
                <p class="complete-message">회원님의 아이디는 <strong>${foundId}</strong> 입니다.</p>
                <p class="complete-message">회원 정보 확인 및 수정은 <a href="#">마이페이지</a>에서 가능합니다.</p>
                <a href="${pageContext.request.contextPath}/member/login" class="action-btn">로그인 바로가기</a>
            </c:when>
            
            <%-- 아이디를 못 찾았을 경우 --%>
            <c:otherwise>
                 <div class="icon-wrapper">
                    <svg class="failmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                        <circle class="failmark-circle" cx="26" cy="26" r="25" fill="none"/>
                        <path class="failmark-check" d="M16 16 36 36 M36 16 16 36" fill="none"/>
                    </svg>
                </div>
                <h2 class="complete-title fail">본인 확인 실패</h2>
                <p class="complete-message">일치하는 회원 정보를 찾을 수 없습니다.</p>
                <a href="${pageContext.request.contextPath}/member/findId" class="action-btn">다시 시도</a>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>

