<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<c:choose>
	<c:when test="${not empty repo}">
		현재 폴더 : ${repo.dirName}
	</c:when>
	<c:otherwise>
		전체 파일 목록
	</c:otherwise>
</c:choose>

<c:forEach var="file" items="${fileList}">
	<p>${file.originName} | ${file.createDate} | ${file.fileSize}</p>
</c:forEach>

<button>새 폴더 생성</button>

<c:if test="${not empty repo}">
	<div class="upload-file">
		<form:form action="${pageContext.request.contextPath}/mypage/storage/insert"
			id="uploadForm" method="post" enctype="multipart/form-data">
			<label for="upfile">파일 선택 : </label>
			<input type="file" id="upfile" class="form-control" name="upfile" multiple required>
			<input type="hidden" name="repoNo" value="${repo.repoNo}"/>
			<button type="submit">파일 업로드</button>
		</form:form>
	</div>
</c:if>