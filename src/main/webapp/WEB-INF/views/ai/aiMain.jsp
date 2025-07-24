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
                <button id="new-chat" type="button"><h2>새 대화</h2></button>
                <br>
                <ul id="side-session-list">
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

    let sessionNo = 0;
    let numPromptsSent = 0;

    // Apply to all auto-resize textareas
    document.querySelector('#prompt').addEventListener('input', function() {
        autoResize(this);
    });

    $("#prompt-send-btn").on("click", function() {
        //
        const prompt = $("#prompt").val();
        $("#answer-box").append($("<div class='prompt-bubble'></div>").text(prompt));
        // $("#answer-box").append("<hr>");

        $.ajax({
            url: "${pageContext.request.contextPath}/ai/getPrompt",
            data: {
                prompt,
                sessionNo
            },
            //dataType: "html",
            success: function(result) {
                console.log(result);
                // $("#answer-box").html(result);
                $("#answer-box").append(result.content);
                // $("#answer-box").append("<hr>");
                if (sessionNo == 0 && numPromptsSent == 0) {
                    refreshSessionList();
                }
                numPromptsSent++;
                sessionNo = parseInt(result.sessionNo);

                console.log("sessionNo: " + sessionNo);
            },
            error: function(error) {
                console.log(error);
            }
        });
    });

    $("#new-chat").on("click", function() {
        sessionNo = 0;
        $("#answer-box").empty();
        numPromptsSent = 0;

        // 서버에 "새 세션 생성" 요청 (insert), 생성된 sessionNo 받아서 sessionNo에 저장
        // $.post('/ai/newSession', {}, function(newSessionNo) {
        //     sessionNo = newSessionNo;
        //     // answer-box 클리어
        //     $("#answer-box").empty();
        // });
    })
	
    $("#side-session-list").on("click", ".ai-chat-sessions-list", function() {        
        if ($(this).hasClass("clicked")) {
            return;
        }
        // 다른 li의 clicked 클래스 다 지움
        $(".ai-chat-sessions-list").removeClass("clicked");
        // 클릭한 li만 clicked 클래스 추가
        $(this).addClass("clicked");

        sessionNo = $(this).data("session-no");
    	console.log("sessionNo: " + sessionNo);

        // 서버에 해당 sessionNo의 히스토리 불러오기 요청
        // loadChatHistory(sessionNo);
        $.ajax({
            url: "${pageContext.request.contextPath}/ai/getChatHistory",
            data: {
                sessionNo
            },
            success: function(result) {
                $("#answer-box").empty();
                for (let i = 0; i < result.length; i++) {
                    console.log(i);
                    console.log(result[i].content);
                    
                    if (result[i].role === 'user') {
                        $("#answer-box").append($("<div class='prompt-bubble'></div>").text(result[i].content));
                    }

                    if (result[i].role === 'assistant') {
                        $("#answer-box").append(result[i].content);
                    }
                }
                console.log(result);
            },
            error: function(error) {
                console.log(error);
            }
        });
    });

    // 예: 새 대화 생성 or 채팅방 리스트 갱신 필요할 때!
    function refreshSessionList() {
        $.get("${pageContext.request.contextPath}/ai/sessionListFragment", function(html) {
            // <ul id="side-session-list">로 감싸는 걸 추천!
            $("#side-session-list").html(html);
        });
    }
</script>
</html>