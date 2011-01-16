<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<table border="1">
			<tr>
				<td>序号</td>
				<td>用户名</td>
				<td>密码</td>
				<td>操作</td>
			</tr>
			<s:iterator id="user" value="list" status="st">
			<tr>
				<td><s:property value="(#st.index+1)" /></td>
				<td><s:property value="name" /></td>
				<td><s:property value="password" /></td>
				<td><s:a href="userEdit.shtml?id=%{id}">修改</s:a>||<s:a href="userDel.shtml?id=%{id}">删除</s:a></td>
			</tr>
			</s:iterator>
			
		</table>
		<br />
		<s:a href="./">添加用户</s:a>
	</body>
</html>
