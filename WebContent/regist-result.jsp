<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service_DB.RegisterMemberStub" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>
		<%
		try {
			request.setCharacterEncoding("UTF-8");
			String text_account = request.getParameter("account_regist");
			String text_password = request.getParameter("password_regist");
			String text_name = request.getParameter("name_regist");
			String text_email = request.getParameter("email_regist");
			String text_phone = request.getParameter("phone_regist");
			String text_address = request.getParameter("address_regist");
						
			if(text_account == "" 
					|| text_password == "" 
					|| text_name == "" 
					|| text_email == "" 
					|| text_phone == "" 
					|| text_address == ""
					
					|| text_account == null
					|| text_password == null
					|| text_name == null
					|| text_email == null
					|| text_phone == null
					|| text_address == null)
			{
				out.print("Input error !! please wait for 3 seconds ... we will bring you back to regist page!");
				//out.print("input error !!");
				out.print("<meta http-equiv=\"refresh\" content=\"3;url=javascript:history.go(-1)\" />");
				//out.print("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
			}
			else
			{
				RegisterMemberStub stub = new RegisterMemberStub();
				RegisterMemberStub.Register register = new RegisterMemberStub.Register();
				register.setAccount(text_account);
				register.setPassword(text_password);
				register.setName(text_name);
				register.setEmail(text_email);
				register.setPhone(text_phone);
				register.setAddress(text_address);
				RegisterMemberStub.RegisterResponse resp = stub.register(register);
				boolean result = resp.get_return();
				
				if(result)
				{
					out.print("Regist success !! please wait for 3 seconds ... we will bring you back to login page!");
					out.print("<meta http-equiv=\"refresh\" content=\"3;url=login.jsp\" />");
				}
				else
				{
					out.print("Same account, please change and regist again !! please wait for 3 seconds ... we will bring you back to login page!");
					out.print("<meta http-equiv=\"refresh\" content=\"3;url=javascript:history.go(-1)\" />");
				}
			}
			
		} catch ( Exception e) {
			out.print("Error !!!" + e.toString());
		}
		
		
		
 	%>　		

</body>
</html>