<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>

<link rel='stylesheet' href="<c:url value='/css/style.css'  />" />
<meta charset="UTF-8">
<title>客戶資料管理</title>
</head>  
<body>
<p>&nbsp;</p>
<hr>
<div class='center' >
<h2>客戶管理</h2>
<hr>
<a href="<c:url value='/lab01/InsertCustomerForm.jsp' />" >客戶資料新增</a><br>
<a href="<c:url value='/lab01/queryCustomer.do' />" >客戶資料查詢</a><br>
<br>
<a href="<c:url value='/index.jsp' />">回前頁</a>
</div>
</body>
</html>