<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="description" content="AI를 이용할 수 있는 페이지" />
    <meta name="author" content="한종윤" />
    <title>AI 모음 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ai-main.css"/>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="icon" href="https://cdn-icons-png.flaticon.com/16/1998/1998614.png">
</head>
<body>
    <div class="content-wrapper">
        <div class="content">
            <div class="side-nav">
                <ul>
                	<c:if test="${not empty aiChatSessionsList }">
                		<c:forEach var="aiChatSession" items="${aiChatSessionsList }">
                			<li class="ai-chat-sessions-list" data-session-no="${aiChatSession.sessionNo }">${aiChatSession.title }</li>
                		</c:forEach>
                	</c:if>
                </ul>
            </div>
            <div class="main-view">
                <div class="main-view-top">
                    <select name="ai-models-dropdown">
                    	<c:forEach var="ai" items="${aiList }" varStatus="i">
                    		<c:if test="${i.index == 0 }">
                    			<option value="${ai.modelNo }" selected>${ai.modelName }</option>
                    		</c:if>
                    		<c:if test="${i.index != 0 }">
                    			<option value="${ai.modelNo }">${ai.modelName }</option>
                    		</c:if>
                    	</c:forEach>
                    </select>
                    <button type="button" id="return-to-main"><a href="${pageContext.request.contextPath}/">나가기</a></button>
                </div>
                <div class="answer-area">
                    <div id="answer-box">
                        
                    </div>
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
<script>
    function autoResize(textarea) {
        // Reset height to auto to get accurate scrollHeight
        textarea.style.height = 'auto';
        
        // Set height to scrollHeight (content height)
        textarea.style.height = textarea.scrollHeight + 'px';
    }

    // Apply to all auto-resize textareas
    document.querySelector('#prompt').addEventListener('input', function() {
        autoResize(this);
    });

    $("#prompt-send-btn").on("click", function() {
        //
        const prompt = $("#prompt").val();
        const $promptDiv = $("<div class='prompt-bubble'></div>").text(prompt);
        $("#answer-box").append($promptDiv);
        // $("#answer-box").append("<hr>");

        $.ajax({
            url: "${pageContext.request.contextPath}/ai/getPrompt",
            data: {
                prompt
            },
            //dataType: "html",
            success: function(result) {
                console.log(result);
                // $("#answer-box").html(result);
                $("#answer-box").append(result.content);
                // $("#answer-box").append("<hr>");
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
	
    $(".ai-chat-sessions-list").on("click", function() {
    	console.log(this);
    });
</script>
</html>