<%@ page 
	language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<body>
	<%@ include file="/Header.jsp" %>
	<form>
		<ul>
			<del title="Áö¿îÃ´ÀÌ¾ä" ><b>Áö¿îÃ´!</b></del>
			<ins>¹ØÁÙ!</ins>
			<li>
				<a title="memberList·Î ÀÌµ¿." href="<%=request.getContextPath() %>/member/list">list</a>
				<a title="memberList·Î ÀÌµ¿." href="<%=request.getContextPath() %>/member/add">add</a>
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
