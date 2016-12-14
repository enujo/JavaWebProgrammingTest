<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>로그인</title>
</head>
<body>
	
	<form action="login" method="post" align="center">
		<h2>사용자 로그인</h2>
		<table border="1" align="center">
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" name="email">
				</td>						
			</tr>
			<tr>
				<th>암호</th>
				<td>
					<input type="password" name="password">	
				</td>			
			</tr>
			<tr>
				<td align="right" colspan="2">
					<input type="submit" value="로그인">	
				</td>
			</tr>
		</table>
	</form>
</body>
</html>