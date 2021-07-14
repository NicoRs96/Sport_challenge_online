<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" style="background-color: White; height: 716px; color: #FF0000; font-size: 20px">
<head>
	
    
<meta charset="ISO-8859-1">
<title>Sport Challenge Online</title>
<style>
      body { 
    background-image: url("image/main.jpg"); 
    background-position: center ; 
    background-size: cover;
    height: 99%;
    width: 99%;
     
}
    </style>
<style><%@include file="/WEB-INF/css/style.css"%></style>

</head>

<body style="height: 658px; ">
  <h1><p class="test">Sport Challenge Online</p></h1>
  <form action="<%=request.getContextPath()%>/index" method="post" style="height: 116px; ">
   <table class="test5">
   <caption>Login</caption>
    <tr>
     <th scope="col" style="width: 159px; font-style: normal; color: Black; background-color: Yellow; font-size: 16px">UserName</th> 
     <td><input type="text" name="username" style="width: 234px; "></td>
    </tr>
    <tr>
     <th scope="col" style="background-color: #FFFF00; font-size: 16px; color: Black">Password</th>
     <td><input type="password" name="password" style="width: 233px; "></td>
    </tr>

   </table><p  style="height: 118px; text-align: center">
   <input type="submit" value="LOGIN" style="color: Black; background-color: Lime; width: 232px;  text-align: center; margin-left: auto; margin-top: 0px; background-position: center center; margin-bottom: 0px; margin-right: auto;"></p>
	</form><input type="submit" value="ISCRIVITI" onclick="window.location='Iscriviti.jsp'" style="width: 224px; background-color: Aqua; background-position: center center; margin-left: 650px" center="">

</body>
</html>