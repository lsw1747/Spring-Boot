<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="test" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email address</label> 
			<input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<button id="btn-save" class="btn btn-primary">Sign Up</button>
	</form>
</div>
<br />

<%@ include file="../layout/footer.jsp"%>
