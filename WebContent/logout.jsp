<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="service_DB.LoginMemberStub" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

</head>
<body>		
	<%
		String sid = session.getId();
		
		LoginMemberStub stub = new LoginMemberStub();
		
		LoginMemberStub.Logout logout = new LoginMemberStub.Logout();
		logout.setSessionID(sid);
		LoginMemberStub.LogoutResponse resp_logout = stub.logout(logout);
		resp_logout.get_return();
		
		session.invalidate();
		
		out.print("<script>alert(\"You have Logged out !! Thank you !!\")</script>");
		out.print("<meta http-equiv=\"refresh\" content=\"1;url=login.jsp\" />");
 	%>
</body>
</html>