<%@ page 
	language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<body>
	<%@ include file="/Header.jsp" %>
	<form>
		<ul>
			<del title="����ô�̾�" ><b>����ô!</b></del>
			<ins>����!</ins>
			<li>
				<a title="memberList�� �̵�." href="<%=request.getContextPath() %>/member/list">list</a>
				<a title="memberList�� �̵�." href="<%=request.getContextPath() %>/member/add">add</a>
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
