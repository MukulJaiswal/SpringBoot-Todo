<%@ include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">
	<h1>Todos of ${ name }</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Date</th>
				<th>Status</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${ todo.id }</td>
					<td>${ todo.desc }</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.date}" /></td>
					<td>${ todo.done }</td>
					<td><a href="update-todo?id=${ todo.id }"
						class="btn btn-success">Update</a></td>
					<td><a href="delete-todo?id=${ todo.id }"
						class="btn btn-warning">Delete</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>


	<h4>Do you want to add a new TODOS...Click below</h4>

	<a class="btn btn-primary" href="add-todos" role="button">Add Todo</a>
</div>
<%@ include file="common/footer.jspf"%>