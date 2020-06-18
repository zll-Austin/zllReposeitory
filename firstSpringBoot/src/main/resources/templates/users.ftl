<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>用户列表</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>用户名称</td>
			<td>用户地址</td>
			<td>用户爱好</td>
		</tr>
		<!-- 判断users不为空且users数据不为空 -->
		<#if users ??&&(users?size>0)>
			<!-- 遍历books数据 -->
			<#list users as user>
				<tr>
					<td>${user.name}</td>
					<td>${user.address}</td>
 				</tr>
			</#list>
		</#if>
	</table>
</body>
</html>