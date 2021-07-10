<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
</head>
<body style="background-color: White; height: 1000px">
	<p> I miei tornei </p>
	<form id="myForm" action="<%=request.getContextPath()%>/GestisciTornei" method="post">
	<div>
		<table border="1">
			<tbody>
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>DATA</th>
			<th>ORA</th>
			<th>PREZZO</th>
			<th>ETA MINIMA</th>
			<th>NUMERO MINIMO</th>
			<th>SCADENZA PRENOTAZIONI</th>
		</tr>
		<c:forEach var= "torneo" items ="${tornei}">
			<tr>
				<th>${torneo.id}</th>
				<th>${torneo.nome}</th>
				<th>${torneo.data.toString()}</th>
				<th>${torneo.ora}</th>
				<th>${torneo.prezzo}</th>
				<th>${torneo.etaMin}</th>
				<th>${torneo.numMinPart}</th>
				<th>${torneo.dataScadenza} </th>
				<th><button  class="torneiRenter" type="submit" name="torneoId" value="${torneo.id}">Ottieni iscritti</button></th>
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
			<tbody>
		<tr>
			<th>ID</th>
			<th>NOME</th>
			<th>COGNOME</th>
			<th>LIVELLO</th>
			<th>CONFERMATO</th>
		</tr>

		<c:forEach var= "iscritto" items ="${iscritti}">
		<tr>
			<th>${iscritto.id}</th>
			<th>${iscritto.nome}</th>
			<th>${iscritto.cognome}</th>
			<th>${iscritto.livello}</th>
			<th>${iscritto.isConfermato}</th>
			<th><button  class="torneiRenter" type="submit" name="conferma" value="${iscritto.id}" style="background-color: #00FF00; ">Conferma</button>
				<button  class="torneiRenter" type="submit" name="cancella" value="${iscritto.id}"  style="background-color: Red; ">Cancella</button>
			</th>
		</tr>
		</c:forEach>
			</tbody>
		</table>
	</div>
	</form>
	<form action="<%=request.getContextPath()%>/GestisciTornei" method="post">
	<div align="center">
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