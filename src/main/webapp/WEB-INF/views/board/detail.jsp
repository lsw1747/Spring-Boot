<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>


<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div>
			글 번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i>${board.user.username}</i></span>
		</div>
		<br />
		<h3>${board.title}</h3>
		<hr />
		<div class="form-group">
			<div>${board.content}</div>
		</div>
		<hr />
	</form>
	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/update/${board.id}" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<a href="/" class="btn btn-primary">처음으로</a>
</div>
<br />
<script src="/js/board.js?v=2"></script>
<%@ include file="../layout/footer.jsp"%>
