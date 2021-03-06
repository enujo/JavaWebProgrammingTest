<%-- 
	<%@page import="spms.vo.Member"%>
	<%@page import="java.util.ArrayList"%>
 --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>회원 목록</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>회원목록</h1>
	<p><a href='add'>신규 회원</a></p>
	<%-- 
	<jsp:useBean id="members"	이름
	  scope="request"				request영역 안에
	  class="java.util.ArrayList" 
	  type="java.util.ArrayList<spms.vo.Member>"/>
	<%
	for(Member member : members) {
	%>
	<%=member.getNo()%>,
	<a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>,
	<%=member.getEmail()%>,
	<%=member.getCreatedDate()%>
	<a href='delete?no=<%=member.getNo()%>'>[삭제]</a><br>
	<%} %>	
	 --%>
	<c:forEach var="member" items="${members}" >
		${member["no"] },
		<a href='update?no=${member["no"]}'>${member.name }</a>,
		${member.email },
		${member.createdDate }
		<a href='delete?no=${member.no}'>[삭제]</a><br>	
	</c:forEach>
	<%-- <jsp:include page="/Tail.jsp"/> --%>		<!-- actiontag -->
	<c:import url="/Tail.jsp" />
</body>
</html>