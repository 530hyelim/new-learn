<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>íìê°ì ê²°ê³¼</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/enrollComplete.css">
</head>
<body>

    <h1 class="page-title">íìê°ì</h1>

   <c:set var="result" value="${empty result ? 'success' : result}" />
   <c:set var="userName" value="${empty userName ? 'ì¬ì©ì' : userName}" /> 


    <!-- ì§í ë¨ê³ íì -->
    <div class="progress-indicator">
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">ì½ê´ëì</div>
        </div>
        <div class="step-line active"></div>
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">ì ë³´ìë ¥</div>
        </div>
        <div class="step-line active"></div>
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">ê°ììë£</div>
        </div>
    </div>


    <c:choose>
        <%-- 1. ê°ì ì±ê³µ ì¼ì´ì¤ --%>
        <c:when test="${result == 'success'}">
            <div class="complete-container">
                <div class="icon-wrapper">
                    <!-- ì±ê³µ ì²´í¬ ìì´ì½ SVG -->
                    <svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">                	
                        <circle class="checkmark-circle" cx="26" cy="26" r="25" fill="none"/>
                        <path class="checkmark-check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
                    </svg>
                </div>
                <h2 class="complete-title">íìê°ì ìë£</h2>
                <p class="complete-message"><strong>${userName}</strong>ëì íìê°ìì ì¶íí©ëë¤!</p>
                <p class="complete-message">íì ì ë³´ íì¸ ë° ìì ì <a href="#">ë§ì´íì´ì§</a>ìì ê°ë¥í©ëë¤.</p>
                <a href="#" class="action-btn">ë¡ê·¸ì¸ ë°ë¡ê°ê¸°</a>
            </div>
        </c:when>

        <%-- 2. ê°ì ì¤í¨ ì¼ì´ì¤ --%>
        <c:otherwise>
            <div class="complete-container">
                <div class="icon-wrapper">
                    <!-- ì¤í¨ X ìì´ì½ SVG -->
                    <svg class="failmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                        <circle class="failmark-circle" cx="26" cy="26" r="25" fill="none"/>
                        <path class="failmark-check" d="M16 16 36 36 M36 16 16 36" fill="none"/>
                    </svg>
                </div>
                <h2 class="complete-title fail">íìê°ì ì¤í¨</h2>
                <p class="complete-message">ì´ë¯¸ ê³ì ì´ ì¡´ì¬íë ì¬ì©ì ì ë³´ìëë¤.</p>
                <a href="#" class="action-btn">ë¡ê·¸ì¸ ë°ë¡ê°ê¸°</a>
            </div>
        </c:otherwise>
    </c:choose>

</body>
</html>

