<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" style="background-color: White; height: 477px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>


</head>
<body style="height: 1000px;">
		<h1><p class="test">Sport Challenge Online<form>
				
			</form></p></h1>
		
		Seleziona sport
		<form action="<%=request.getContextPath()%>/SportPreferito" method="get">
			<select multiple="true" name="selezionasport" size="1">
					<option>Tennis</option>
					<option>Calcio</option>
					<option>Basket</option>
					<option>Calcio a 5</option>
					<option>Pallavolo</option>
					<option>Golf</option>
					<option>Padel</option>					
				</select>

	
			<input type="submit" value="conferma" style="background-color: Green; ">
		</form></div>
		<input type="submit" value="esci" onclick="window.location='HomePageSportman.jsp'" style="background-color: Red; ">
	
</body>
</html>