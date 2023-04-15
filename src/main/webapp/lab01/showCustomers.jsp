<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/style.css' />" />
<meta charset="UTF-8">
<title>客戶資料</title>
</head>
<body>
<div align='center'>
<h2>客戶資料清單</h2>
<h4><font color='red'>${DeleteSuccess}&nbsp;</font></h4>
<c:remove var='DeleteSuccess' scope='session' />
<c:choose>
  <c:when test="${empty allCustomers}">
         <h3>查無任何客戶資料</h3>   
  </c:when>
  <c:otherwise>
	<table border='1'>
	  <tr>
	    <th width='120'>客戶代號</th>
	    <th width='120'>密碼</th>
	    <th width='140'>姓名</th>
	    <th width='160'>電話</th>
	    <th width='140'>生日</th>
	    <th width='80'>體重</th>
	    <th width='330'>登錄日期</th>
	  </tr>
	  <c:forEach var="bean" items="${ allCustomers}">
	    <tr>
	      <td>
	          <a href="<c:url value='/lab01/findCustomer.do?key=${bean.id}' />">
	                   ${bean.customerId}
	          </a>
	      </td>
	      <td>${bean.password}</td>
	      <td >${bean.name}</td>
	      <td>${bean.phone}</td>
	      <td align='center'>${bean.birthday}</td>
	      <td align='right'>${bean.weight}</td>
	      <td align='center'>${bean.registerDate}</td>
	    </tr>
	  </c:forEach>
	</table>
  </c:otherwise>
</c:choose>
<p>
<hr>
<a href="<c:url value='/lab01/index.jsp' />">回到客戶管理</a>
</div>
</body>
</html>