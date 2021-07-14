<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" style="height: 634px; background-color: White">
<head>
<meta charset="ISO-8859-1">
<title>Sport Challenge Online</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body style="height: 574px; align="center" ">
	<h1><p class="test" >Sport Challenge Online</p></h1>
	<form action="<%=request.getContextPath()%>/Iscriviti" method="post" style="height: 409px; ">
	<table>
	<caption>iscrizione</caption>
	<tr>
			<th scope="col">Nome</th>
			<td><input type="text" name="Nome" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<th scope="col">Cognome</th>
			<td><input type="text" name="Cognome" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<th scope="col">Data di nascita</th>
			<td>    <input type="date" name="mydatetime">
			</td>		
		</tr>
		<tr>
			<th scope="col">Telefono</th>
			<td><input type="text" name="Telefono" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<th scope="col">Email</th>
			<td><input type="text" name="Email" size="20" maxlength="25"></td>		
		</tr>
		<tr>
			<th scope="col">Password</th>
			<td><input type="password" name="Password" size="20" maxlength="16"></td>		
		</tr>
		
	</table>
		<input type="checkbox" name="cb" style="width: 31px; height: 26px;  ">Rent 
		<td><td><p><input type="submit"; value="Iscriviti" style="width: 304px; background-color: Lime; height: 46px"></p></td>
	</form>
		<input type="submit" value="esci" onclick="window.location='index.jsp'" style="background-color: Red; ">
	
	</body>
</html>