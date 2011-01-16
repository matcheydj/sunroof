<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>My JSP 'index.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<s:if test="%{user==null}">
			<s:form action="userAdd">
				<s:textfield name="user.name" label="用户名"></s:textfield>
				<br>
				<s:password name="user.password" label="密码" showPassword="true"></s:password>

				<s:submit></s:submit>
			</s:form>
		</s:if>
		<s:else>
			<s:form action="userUpdate">
				<s:textfield name="user.name" label="用户名"></s:textfield>
				<br>
				<s:password name="user.password" label="密码" showPassword="true"></s:password>
				<br>
				<s:submit></s:submit>
			</s:form>
		</s:else>

		<br />
		<s:a href="userList.shtml">用户列表</s:a>
	</body>
</html>
