<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="background-color: White; height: 274px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/GestisciCampiSportivi" method="get" style="height: 409px; id="form" ">
		<div><h1 style="height: px; color: Red" align="center">Sport Challenge Online</h1></div>
		<p> I miei campi </p>
	<div>
		<table border="1">
		<tr>
			<th>NOME</th>
			<th>CITTA'</th>
			<th>INDIRIZZO</th>
			<th>DESCRIZIONE</th>
			<th>DATA</th>
			
		<c:forEach var="campo" items="${campo}">
		<tr>
			<td>${campo}</td>
			
		</tr>
		
		</c:forEach>

		</tr>
		</table></form>
	</div>
	<div><form align="center">
			<input value="Rendi Affittabile"type="submit" style="background-color: Aqua"><input value="Refresh" type="submit" style="background-color: #FF8040"><input value="Cancella" type="submit" style="background-color: Red; ">
		</form>
	</div>
	<p> Prenotazioni </p>
	<table border="1">
		<tr>
			<th>CAMPO</th>
			<th>DATA</th>
			<th>ORA</th>
			<th>NOME CLIENTE</th>
			<th>COGNOME CLIENTE</th>
			<th>TELEFONO</th>
			



		</tr>
		</table>
	<div><form align="center">
			<input value ="Cancella" type="submit" style="background-color: Red; ">
		</form></div>
	<div><form align="right">
			<input value="Esci" type="submit" style="background-color: Red; ">
		</form></div>
</body>
<script>
	document.getElementById("form").addEventListener("click", function(event) {event.preventDefault()});
</script>
</html>