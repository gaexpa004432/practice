<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<form action="hello">
		<input name="department_id">
	</form>
	<table border="1">
		<tr>
			<td>부서번호</td>
			<td>${dept.department_id}</td>
		</tr>
		<tr>
			<td>부서번호</td>
			<td>${dept.department_name}</td>
		</tr>
		<tr>
			<td>부서번호</td>
			<td>${dept.manager_id}</td>
		</tr>
		<tr>
			<td>부서번호</td>
			<td>${dept.location_id}</td>
		</tr>
	</table>
</body>
</html>