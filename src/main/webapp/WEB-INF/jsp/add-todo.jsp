<%@ include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>

<div class="container">
	<h1>My First Application</h1>
	<form:form method="post" modelAttribute="todo">
		<div class="form-group">
			<form:hidden path="id" />
			<form:label path="desc">Description</form:label>
			<form:input type="text" class="form-control" path="desc"
				placeholder="Enter Description" required="required" />
			<form:errors path="desc" cssClass="text-warning" />
		</div>

		<div class="form-group">
			<form:hidden path="id" />
			<form:label path="date">Target Date</form:label>
			<form:input type="text" class="form-control" path="date"
				required="required" />
			<form:errors path="date" cssClass="text-warning" />
		</div>

		<input class="btn btn-success" type="submit">
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>
