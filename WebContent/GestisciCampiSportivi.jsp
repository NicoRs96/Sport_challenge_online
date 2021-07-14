<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ page import="model.Campo" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="it" style="background-color: White; height: 274px">


<head>
	<meta charset="ISO-8859-1">
	<title>SPORT CHALLENGE ONLINE</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	
</head>
<body>
<form id="myForm" action="<%=request.getContextPath()%>/GestisciCampiSportivi" method="post" style="height: 409px;">
	<div><h1 class="test">Sport Challenge Online</h1></div>
	<p> I miei campi </p>
	<div>

		<table border="1">
		<caption>I miei campi</caption>
			<tbody>
			<tr>
				<th scope="col">NOME</th>
				<th scope="col">CITTA'</th>
				<th scope="col">INDIRIZZO</th>
				<th scope="col">DESCRIZIONE</th>
				<th scope="col">DATA</th>
				<th scope="col">DATA</th>
				<th scope="col">AFFITABILE</th>
			</tr>
			<c:forEach var= "campo" items ="${campi}">
				<tr>
					<th scope="col">${campo.nome}</th>
					<th scope="col">${campo.comune}</th>
					<th scope="col">${campo.indirizzo}</th>
					<th scope="col">${campo.desc}</th>
					<th scope="col">${campo.data}</th>
					<th scope="col">${campo.ora}</th>
					<th scope="col">${campo.isAffittabile == 1} </th>
					<th scope="col"><input class="campiRenter" type="radio" name="campoRadio" value="${campo.id}"></th>
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
	<caption>Prenotazioni</caption>
	<tbody>
	<tr>
		<th scope="col">CAMPO</th>
		<th scope="col">DATA</th>
		<th scope="col">ORA</th>
		<th scope="col">NOME CLIENTE</th>
		<th scope="col">COGNOME CLIENTE</th>
		<th scope="col">TELEFONO</th>
	</tr>
	<c:forEach var= "prenotazione" items ="${prenotazioni}">
		<tr>
			<th scope="col">${prenotazione.nomeCampo}</th>
			<th scope="col">${prenotazione.data}</th>
			<th scope="col">${prenotazione.ora}</th>
			<th scope="col">${prenotazione.nomeCliente}</th>
			<th scope="col">${prenotazione.cognomeCliente}</th>
			<th scope="col">${prenotazione.telefonoCliente}</th>
			<th scope="col"><input class="prenotazioniCampi" type="radio" name="pRadio" value="${prenotazione.id}"></th>
		</tr>
	</c:forEach>
	</tbody>
</table>
	<div class="test2">
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