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
					<td>Account�G</td>
					<td><input type="text" name="account_login"></td> 
					</tr>
					<tr>
						<td>Password�G</td>
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
								
					/* *** *** ***** ***** *** *** ����Session�H��getID��k
					
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
							
							//�ϥ�Login����k
							LoginMemberStub.Login login = new LoginMemberStub.Login();
							login.setAccount(text_account);
							login.setPassword(text_password);
							
							LoginMemberStub.LoginResponse resp = stub.login(login);
											
							boolean result = resp.get_return();
							
							if(result)
							{
								//�ϥ�GetID����k
								LoginMemberStub.GetID getid = new LoginMemberStub.GetID();
								getid.setAccount(text_account);
								LoginMemberStub.GetIDResponse resp_getid = stub.getID(getid);
								
								String id = resp_getid.get_return();
								
								//���oSessionID - ����SessionID�O�_���|�ܰ�
								
								/* *** *** *** Session Attribute����
								usr_account�G�ϥΪ̱b��
								usr_id�G�ϥΪ�ID
								*** *** *** *** */
								
								session.setAttribute("usr_account", text_account);
								session.setAttribute("usr_id", id);
								
								//�ʱ�Session�O�_���T�s�J
								out.print(sid + "<br>");
								out.print(session.getAttribute("usr_account") + "<br>");
								out.print(session.getAttribute("usr_id") + "<br>");
								
								out.print("<script>alert(\"Welcome User !!\")</script>");
								
								//�i����}
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