<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<<html style="background-color: White; height: 477px">

<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
</head>
<body>
		<h1><p style="height: px; color: Red" align="center">Sport Challenge Online</h1><form>

			<div>
				Città <input> &emsp;&emsp;Seleziona sport
			<select multiple="true" name="selezionasport" size="1" style="height: 32px; ">
					<option>Tennis</option>
					<option>Calcio</option>
					<option>Basket</option>
					<option>Calcio a 5</option>
					<option>Pallavolo</option>
					<option>Golf</option>
					<option>Padel</option>					
				</select>
				&emsp;&emsp;Data <input type="date" name="mydatetime" id="datefield">&emsp;&emsp;<input
					type="submit" value="Cerca" style="background-color: Aqua; ">
			</div>
			
			
</form>
	<div>
		<table border="1">
		<tr>
			<th>NOME</th>
			<th>COMUNE</th>
			<th>INDIRIZZO</th>
			<th>DESCRIZIONE</th>
			<th>ORARIO</th>
			<th>SPORT</th>
			<th>RENTER</th>	
						
	</div><form align="center">
		<input type="submit" VALUE="SELEZIONA" style="background-color: Green; ">
	</form>
</body>
</html>