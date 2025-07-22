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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">

</head>
<body>
    <div class="login-container">
        <c:choose>
            <%-- 아이디를 찾았을 경우 --%>
            <c:when test="${not empty foundId}">
                <h1 class="logo">아이디 찾기 결과</h1>
                <div style="padding: 20px 0; font-size: 16px; color: #333;">
                    회원님의 아이디는<br>
                    <strong style="font-size: 24px; color: #000; margin: 10px 0; display: block;">${foundId}</strong>
                    입니다.
                </div>
                <a href="${pageContext.request.contextPath}/member/login" class="login-btn">로그인 하러가기</a>
            </c:when>
            
            <%-- 아이디를 못 찾았을 경우 --%>
            <c:otherwise>
                <h1 class="logo" style="color: #dc3545;">찾기 실패</h1>
                <div style="padding: 20px 0 40px; font-size: 16px; color: #333;">
                    일치하는 회원 정보를 찾을 수 없습니다.
                </div>
                <a href="${pageContext.request.contextPath}/member/findId" class="login-btn">다시 시도</a>
            </c:otherwise>
        </c:choose>
        
    </div>

</body>
</html>

