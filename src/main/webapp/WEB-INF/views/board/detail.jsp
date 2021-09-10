<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>


<%@ include file="../layout/header.jsp"%>
<style>
	div.board{
		width : 100%;
		height : 350px;
		border : 1px solid;
	}
	div.left {
		width : 48%;
		float : left;
		box-sizing : border-box;
		margin-left:10px;
		margin-top:10px;
	}
	div.right {
		width : 48%;
		float : right;
		height : 95%;
		box-sizing : border-box;
		margin-left:10px;
		margin-right:10px;
		margin-top:10px;
	}
</style>
<meta name="viewport" 
          content="width=device-width, height=device-height, 
                     minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
<div class="container">
	<form>
	
		<div>
			글 번호 : <span id="id"><i>${board.id}</i></span> 작성자 : <span id="user-id"><i>${board.user.username.substring(0,3)}***</i> 조회수 : <i>${board.count}</i></span>
			<i>// board.user.id = ${board.user.id}</i><i> // principal.user.id = ${principal.user.id}</i><i> // reply.user.id = ${reply.user.id}</i>
		</div>
		<br />
		<h3>${board.title}</h3>
		<h3>whoami : ${principal.user.username}</h3>
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
	<hr />
	<br />
	<div class = "board">
		<div class = "card left">
			<div class = "card-hearder">댓글 리스트</div>
			<ul id="reply-box" class = "list-group" style="overflow:auto; height:300px;">
		
				<c:forEach var="reply" items="${board.reply}" varStatus="status">
				<input type="hidden" id="replyId" value="${reply.id}"/>
				<input type="hidden" id="boardId" value="${board.id}"/>
					<li id="reply-${reply.id}" class ="list-group-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class ="d-flex">
							<div class="font-italic">작성자 : ${reply.user.username.substring(0,3)}**** &nbsp;</div>
							<c:if test="${board.user.id == principal.user.id}">
								<button onClick="index.replyDelete(${board.id},${reply.id})" class ="btn btn-primary badge">삭제</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class ="card right">
			<div class = "card-header">댓글</div>
			<input type="hidden" id="userid" value ="${principal.user.id}"/>
			<div class = "card-body"><textarea id = "reply--content" class = "form-control" rows = "5"></textarea></div>
			<div class = "card-footer"><button id = "btn-reply" type="button" class = "btn btn-primary">등록</button></div>
		</div>
	</div>
</div>
<br />
<script src="/js/board.js?v=1"></script>
<%@ include file="../layout/footer.jsp"%>
