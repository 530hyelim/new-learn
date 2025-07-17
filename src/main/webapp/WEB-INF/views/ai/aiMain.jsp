<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="description" content="AI를 이용할 수 있는 페이지" />
<meta name="author" content="한종윤" />
<title>AI 모음 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ai-main.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<div class="content-wrapper">
        <div class="content">
            <div class="side-nav">
                
            </div>
            <div class="main-view">
                <div class="main-view-top">
                    <select name="national">
                        <option value="gpt-4o" selected>ChatGPT-4o</option>
                        <option value="gemini-2.5-flash">Gemini 2.5 Flash</option>
                    </select>
                    <button type="button">나가기</button>
                </div>
                <div class="answer">
					<button id="test">테스트용</button>    
                </div>
                <div class="main-view-bottom">
                    <div class="input-area">
                        <div class="text-input-area">
                            <textarea name="prompt" id="prompt"></textarea>
                        </div>
                        <button id="prompt-send-btn">
                            전송
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/ai-main.js"></script>
</html>