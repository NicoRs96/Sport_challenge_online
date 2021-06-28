<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="background-color: White; height: 398px">
<head>

<meta charset="ISO-8859-1">
<title>Sport Challenge Online</title>
<style>
      body { 
    background-image: url("file:///C:/Users/Paolo%20C/Desktop/Sport_challenge_online.git/trunk/src/logic/view/main.jpg"); 
    background-position: center ; 
    background-size: 100% 100%; 
}
    </style>
</head>

<body>
 <div align="center">
  <h1>Sport Challenge Online</h1>
  <form action="<%=request.getContextPath()%>/index" method="post">
   <table style="with: 100%">
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>

   </table>
   <input type="submit" value="LOGIN" />
  </form>
 </div>
</body>
</html>