<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ page session="false" %>
<html>
<head>
	<title>Register User</title>
</head>
<body>
<h1>
	<form:form id="register" modelAttribute="userInfo">
		<table>
			<tr>
				<td>Username: </td>
				<td><form:input path="username"/></td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><form:password path="password"/></td>
			</tr>
			<tr>
				<td>Age: </td>
				<td><form:input path="age"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<tags:captcha privateKey="6LdZe8QSAAAAAA8DQu_WXpuxQpTVaQM0EYPvO1M5" publicKey="6LdZe8QSAAAAANw5tJUftmtx1m45kYk3fw8aNd1N"></tags:captcha>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					 <input id="submit" type="submit" value="Submit" />
				</td>
			</tr>
		</table>
	</form:form>
</h1>
</body>
</html>
