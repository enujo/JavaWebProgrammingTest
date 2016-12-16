<%@page import="spms.vo.Member"%>
<%@ page 
	language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="member"
  scope="session"
  class="spms.vo.Member"/>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding: 5px;">
SPMS(Simple Project Management System)
<% if (member.getEmail() != null) { %>
<span style="float:right;">
<%=member.getName()%>
<a style="color:white;" 
  href="<%=request.getContextPath()%>/auth/logout">·Î±×¾Æ¿ô</a>
</span>
<% } %>
</div>