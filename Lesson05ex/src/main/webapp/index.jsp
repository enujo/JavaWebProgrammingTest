<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
	<%@ include file="/Header.jsp" %>
	<form>
		<ul>
			<del title="지운척이얌" ><b>지운척!</b></del>
			<ins>밑줄!</ins>
			<li>
				<a title="memberList로 이동." href="<%=request.getContextPath() %>/member/list">list</a>
			</li>
			<li></li>
			<li></li>			
		</ul>
		<ol>
			<li>
				<a href="<%=request.getContextPath() %>/member/list">list</a>
			</li>
		</ol>
	</form>
	<%@ include file="/Tail.jsp" %>
</body>
</html>
