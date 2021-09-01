<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<style>
	.color {
		color: black;
		font-size: 20;
	}
	.title {
		margin-top: 20;
		margin-left: 215;
		margin-bottom : 20;
	}
</style>
	<%@ include file="layout/header.jsp" %>
		<div>
			<h1 class ="title">Blog</h1>
		</div>
		<div class="container">
			<c:forEach var="board" items="${boards.content}">
				<div class="card m-2">
					<div class="card-body">
						<a href="/board/${board.id}"><p class="color">${board.title}</p></a>
					</div>
				</div>
			</c:forEach>

		</div>
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.first}">
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${boards.last}">
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
		<br />

		<%@ include file="layout/footer.jsp" %>