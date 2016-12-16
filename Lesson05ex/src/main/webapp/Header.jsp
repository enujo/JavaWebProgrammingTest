<%@ page language="java" contentType="text/html; charset=EUC-KR" 
	pageEncoding="EUC-KR"%>
<%-- <jsp:useBean id="member" scope="session" class="spms.vo.Member"/> --%>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding: 5px;">
	SPMS(Simple Project Management System)
	<c:if test="${member.email != null }">
		<span style="float:right;">
			${member.name }
			
			<a style="color:white;" 
		  	href="<c:url value='/auth/logout'/>">로그아웃</a>
		</span>
	</c:if>
	<c:if test="${member.email == null }">
		<a style="color:white;" 
		  	href="<c:url value='/auth/login'/>">로그인</a>
	</c:if>
<%--
 	<% if (member.getEmail() != null) { %>
	<span style="float:right;">
	<%=member.getName()%>
	<a style="color:white;" 
	  href="<%=request.getContextPath()%>/auth/logout">로그아웃</a>
	</span>
	<% } %>
 --%>
 </div>