<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="background-color: White; height: 477px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>

</head>
<body style="height: 1000px;">
		<h1><p style="height: px; color: Red" align="center">Sport Challenge Online<form>
				
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