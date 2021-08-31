<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
	

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<h3>${board.title}</h3>
		<hr />
		<div class = "form-group">
			<div>${board.content}</div>
		</div>
		<hr />
	</form>
	<script>
		var data = {
			id : ${board.id}
		}; 
	</script>
	<button class = "btn btn-secondary" onclick="history.back()">뒤로가기</button>
	<button id = "btn-update" class = "btn btn-warning">수정</button>
	<button id = "btn-delete" class = "btn btn-danger">삭제</button>
</div>
<br />
<script src="/js/board.js?v=2"></script>
<%@ include file="../layout/footer.jsp"%>
