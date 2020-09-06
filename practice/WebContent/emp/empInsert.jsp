<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
	label { display: inline-block; width : 100px}
</style>
</head>
<body>
   <h1>사원등록</h1>
   <form action="empInsert" method="post">  
    <div><label>employeeId</label> <input name="employee_id"></div>
    <div><label>firstName</label> <input name="first_name"></div>
    <div><label>lastName</label> <input name="last_name"></div>
    <div><label>email</label> <input name="email"></div>
    <div><label>hireDate</label> <input type="date" name="hire_date"></div>
    <table border="1">
    <c:forEach items="${depart}" var="name">
    <tr><td>
    <label>${name.department_name}</label> <input type="radio" name="department_id" value="${name.department_id}">
    </td></tr>
    </c:forEach>
    </table>
    <div><label>jobId</label> <select name="job_id">
    <c:forEach items="${joblist}" var="job">
    <option value="${job.job_id}"> ${job.job_id}</option>
    </c:forEach>
    	  </select></div>
    <div><label>manager_id</label> <select name="manager_id">
    <c:forEach items="${managerList}" var="manager">
    <option value="${manager.employee_id}"> ${manager.first_name}${manager.last_name}</option>
    </c:forEach>
    	       </select></div>
    <button>등록</button>
    </form>
</body>
</html>