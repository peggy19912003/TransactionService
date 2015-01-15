<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service_DB.MessageStub"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Secret-Message</title>
</head>
<body>
	<form name="form" method="post">
	<%
		String sid = session.getId();
		
		MessageStub stub = new MessageStub();
		
		MessageStub.IsValid isValid = new MessageStub.IsValid();
		isValid.setSessionID(sid);
		MessageStub.IsValidResponse resp_isValid = stub.isValid(isValid);
				
		if(!resp_isValid.get_return())
		{
			out.print("<script>alert(\"Invalid Login, please login again !!\")</script>");
			out.print("<meta http-equiv=\"refresh\" content=\"3;url=login.jsp\" />");
		}
		
		else
		{
			if(request.getParameter("goback") != null)
			{
				//此部分需要修改網址
				out.println("<meta http-equiv=\"refresh\" content=\"0;url=javascript:history.go(-1)\" />");
			}
		}		
	%>
	<textarea cols="85" rows="25" readonly>
		<%
			request.setCharacterEncoding("UTF-8");
			String goback = request.getParameter("goback");

			if(goback != null)
			{
				
			}
			else
			{
				session.setAttribute("LessorID", "9");
				session.setAttribute("TenantID", "9");
				session.setAttribute("GoodsID", "1");
				session.setAttribute("usr_id", "9");
				session.setAttribute("transact_id", "1");

				String goodsID = (String) session.getAttribute("GoodsID");
				String explorerID = (String) session.getAttribute("usr_id");
				String lessorID = (String) session.getAttribute("LessorID");
				String tenantID = (String) session.getAttribute("TenantID");
				String transactID = (String) session.getAttribute("transact_id");

				String submit = request.getParameter("submit");
				String text = request.getParameter("text");

				if(submit != null
						&& submit != "")
				{
					MessageStub.InputMessage inputMessage = new MessageStub.InputMessage();

					String text_modify = new String(text.getBytes("ISO-8859-1"), "UTF-8"); 

					inputMessage.setMessage(text_modify);
					inputMessage.setSenderID(explorerID);
					inputMessage.setTransactID(transactID);
					MessageStub.InputMessageResponse resp_inputMessage = stub.inputMessage(inputMessage);
					resp_inputMessage.get_return();
				}

				out.println("\tMessage Records");
				MessageStub.GetMessage getMessage = new MessageStub.GetMessage();
				getMessage.setTransactID(transactID);
				MessageStub.GetMessageResponse resp_getMessage = stub.getMessage(getMessage);
				out.println("\n" + resp_getMessage.get_return());
			}
		%>
	</textarea>
	<br>
		<input type="text" name="text" size="70">
		<input type="submit" id="submit_message" name="submit" value="Send">
		<input type="submit" id="goback" name="goback" value="Back">
	</form>
</body>
</html>