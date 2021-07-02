<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="background-color: White; height: 716px; color: #FF0000; font-size: 20px">
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
</head>

<body style="height: 658px; ">
  <h1><p align="center" style="height: 132px; ">Sport Challenge Online</p></h1>
  <form action="<%=request.getContextPath()%>/index" method="post" style="height: 116px; ">
   <table style="with: 100%; width: 178px; border="1" align="center">
    <tr>
     <td style="width: 159px; font-style: normal; color: Black; background-color: Yellow; font-size: 16px">UserName</td> 
     <td><input type="text" name="username" style="width: 234px; "></td>
    </tr>
    <tr>
     <td style="background-color: #FFFF00; font-size: 16px; color: Black">Password</td>
     <td><input type="password" name="password" style="width: 233px; "></td>
    </tr>

   </table><p align="center" style="height: 118px;">
   <input type="submit" value="LOGIN" style="color: Black; background-color: Lime; width: 232px;  text-align: center; margin-left: auto; margin-top: 0px; background-position: center center; margin-bottom: 0px; margin-right: auto;"></p>
	</form><input type="submit" value="ISCRIVITI" onclick="window.location='Iscriviti.jsp'" style="width: 224px; background-color: Aqua; background-position: center center; margin-left: 650px" center="">

</body>
</html>