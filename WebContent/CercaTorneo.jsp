<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<<html style="background-color: White; height: 477px">
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
			<tbody>
				<tr>
					<th>NOME</th>
					<th>CAMPO</th>
					<th>DATA</th>
					<th>ORA</th>
					<th>ETA MINIMA</th>
					<th>NUM MINIMO PARTECIPANTI</th>
					<th>DATA SCADENZA</th>
					<th>PREZZO</th>
				</tr>
				<c:forEach var= "torneo" items ="${tornei}">
					<tr>
						<th>${torneo.nome}</th>
						<th>${torneo.campo}</th>
						<th>${torneo.data.toString()}</th>
						<th>${torneo.ora}</th>
						<th>${torneo.etaMin}</th>
						<th>${torneo.numMinPart}</th>
						<th>${torneo.dataScadenza} </th>
						<th>${torneo.prezzo}</th>
						<th><input type="radio" name="torneoId" value="${torneo.id}"></th>
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