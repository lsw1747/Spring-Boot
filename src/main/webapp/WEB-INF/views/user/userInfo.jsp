<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<%@ include file="../layout/header.jsp"%>
<style>
	div.form-group{
		width: 30%;
	}
</style>
<div class="container">
<c:choose>
	<c:when test="${empty principal.user.oauth}">
		<form>
		<input type="hidden" id="id" value ="${principal.user.id}"/>
			<div class="form-group">
				<label for="username">ID</label> 
				<input type="text" class="form-control" id="username" value="${principal.user.username}" readonly />
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> 
				<input type="password" class="form-control" id="password">
			</div>
			<div class="form-group">
				<label for="email">이메일</label> 
				<input type="email" class="form-control" id="email" value="${principal.user.email}">
			</div>
			<button id="btn-update" class="btn btn-primary">수정하기</button>
			<a href="/" class="btn btn-secondary">처음으로</a>
		</form>
	</c:when>
	<c:otherwise>
		<p>잘못된 접근입니다.</p>
	</c:otherwise>
</c:choose>
</div>
<script src="/js/user.js"></script>
<br />

<%@ include file="../layout/footer.jsp"%>
