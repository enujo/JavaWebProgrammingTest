<%@page import="tistory.luahius.spms.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page 
	language="java" 
	contentType="text/html; charset=euc-kr"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원 목록</title>
</head>
<body>
	<h1>회원목록</h1>
	<p><a href='add'>신규 회원</a></p>
	<%
		ArrayList<Member> members = (ArrayList<Member>)request.getAttribute(	"members");
		for(Member member : members) {
	%>
			<%=member.getNo()%>,
			<a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>,
			<%=member.getEmail()%>,
			<%=member.getCreatedDate()%>
			<a href='delete?no=<%=member.getNo()%>'>[삭제]</a><br>
	<%} %>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>이름</th>
				<th>메일</th>
				<th>가입날짜</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="m" items="${ members}">
				<tr>
					<td align="center">${m.no }</td>
					<td><a href="update?no=${m.no }">${m.name }</a></td>
					<td>${m.email}</td>
					<td>${m.createdDate }</td>
					<td><a href="dete?no=${m.no }">[삭제]</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>