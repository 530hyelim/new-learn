<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div class="left-side">
	<div class="repo-list">
		<c:forEach var="repo" items="${repoList}">
			<button>${repo.dirName}</button>
		</c:forEach>
	</div>
	<div class="search-file">
		<form id="searchForm">
            <div class="select">
                <select class="custom-select" name="condition">
                    <option value="fileName">파일명</option>
                    <option value="dirName">폴더명</option>
                    <option value="fileAndDir">파일명+폴더명</option>
                </select>
            </div>
            <div class="text">
                <input type="text" class="form-control" name="keyword"/>
            </div>
            <button type="submit" class="search-Btn">검색</button>
        </form>
	</div>
</div>
<div class="storage-main-body">
	<!-- 비동기식으로 form 요청 보내서 받아온 데이터 출력하는 곳 -->
</div>