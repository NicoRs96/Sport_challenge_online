<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="it" style="background-color: White; height: 477px">

<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body>

		<h1><p class="test">Sport Challenge Online</h1>
		<form action="<%=request.getContextPath()%>/CercaCampo" method="post">
			<input type="text" required name="citta" value="Città" />
			<input value="Data" name="data" type="date" required>
			<select multiple="true" name="sport" size="1" style="height: 32px; " required>
					<option>Tennis</option>
					<option>Calcio</option>
					<option>Basket</option>
					<option>Calcio a 5</option>
					<option>Pallavolo</option>
					<option>Golf</option>
					<option>Padel</option>					
			</select>
			<input type="submit" value="Cerca" name="cerca" style="background-color: Aqua; ">
			</div>
			
			
</form>
		<form action="<%=request.getContextPath()%>/CercaCampo" method="post">
	<div>
		<table border="1">
		<caption>Campi disponibili</caption>
			<tbody>
		<tr>
			<th scope="col">NOME</th>
			<th scope="col">COMUNE</th>
			<th scope="col">INDIRIZZO</th>
			<th  scope="col">DESCRIZIONE</th>
			<th scope="col">ORARIO</th>
			<th scope="col">SPORT</th>
			<th scope="col">RENTER</th>
		</tr>
		<c:forEach var= "campo" items ="${campi}">
			<tr>
				<th scope="col">${campo.nome}</th>
				<th scope="col">${campo.comune}</th>
				<th scope="col">${campo.indirizzo}</th>
				<th scope="col">${campo.desc}</th>
				<th scope="col">${campo.ora}</th>
				<th scope="col">${campo.sport} </th>
				<th scope="col">${campo.renter} </th>
				<th scope="col"><input type="radio" name="campoId" value="${campo.id}"></th>
			</tr>
		</c:forEach>
			</tbody>
		</table>
						
	</div>

		<input type="submit" name="seleziona" VALUE="SELEZIONA" style="background-color: Green; ">
	</form>
		<input type="submit" value="esci" onclick="window.location='HomePageSportman.jsp'" style="background-color: Red; ">
	
</body>
</html>