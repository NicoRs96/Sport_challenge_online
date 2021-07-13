<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html style="background-color: White; height: 477px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body style="height: 1000px;">
			<h1><p class="test">Sport Challenge Online</h1>

		<form align="center" action="<%=request.getContextPath()%>/MieiEventi" method="post">
		<div>CAMPI PRENOTATI
	
		<table border="1">
			<tbody>
				<tr>
					<th>ID</th>
					<th>NOME</th>
					<th>COMUNE</th>
					<th>INDIRIZZO</th>
					<th>DESCRIZIONE</th>
					<th>DATA</th>
					<th>ORARIO</th>
					<th>SPORT</th>
				</tr>

				<c:forEach var= "campo" items ="${campi}">
					<tr>
						<th>${campo.id}</th>
						<th>${campo.nome}</th>
						<th>${campo.comune}</th>
						<th>${campo.indirizzo}</th>
						<th>${campo.desc}</th>
						<th>${campo.data}</th>
						<th>${campo.ora}</th>
						<th>${campo.sport} </th>
						<th><input type="radio" name="campoId" value="${campo.id}"></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="submit" name="cancellaC" VALUE="CANCELLA" style="background-color: Red; ">
			<input id="refreshC" value="Refresh" type="submit" style="background-color: #FF8040">
		</div>
	</form>

	<form align="center" action="<%=request.getContextPath()%>/MieiEventi" method="post">
	<div>ISCRIZIONE AI TORNEI
		<table border="1">
			<tbody>
				<tr>
					<th>ID</th>
					<th>NOME</th>
					<th>CITTA</th>
					<th>DATA</th>
					<th>ORA</th>
					<th>DESCRIZIONE</th>
					<th>PREZZO</th>
					<th>SPORT</th>
				</tr>

				<c:forEach var= "torneo" items ="${tornei}">
					<tr>
						<th>${torneo.id}</th>
						<th>${torneo.nome}</th>
						<th>${torneo.citta}</th>
						<th>${torneo.data}</th>
						<th>${torneo.ora}</th>
						<th>${torneo.desc}</th>
						<th>${torneo.prezzo}</th>
						<th>${torneo.sport} </th>
						<th><input type="radio" name="torneoId" value="${torneo.id}"></th>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<input type="submit" name="cancellaT" value="CANCELLA" style="background-color: Red; ">
		<input id="refreshP" value="RefreshT" type="submit" style="background-color: #FF8040">
	</div>
	</form>

	<input type="submit" name="exit" onclick="window.location='HomePageSportman.jsp'" value="esci" style="background-color: Red;">

</body	>
<script>
	document.getElementById("refreshC").addEventListener("click", function(event) {location.reload()});
	document.getElementById("refreshT").addEventListener("click", function(event) {location.reload()});
</script>
</html>