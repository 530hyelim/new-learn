<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<c:forEach var="file" items="${fileList}">
	<p>${file.originName} | ${file.createDate} | ${file.fileSize}</p>
</c:forEach>

<button>새 폴더 생성</button>

<div class="upload-file">
	<form:form action="${pageContext.request.contextPath}/mypage/storage/upload"
		id="uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" id="upfile" class="form-control" name="upfile">
		<button type="submit">파일 업로드</button>
	</form:form>
</div>