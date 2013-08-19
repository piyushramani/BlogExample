<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p> Status message ${message }.</p>
<br/>
<a href="${pageContext.request.contextPath}/team/add.html">Add new team</a><br>
<a href="${pageContext.request.contextPath}/team/list.html">Team list</a><br>

</body>
</html>
