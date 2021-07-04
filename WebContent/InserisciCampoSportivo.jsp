<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="background-color: White; height: 1000px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
</head>
<body>

	<div><h1 style="height: px; color: Red" align="center">Sport Challenge Online</h1></div>

	<div>
	<p>Inserisci i dati del tuo campo</p>
		<form action="<%=request.getContextPath()%>/InserisciCampoSportivo" method="post" style="height: 409px; ">
	<table>
	<tr>
			<td>Nome del campo</td>
			<td><input type="text" name="NomeCampo" size="20" maxlength="40"></td>		
		</tr>
		<tr>
			<td>Indirizzo</td>
			<td><input type="text" name="Indirizzo" size="20" maxlength="40"></td>		
		</tr>
		<tr>
			<td>Data</td>
			<td>    <input type="date" name="mydatetime">
			</td>		
		</tr>
		<tr>
			<td>Città</td>
			<td><input type="text" name="citta" size="20" maxlength="30"></td>		
		</tr>
		<tr>
			<td>Ora (hh:mm)</td>
			<td><input type="text" name="ora" size="20" maxlength="5"></td>		
		</tr>
		<tr>
			<td>Prezzo</td>
			<td><input type="text" name="prezzo" size="20" maxlength="16"></td>		
		</tr>
		<tr>
			<td>Descrizione</td>
			<td><input type="text" name="descrizione" size="20" maxlength="500" style="height: 133px; width: 230px"></td>		
		</tr>
		<tr>
			<td>Seleziona sport</td>
			<td><select multiple="true" name="selezionasport" size="5">
					<option>Tennis</option>
					<option>Calcio</option>
					<option>Basket</option>
					<option>Calcio a 5</option>
					<option>Pallavolo</option>
					<option>Golf</option>
					<option>Padel</option>					
				</select>
			</td>
		</tr>
		<tr>
			<td>Modalità di pagamento</td>
			<td><select name="ModPagamento" size="1">
					<option>Contanti</option>
					<option>Carta di credito</option>
					<option>Entrambi</option>				
				</select>
			</td>
		</tr>
		<tr>
			<td>Campo per torneo</td>
					<td><input type="checkbox" name="cbTorneo"></td></tr>
		</table>
		
		<td><td><p><input type="submit"; value="Conferma" style="width: 304px; background-color: Lime; height: 46px"></p></td>
	</form>
	</div>
	<div><form align="center">
			<input type="submit" value="Esci" style="background-color: Red;  ">
		</form></div>
</body>
</html>