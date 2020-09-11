<%@page import="dept.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptSelectAll.jsp</title>
</head>
<body>
	<table border="1">
		<% 
	ArrayList<DeptVO> list = (ArrayList<DeptVO>)request.getAttribute("list");
	for(DeptVO dept : list ) {
%>
		<tr>
			<td><a
				href="deptSelect?department_id=<%=dept.getDepartment_id()%>"><%=dept.getDepartment_id()%></a></td>
			<td><%=dept.getDepartment_name()%></td>
		</tr>
		<% } %>
	</table>
	
<%-- <c:forEach begin="${ paging.startPage }" end="${ paging.endPage}" var="i">
	<a href="deptSelectAll?p=${i}">${i }</a>
</c:forEach> --%>
<form name="searchForm">
	<input type="hidden" name="p" value="1">
	<input name="department_name" value="${ param.department_name }">
	<button>검색</button>
</form>
<div >
<my:paging paging="${ paging }" jsfunc="gopage" />
</div>
<script>
	function gopage(p){
		searchFrm.p.value = p;
		searchFrm.submit();
		// location.href="deptSelectAll?p="+ p;
	}
</script>
</body>
</html>