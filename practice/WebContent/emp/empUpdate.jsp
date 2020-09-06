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
   <h1>사원 변경</h1>
   <form action="empUpdate" method="post">  
    <div><label>employeeId</label> <input name="employee_id" value="${empone.employee_id}" readonly></div>
    <div><label>firstName</label> <input name="first_name" value="${empone.first_name}"></div>
    <div><label>lastName</label> <input name="last_name" value="${empone.last_name}"></div>
    <div><label>email</label> <input name="email" value="${empone.email}"></div>
    <fmt:parseDate value="${empone.hire_date}" pattern="yyyy-MM-dd" var="date_date"/>
    <div><label>hireDate</label> <input name="hire_date" value="${empone.hire_date}"></div>
    <c:forEach items="${depart}" var="name">
   <c:if test= "${ empone.department_id != name.department_id }">
  
    <div><label>${name.department_name}</label> <input type="radio" name="department_id" value="${name.department_id}"></div>
     </c:if>
    <c:if test= "${ empone.department_id == name.department_id }">
    <div><label>${name.department_name}</label> <input type="radio" name="department_id" value="${name.department_id}" checked></div>
    </c:if>
    
    </c:forEach>
    <div><label>jobId</label> <select name="job_id">
    <c:forEach items="${joblist}" var="job">
    <c:if test= "${ empone.job_id != job.job_id}">
    <option value="${job.job_id}"> ${job.job_id}</option>
    </c:if>
    <c:if test= "${empone.job_id == job.job_id}">
    <option value="${job.job_id}" selected> ${job.job_id}</option>
    </c:if>
    </c:forEach>
    	  </select></div>
    <div><label>manager_id</label> <select name="manager_id">
    <c:forEach items="${managerList}" var="manager">
    <c:if test= "${empone.manager_id != manager.manager_id}">
    <option value="${manager.employee_id}"> ${manager.first_name}${manager.last_name}</option>
    </c:if>
    <c:if test= "${empone.manager_id == manager.manager_id}">
    <option value="${manager.employee_id}" selected> ${manager.first_name}${manager.last_name}</option>
    </c:if>
    </c:forEach>
    	       </select></div>
    <button>등록</button>
    </form>
</body>
</html>