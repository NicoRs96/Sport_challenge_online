<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="background-color: White; height: 1000px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
</head>
<body>
			<div><h1><p style="height: px; color: Red" align="center">Sport Challenge Online</p></h1></div>
	<p> Seleziona un campo </p>
	<td><select name=Campi size="1">
									
				</select>
			</td>
	<form action="<%=request.getContextPath()%>/CreaTorneo" method="post" style="height: 409px; ">
	<table>
	<tr>
			<td>Nome</td>
			<td><input type="text" name="Nome" size="20" maxlength="25"></td>		
		</tr>
		
		<tr>
			<td>Scegli la data</td>
			<td>    <input type="date" name="mydatetime">
			</td>		
		</tr>
		<tr>
			<td>Ora</td>
			<td><input type="text" name="ora" size="20" maxlength="5"></td>		
		</tr>
		<tr>
			<td>Età minima</td>
			<td><input type="text" name="eta" size="20" maxlength="2"></td>		
		</tr>
		<tr>
			<td>Numero minimo di partecipanti</td>
			<td><input type="text" name="numMin" size="20" maxlength="16"></td>		
		</tr>
		<tr>
			<td>Scegli la data di scadenza prenotazione</td>
			<td>    <input type="date" name="mydatetime">
			</td>		
		</tr>
		<tr>
			<td>Prezzo</td>
			<td>    <input type="text" name="prezzo" size="20" maxlength="16">
			</td>		
		</tr>
		<tr>
			<td>Descrizione</td>
			<td>    <input type="text" name="descrizione" size="20" maxlength="500" style="height: 89px; width: 214px">
			</td>		
		</tr>
		
		<tr>
			<td>Modalità di pagamento</td>
			<td><select name="ModPagamento" size="1">
					<option>Contanti</option>
					<option>Carta di credito</option>
				</select>
			</td>
		</tr>
	</table>
		 
		<td><td><p><input type="submit"; value="Conferma" style="width: 304px; background-color: Lime; height: 46px"></p></td>
	</form>
</body>
</html>