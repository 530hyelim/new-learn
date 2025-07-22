<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/find-id.css">
</head>
<body>

	<h1 class="page-title">아이디 찾기</h1>

    <div class="progress-indicator">
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">본인확인</div>
        </div>
        <div class="step-line"></div>
        <div class="step">
            <div class="step-circle"></div>
            <div class="step-label">확인완료</div>
        </div>
    </div>

    <%-- 공통폼 파일 포함시키기--%>
    <jsp:include page="_member-verification-form.jsp">
    	<jsp:param name="actionUrl" value="${pageContext.request.contextPath}/member/findId"/>
    </jsp:include>

</body>
</html>