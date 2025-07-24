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
		<label for="upfile">파일 선택 : </label>
		<input type="file" id="upfile" class="form-control" name="upfile" multiple required>
		<label for="directory">저장 경로 선택 : </label>
		<select id="directory" class="dir-select" name="directory" required>
			<c:forEach var="repo" items="${repoList}">
				<option value="${repo.repoNo}">${repo.dirName}</option>
			</c:forEach>
        </select>
		<button type="submit">파일 업로드</button>
	</form:form>
</div>