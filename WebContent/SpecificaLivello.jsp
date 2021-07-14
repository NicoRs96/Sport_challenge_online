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
		<div style="height: 291px; ">
		<form style="height: 242px; " action="<%=request.getContextPath()%>/SpecificaLivello" method="get">
		
		Scegli il tuo livello&emsp;&emsp;&emsp;&emsp;
		Seleziona sport
		
			<select multiple="true" name="selezionasport" size="1" style="height: 51px; ">
					<option>Tennis</option>
					<option>Calcio</option>
					<option>Basket</option>
					<option>Calcio a 5</option>
					<option>Pallavolo</option>
					<option>Golf</option>
					<option>Padel</option>					
				</select>
	
	
			<div><p></p><input type="radio" name="livello" value="Dilettante">Dilettante&emsp;&emsp; <input type="radio" name="livello" value="Esperto" >Esperto &emsp;&emsp;<input type="radio" name="livello" value="Professionista">Professionista</div>
			<div><p></p><input type="submit" value="conferma" style="background-color: Green; "></div>
		</form>
	</div>
	
		<input type="submit" value="esci" onclick="window.location='HomePageSportman.jsp'" style="background-color: Red; ">
	
</body>
</html>