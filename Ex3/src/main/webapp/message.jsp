<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
</head>
<body>
<%
	String message = request.getAttribute("message") + "";
%>
<h1><%= message %></h1>
</body>
</html>