<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<title>Add Team Page</title>
	</head>
	<body>
		<h1>Add Team Page</h1>
		<p> Add Here New team </p>
		<form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/team/add/process.html">
		<table>
		<tbody>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssStyle="color:red;"></form:errors></td>
			</tr>
			<tr>
				<td>Rating:</td>
				<td><form:input path="rating" /></td>
				<td><form:errors path="rating" cssStyle="color:red;"></form:errors></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add" /></td>
				<td></td>
			</tr>
		</tbody>
		</table>
		</form:form>
	</body>
</html>