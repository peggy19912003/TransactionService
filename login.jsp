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
<table align="center">
	<tbody>
		<tr>
			<td>
				<form name="form" method="post">
				<table>
					<tr>
						<td colspan="2">
							<H1>Login</H1>
						</td>
					</tr>
					<tr>
					<td>Account：</td>
					<td><input type="text" name="account_login"></td> 
					</tr>
					<tr>
						<td>Password：</td>
						<td><input type="password" name="password_login"></td> 
					</tr>
					<tr>
						<td>
							<input type="submit" name="Login" value="Login">
						</td>
						<td>
							<input type="reset" value="Reset"/>
						</td> 
					</tr>
					<tr>
						<td>
						</td>
						<td align="right">
							<a href="register.jsp"><font size="2">No Account?</font></a>
						</td>
					</tr>
				</table>
				
				<%
				try {
					String text_account = request.getParameter("account_login");
					String text_password = request.getParameter("password_login");
					String sid = session.getId();
								
					/* *** *** ***** ***** *** *** 測試Session以及getID方法
					
					LoginMemberStub stub = new LoginMemberStub();
					
					LoginMemberStub.GetID getid = new LoginMemberStub.GetID();
					getid.setAccount(text_account);
					LoginMemberStub.GetIDResponse resp_getid = stub.getID(getid);
									
					String id = resp_getid.get_return();
					
					String sid = session.getId();
					
					session.setAttribute("usr_account", text_account);
					session.setAttribute("usr_id", id);
					
					out.print(sid + "<br>");
					out.print(session.getAttribute("usr_account") + "<br>");
					out.print(session.getAttribute("usr_id") + "<br>");
					
					***** ***** ***** ***** ***** ***** *** *** *** *** *** */
					if(request.getParameter("Login") != null)
					{			
						if(text_account == "" 
								|| text_password == "" 
								|| text_account == null
								|| text_password == null)
						{
							out.print("<script>alert(\"Login error !\")</script>");
						}
						else
						{
							LoginMemberStub stub = new LoginMemberStub();
							
							//使用Login的方法
							LoginMemberStub.Login login = new LoginMemberStub.Login();
							login.setAccount(text_account);
							login.setPassword(text_password);
							
							LoginMemberStub.LoginResponse resp = stub.login(login);
											
							boolean result = resp.get_return();
							
							if(result)
							{
								//使用GetID的方法
								LoginMemberStub.GetID getid = new LoginMemberStub.GetID();
								getid.setAccount(text_account);
								LoginMemberStub.GetIDResponse resp_getid = stub.getID(getid);
								
								String id = resp_getid.get_return();
								
								//取得SessionID - 測試SessionID是否不會變動
								
								/* *** *** *** Session Attribute對應
								usr_account：使用者帳號
								usr_id：使用者ID
								*** *** *** *** */
								
								session.setAttribute("usr_account", text_account);
								session.setAttribute("usr_id", id);
								
								//監控Session是否正確存入
								out.print(sid + "<br>");
								out.print(session.getAttribute("usr_account") + "<br>");
								out.print(session.getAttribute("usr_id") + "<br>");
								
								out.print("<script>alert(\"Welcome User !!\")</script>");
								
								//進行轉址
								out.print("<meta http-equiv=\"refresh\" content=\"1;url=index.jsp\" />");
							}
							else
							{
								out.print("<script>alert(\"Invailid user, please try again !!\")</script>");
							}
						}
					}
				} catch ( Exception e) {
					
				}
				
		 	%>
			</form>
			</td>
		</tr>
	</tbody>
</table>
	
</body>
</html>