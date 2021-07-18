<%@ include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
	<h1>My Todo Application</h1>

	<font color="RED">${ errorMessage }</font>

	<form method="POST">
		Name : <input type="text" name="name"> Password : <input
			type="password" name="password"> <input type="submit">
	</form>
</div>
<%@ include file="common/footer.jspf"%>
