<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ page import="model.Campo" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html style="background-color: White; height: 274px">


<head>
	<meta charset="ISO-8859-1">
	<title>SPORT CHALLENGE ONLINE</title>
</head>
<body>
<form id="myForm" action="<%=request.getContextPath()%>/GestisciCampiSportivi" method="post" style="height: 409px;">
	<div><h1 style="height: 5px; color: #ff0000" align="center">Sport Challenge Online</h1></div>
	<p> I miei campi </p>
	<div>

		<table border="1">
			<tbody>
			<tr>
				<th>NOME</th>
				<th>CITTA'</th>
				<th>INDIRIZZO</th>
				<th>DESCRIZIONE</th>
				<th>DATA</th>
				<th>DATA</th>
				<th>AFFITABILE</th>
			</tr>
			<c:forEach var= "campo" items ="${campi}">
				<tr>
					<th>${campo.nome}</th>
					<th>${campo.comune}</th>
					<th>${campo.indirizzo}</th>
					<th>${campo.desc}</th>
					<th>${campo.data}</th>
					<th>${campo.ora}</th>
					<th>${campo.isAffittabile == 1} </th>
					<th><input class="campiRenter" type="radio" name="campoRadio" value="${campo.id}"></th>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<input value="Rendi Affittabile" name="affittabile" type="submit" style="background-color: Aqua">
		<input id="refresh" value="Refresh" type="submit" style="background-color: #FF8040">
		<input value="Cancella" type="submit" name="cancella" style="background-color: Red; ">
	</div>
</form>


<p> Prenotazioni </p>
<form  action="<%=request.getContextPath()%>/GestisciCampiSportivi" method="post">
<table border="1">
	<tbody>
	<tr>
		<th>CAMPO</th>
		<th>DATA</th>
		<th>ORA</th>
		<th>NOME CLIENTE</th>
		<th>COGNOME CLIENTE</th>
		<th>TELEFONO</th>
	</tr>
	<c:forEach var= "prenotazione" items ="${prenotazioni}">
		<tr>
			<th>${prenotazione.nomeCampo}</th>
			<th>${prenotazione.data}</th>
			<th>${prenotazione.ora}</th>
			<th>${prenotazione.nomeCliente}</th>
			<th>${prenotazione.cognomeCliente}</th>
			<th>${prenotazione.telefonoCliente}</th>
			<th><input class="prenotazioniCampi" type="radio" name="pRadio" value="${prenotazione.id}"></th>
		</tr>
	</c:forEach>
	</tbody>
</table>
	<div align="left">
		<input value ="Cancella Prenotazione" name="cancellaP" type="submit" style="background-color: Red; ">
		<input id="refreshP" value="Refresh" type="submit" style="background-color: #FF8040">
	</div>
</form>

<div>
	<input value="Esci" onclick="window.location='HomePageRenter.jsp'" type="submit" style="background-color: Red; ">
</div>
</body>
<script>
document.getElementById("refresh").addEventListener("click", function(event) {location.reload()});
document.getElementById("refreshP").addEventListener("click", function(event) {location.reload()});

</script>
</html>