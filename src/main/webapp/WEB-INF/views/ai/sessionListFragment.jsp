<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="aiChatSession" items="${aiChatSessionsList}">
    <li class="ai-chat-sessions-list" data-session-no="${aiChatSession.sessionNo}">
        ${aiChatSession.title}
    </li>
</c:forEach>