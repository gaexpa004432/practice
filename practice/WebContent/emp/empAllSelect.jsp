<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<c:forEach items="${selall }" var="emp">
<tr><td><a href="empUpdate?employee_id=${emp.employee_id}"> ${emp.employee_id}</a></td><td> ${emp.first_name }</td>
<td> ${emp.last_name }</td><td> ${emp.job_id }</td>
<td> ${emp.hire_date }</td><td> ${emp.salary }</td>
<td> ${emp.phone_number }</td><td> ${emp.manager_id }</td>
<td> ${emp.email }</td><td> ${emp.department_id }</td>
<td> ${emp.commission_pct }</td></tr>

</c:forEach>
${ 1 == 2  }
</table>
</body>
</html>