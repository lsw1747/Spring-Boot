<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">ID</label> <input type="text"
				class="form-control" placeholder="ex) abc1234" id="username">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password"
				class="form-control" placeholder="비밀번호 입력" id="password">
		</div>
		<div class="form-group">
			<label for="email">이메일</label> <input type="email"
				class="form-control" placeholder="ex) abc@naver.com" id="email">
		</div>
		<button id="btn-save" type="button" class="btn btn-primary">회원가입</button>
	</form>
</div>
<script src="/js/user.js"></script>
<br />

<%@ include file="../layout/footer.jsp"%>
