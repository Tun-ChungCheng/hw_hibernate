<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/style.css'  />" />
<meta charset="UTF-8">
<title>Lab01 加入客戶</title>
<style>
form {
	margin: 0 auto;
	width: 600px;
}
</style>
</head>

<body onload="javascript:document.insertCustomerFormA.mId.focus();">
<div align='center'>
	<form name="insertCustomerFormA" action="<c:url value='/lab01/insertCustomer.do' />" method="POST">
		<table border="1">
			<thead>
				<tr bgcolor='tan'>
					<th height="60" colspan="2" align="center" ><h2>新增客戶資料</h2></th>
				</tr>
			</thead>
			<tbody>
				<tr bgcolor='tan'>
					<td width="120" height="40">代號:</td>
					<td width="600" height="40" align="left"><input id='customerId'
						style="text-align: left" name="customerId" value="${param.customerId}" type="text" size="14">
						<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.customerId}</div>
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">密碼:</td>
					<td width="600" height="40" align="left"><input id='pswd'
						style="text-align: left" name="pswd" value="${param.pswd}" type="password" size="14">
						<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.password}</div>
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">姓名:</td>
					<td width="600" height="40" align="left"><input name="name" value="${param.name}"					
						type="text" size="20">
						<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.name}</div>
					</td>
						
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">手機:</td>
					<td width="600" height="40" align="left"><input name="phone" value="${param.phone}"
						type="text" size="20">
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">生日:</td>
					<td width="600" height="40" align="left"><input
						name="birthday" value="${param.birthday}" type="text" size="14"><font color='blue'
						size="-1">&nbsp;&nbsp;格式為yyyy-MM-dd</font>
						<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.birthday}</div>
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">體重:</td>
					<td width="600" height="40" align="left"><input name="weight" value="${param.weight}"
						type="text" size="14">
						<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.weight}</div>
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td height="50" colspan="2" align="center"><input
						type="submit" value="送出"></td>
				</tr>
			</tbody>
		</table>
		<div style="color:#FF0000; display: inline">${ErrorMsg.exception}</div>		
	</form>
	<hr>
	<a href="<c:url value='/lab01/index.jsp' />">回前頁</a>
</div>	
</body>
</html>
