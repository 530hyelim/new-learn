<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 결과</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/enrollComplete.css">
</head>
<body>

    <h1 class="page-title">회원가입</h1>

   <c:set var="result" value="${empty result ? 'success' : result}" />
   <c:set var="userName" value="${empty userName ? '사용자' : userName}" /> 


    <!-- 진행 단계 표시 -->
    <div class="progress-indicator">
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">약관동의</div>
        </div>
        <div class="step-line active"></div>
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">정보입력</div>
        </div>
        <div class="step-line active"></div>
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">가입완료</div>
        </div>
    </div>


    <c:choose>
        <%-- 1. 가입 성공 케이스 --%>
        <c:when test="${result == 'success'}">
            <div class="complete-container">
                <div class="icon-wrapper">
                    <!-- 성공 체크 아이콘 SVG -->
                    <svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">                	
                        <circle class="checkmark-circle" cx="26" cy="26" r="25" fill="none"/>
                        <path class="checkmark-check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
                    </svg>
                </div>
                <h2 class="complete-title">회원가입 완료</h2>
                <p class="complete-message"><strong>${userNameForComplete}</strong>님의 회원가입을 축하합니다!</p>
                <p class="complete-message">회원 정보 확인 및 수정은 <a href="#">마이페이지</a>에서 가능합니다.</p>
                <a href="${pageContext.request.contextPath}/member/login" class="action-btn">로그인 바로가기</a>
            </div>
        </c:when>

        <%-- 2. 가입 실패 케이스 --%>
        <c:otherwise>
            <div class="complete-container">
                <div class="icon-wrapper">
                    <!-- 실패 X 아이콘 SVG -->
                    <svg class="failmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                        <circle class="failmark-circle" cx="26" cy="26" r="25" fill="none"/>
                        <path class="failmark-check" d="M16 16 36 36 M36 16 16 36" fill="none"/>
                    </svg>
                </div>
                <h2 class="complete-title fail">회원가입 실패</h2>
                <p class="complete-message">이미 계정이 존재하는 사용자 정보입니다.</p>
                <a href="${pageContext.request.contextPath}/member/login" class="action-btn">로그인 바로가기</a>
            </div>
        </c:otherwise>
    </c:choose>

</body>
</html>

