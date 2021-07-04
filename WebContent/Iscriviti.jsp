<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="height: 634px; background-color: White">
<head>
<meta charset="ISO-8859-1">
<title>Sport Challenge Online</title>
</head>
<body style="height: 574px; align="center" ">
	<h1><p style="height: px; color: Red" align="center">Sport Challenge Online</p></h1>
	<form action="<%=request.getContextPath()%>/Iscriviti" method="post" style="height: 409px; ">
	<table>
	<tr>
			<td>Nome</td>
			<td><input type="text" name="Nome" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<td>Cognome</td>
			<td><input type="text" name="Cognome" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<td>Data di nascita</td>
			<td>    <input type="date" name="mydatetime">
			</td>		
		</tr>
		<tr>
			<td>Telefono</td>
			<td><input type="text" name="Telefono" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="Email" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="Password" size="20" maxlength="16"></td>		
		</tr>
		
	</table>
		<input type="checkbox" name="cb" style="width: 31px; height: 26px;  ">Rent 
		<td><td><p><input type="submit"; value="Iscriviti" style="width: 304px; background-color: Lime; height: 46px"></p></td>
	</form>
	</body>
</html>