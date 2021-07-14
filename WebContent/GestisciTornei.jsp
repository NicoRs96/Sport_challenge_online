<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body style="background-color: White; height: 1000px">
	<p> I miei tornei </p>
	<form id="myForm" action="<%=request.getContextPath()%>/GestisciTornei" method="post">
	<div>
		<table border="1">
		<caption>Tornei</caption>
			<tbody>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">NOME</th>
			<th scope="col">DATA</th>
			<th scope="col">ORA</th>
			<th scope="col">PREZZO</th>
			<th scope="col">ETA MINIMA</th>
			<th scope="col">NUMERO MINIMO</th>
			<th scope="col">SCADENZA PRENOTAZIONI</th>
		</tr>
		<c:forEach var= "torneo" items ="${tornei}">
			<tr>
				<th scope="col">${torneo.id}</th>
				<th scope="col">${torneo.nome}</th>
				<th scope="col">${torneo.data.toString()}</th>
				<th scope="col">${torneo.ora}</th>
				<th scope="col">${torneo.prezzo}</th>
				<th scope="col">${torneo.etaMin}</th>
				<th scope="col">${torneo.numMinPart}</th>
				<th scope="col">${torneo.dataScadenza} </th>
				<th scope="col"><button  class="torneiRenter" type="submit" name="torneoId" value="${torneo.id}">Ottieni iscritti</button></th>
			</tr>
		</c:forEach>
			</tbody>
		</table>
	</div>
	</form>
	<div style="height: 5%"></div>
	<form id="myForm2" action="<%=request.getContextPath()%>/GestisciTornei" method="post">
	<div>
		<div>ISCRITTI: ${iscritti.size()}</div>
			<form>
		<table border="1">
		<caption>Iscritti</caption>
			<tbody>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">NOME</th>
			<th scope="col">COGNOME</th>
			<th scope="col">LIVELLO</th>
			<th scope="col">CONFERMATO</th>
		</tr>

		<c:forEach var= "iscritto" items ="${iscritti}">
		<tr>
			<th scope="col">${iscritto.id}</th>
			<th scope="col">${iscritto.nome}</th>
			<th scope="col">${iscritto.cognome}</th>
			<th scope="col">${iscritto.livello}</th>
			<th scope="col">${iscritto.isConfermato}</th>
			<th scope="col"><button  class="torneiRenter" type="submit" name="conferma" value="${iscritto.id}" style="background-color: #00FF00; ">Conferma</button>
				<button  class="torneiRenter" type="submit" name="cancella" value="${iscritto.id}"  style="background-color: Red; ">Cancella</button>
			</th>
		</tr>
		</c:forEach>
			</tbody>
		</table>
	</div>
	</form>
	<form action="<%=request.getContextPath()%>/GestisciTornei" method="post">
	<div class="test3">
		<input id="refresh" value="Refresh" type="submit" style="background-color: #FF8040">
		
	</div>
	</form>
	<input name="exit" value="Exit" onclick="window.location='HomePageRenter.jsp'" type="submit" style="background-color: #fa0000">
</body>
<script>
	//document.getElementById("myForm").addEventListener("click", function (event) {
	//	event.preventDefault();
	//})
	document.getElementById("refresh").addEventListener("click", function(event) {location.reload()});
</script>
</html>