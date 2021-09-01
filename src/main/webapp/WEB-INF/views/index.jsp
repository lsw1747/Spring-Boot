<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

	<%@ include file="layout/header.jsp" %>
		<div class="container">
			<c:forEach var="board" items="${boards.content}">
				<div class="card m-2">
					<div class="card-body">
						<h4 class="card-title">${board.title}</h4>
						<!-- 
						<c:choose>
							<c:when test="${board.content.length() <= 19}">
								<p>${board.content}</p>
							</c:when>
							<c:when test="${board.content.length() > 20 }">
								<c:set var ="content" value ="${board.content}"/>
								<c:set var ="contentSubString" value = "${fn:substring(content,0,19)}"/>
								<p>${contentSubString}....</p>
							</c:when>
						</c:choose>
						 -->
						<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
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