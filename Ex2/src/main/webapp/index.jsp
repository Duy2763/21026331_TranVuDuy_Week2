<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="fileUpload" method="POST" enctype="multipart/form-data" >
		<input type="file" name="myFiles[]" multiple="multiple">
		<input type="submit" value="Upload" name="upload" id="upload" />
	</form>
</body>
</html>