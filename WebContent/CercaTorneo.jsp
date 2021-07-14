<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<<html lang ="it" style="background-color: White; height: 477px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body>
		<h1><p class="test">Sport Challenge Online</h1>
		<form  action="<%=request.getContextPath()%>/CercaTorneo" method="post">
			<div>
				<input type="text" required name="citta" value="Città" />
				<input value="Data" name="data" type="date" required>
				<input type="submit" value="Cerca" name="cerca" style="background-color: Aqua; ">
			</div>		
			
</form>

		<form  action="<%=request.getContextPath()%>/CercaTorneo" method="post">
	<div>
		<table border="1">
		<caption>Tornei disponibili</caption>
			<tbody>
				<tr>
					<th scope="col">NOME</th>
					<th scope="col">CAMPO</th>
					<th scope="col">DATA</th>
					<th scope="col">ORA</th>
					<th scope="col">ETA MINIMA</th>
					<th scope="col">NUM MINIMO PARTECIPANTI</th>
					<th scope="col">DATA SCADENZA</th>
					<th scope="col">PREZZO</th>
				</tr>
				<c:forEach var= "torneo" items ="${tornei}">
					<tr>
						<th scope="col">${torneo.nome}</th>
						<th scope="col">${torneo.campo}</th>
						<th scope="col">${torneo.data.toString()}</th>
						<th scope="col">${torneo.ora}</th>
						<th scope="col">${torneo.etaMin}</th>
						<th scope="col">${torneo.numMinPart}</th>
						<th scope="col">${torneo.dataScadenza} </th>
						<th scope="col">${torneo.prezzo}</th>
						<th scope="col"><input type="radio" name="torneoId" value="${torneo.id}"></th>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	
						
	</div>
		<input type="submit" name="conferma" VALUE="CONFERMA" style="background-color: Green; ">
	</form>
		<input type="submit" value="esci" onclick="window.location='HomePageSportman.jsp'" style="background-color: Red; ">
	
</body>
</html>