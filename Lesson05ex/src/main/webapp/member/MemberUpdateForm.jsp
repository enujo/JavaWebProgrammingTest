<%@ page 
	language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="member"
	scope="request"
	class="spms.vo.Member"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>
</head>
<body>
<h1>ȸ������</h1>
<form action='update' method='post'>
��ȣ: <input type='text' name='no' value='<%=member.getNo()%>' readonly><br>
�̸�: <input type='text' name='name' value='<%=member.getName()%>'><br>
�̸���: <input type='text' name='email' value='<%=member.getEmail()%>'><br>
������: <%=member.getCreatedDate()%><br>
<input type='submit' value='����'>
<input type='button' value='����' 
	onclick='location.href="delete?no=<%=member.getNo()%>";'>
<input type='button' value='���' onclick='location.href="list"'>
</form>
</body>
</html>