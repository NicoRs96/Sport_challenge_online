<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html  lang="it" style="background-color: White; height: 477px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body style="height: 1000px;">
			<h1><p class="test">Sport Challenge Online</h1>

		<form align="center" action="<%=request.getContextPath()%>/MieiEventi" method="post">
		<div>
	
		<table border="1">
	<caption>Campi prenotati</caption>
			<tbody>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">NOME</th>
					<th scope="col">COMUNE</th>
					<th scope="col">INDIRIZZO</th>
					<th scope="col">DESCRIZIONE</th>
					<th scope="col">DATA</th>
					<th scope="col">ORARIO</th>
					<th scope="col">SPORT</th>
				</tr>

				<c:forEach var= "campo" items ="${campi}">
					<tr>
						<th scope="col">${campo.id}</th>
						<th scope="col">${campo.nome}</th>
						<th scope="col">${campo.comune}</th>
						<th scope="col">${campo.indirizzo}</th>
						<th scope="col">${campo.desc}</th>
						<th scope="col">${campo.data}</th>
						<th scope="col">${campo.ora}</th>
						<th scope="col">${campo.sport} </th>
						<th scope="col"><input type="radio" name="campoId" value="${campo.id}"></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="submit" name="cancellaC" VALUE="CANCELLA" style="background-color: Red; ">
			<input id="refreshC" value="Refresh" type="submit" style="background-color: #FF8040">
		</div>
	</form>

	<form align="center" action="<%=request.getContextPath()%>/MieiEventi" method="post">
	<div>
		<table border="1">
		<caption>Iscrizioni ai tornei</caption>
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