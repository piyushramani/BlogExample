<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Team</title>
</head>
<body>
	<h1>Edit Team Page</h1>
	<form:form method="post" commandName="editTeam" action="${pageContext.request.contextPath}/team/edit/${editTeam.id}.html">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" ></form:input></td>
			</tr>
			
			<tr>
				<td>Rathing:</td>
				<td><form:input path="rating"></form:input></td>
			</tr>
			
			<tr>
				<td colspan="1"><input type="submit" value="Update"/></td>
			</tr>
		</table>
	</form:form>
	<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</body>
</html>