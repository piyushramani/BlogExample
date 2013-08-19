<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<h1>List of Team</h1>
<table cellpadding="1" cellspacing="1" border="1px">
	<thead>
		<th width="10%">Id</th>
		<th width="15%">Name</th>
		<th width="15%">Rating</th>
		<th width="10%">Edit Action</th>
		<th width="10%">Delete Action</th>
	</thead>
	<tbody>
			<c:forEach var="team" items="${teams}">
				<tr>
					<td>${team.id}</td>
					<td>${team.name}</td>
					<td>${team.rating}</td>
					<td><a href="${pageContext.request.contextPath}/team/edit/${team.id}.html">Edit</a></td>
					<td><a href="${pageContext.request.contextPath}/team/delete/${team.id}.html">Delete</a></td>
				</tr>
			</c:forEach>		
	</tbody>
</table>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
	
