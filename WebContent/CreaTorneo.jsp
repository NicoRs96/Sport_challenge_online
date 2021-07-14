<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" style="background-color: White; height: 1000px">
<head>
<meta charset="ISO-8859-1">
<title>SPORT CHALLENGE ONLINE</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body>
			<div><h1><p class="test">Sport Challenge Online</p></h1></div>
	<p> Seleziona un campo </p>
	<td><select name=Campi size="1">
									
				</select>
			</td>
	<form action="<%=request.getContextPath()%>/CreaTorneo" method="post" style="height: 409px; ">
	<table>
	<caption>Creazione del torneo</caption>
	<tr>
			<th  scope="col">Nome</th>
			<td><input type="text" name="Nome" size="20" maxlength="25"></td>		
		</tr>
		
		<tr>
			<th scope="col">Scegli la data</th>
			<td>    <input type="date" name="mydatetime">
			</td>		
		</tr>
		<tr>
			<th  scope="col">Ora</th>
			<td><input type="text" name="ora" size="20" maxlength="5"></td>		
		</tr>
		<tr>
			<th scope="col">Età minima</th>
			<td><input type="text" name="eta" size="20" maxlength="2"></td>		
		</tr>
		<tr>
			<th scope="col">Numero minimo di partecipanti</th>
			<td><input type="text" name="numMin" size="20" maxlength="16"></td>		
		</tr>
		<tr>
			<th scope="col">Scegli la data di scadenza prenotazione</th>
			<td>    <input type="date" name="mydatetime">
			</td>		
		</tr>
		<tr>
			<th scope="col">Prezzo</th>
			<td>    <input type="text" name="prezzo" size="20" maxlength="16">
			</td>		
		</tr>
		<tr>
			<th scope="col">Descrizione</th>
			<td>    <input type="text" name="descrizione" size="20" maxlength="500" style="height: 89px; width: 214px">
			</td>		
		</tr>
		
		<tr>
			<th scope="col">Modalità di pagamento</th>
			<td><select name="ModPagamento" size="1">
					<option>Contanti</option>
					<option>Carta di credito</option>
				</select>
			</td>
		</tr>
	</table>
		 
		<td><td><p><input type="submit"; value="Conferma" style="width: 304px; background-color: Lime; height: 46px"></p></td>
	</form>
		<input type="submit" value="esci" onclick="window.location='HomePageRenter.jsp'"style="background-color: Red; ">
	
</body>
</html>