<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service_DB.TransactProcessStub" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transact-Process</title>
</head>
<body>
	<br>
	<table border="3">
		<tbody>
			<tr>
				<td>
					請進行交易處理
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<form name="form" method="post">
		<input type="submit" id="submit_1" name="submit1" value="賣方物品確認已寄送" disabled="disabled"><br>
		<input type="submit" id="submit_2" name="submit2" value="買方確認已收到" disabled="disabled"><br>
		<input type="submit" id="submit_3" name="submit3" value="買方確認已歸還" disabled="disabled"><br>
		<input type="submit" id="submit_4" name="submit4" value="賣方物品確認已收到" disabled="disabled"><br>
	</form>
	<%
		//session.setAttribute("LessorID", "9");
		//session.setAttribute("GoodsID", "2");
		//session.setAttribute("usr_id", "4");
		
		String goodsID = (String) session.getAttribute("GoodsID");

		String explorerID = (String) session.getAttribute("usr_id");
		String lessorID = (String) session.getAttribute("LessorID");
		
		TransactProcessStub stub = new TransactProcessStub();
		
		String sid = session.getId();

		TransactProcessStub.IsValid isValid = new TransactProcessStub.IsValid();
		isValid.setSessionID(sid);
		TransactProcessStub.IsValidResponse resp_isValid = stub.isValid(isValid);
						
		if(!resp_isValid.get_return())
		{
			out.print("<script>alert(\"Invalid Login, please login again !!\")</script>");
			out.print("<meta http-equiv=\"refresh\" content=\"1;url=login.jsp\" />");
		}
		else
		{
			String submit1 = request.getParameter("submit1");
			String submit2 = request.getParameter("submit2");
			String submit3 = request.getParameter("submit3");
			String submit4 = request.getParameter("submit4");
			
			TransactProcessStub.UpdateRecords updateRecords = new TransactProcessStub.UpdateRecords();
			
			if(submit1 != null)
			{
				updateRecords.setGoodsID(goodsID);
				updateRecords.setState("1");
				updateRecords.setSessionID(sid);
				TransactProcessStub.UpdateRecordsResponse resp_updateRecords = stub.updateRecords(updateRecords);
				resp_updateRecords.get_return();
			}
			if(submit2 != null)
			{
				updateRecords.setGoodsID(goodsID);
				updateRecords.setState("2");
				updateRecords.setSessionID(sid);
				TransactProcessStub.UpdateRecordsResponse resp_updateRecords = stub.updateRecords(updateRecords);
				resp_updateRecords.get_return();
			}
			if(submit3 != null)
			{
				updateRecords.setGoodsID(goodsID);
				updateRecords.setState("3");
				updateRecords.setSessionID(sid);
				TransactProcessStub.UpdateRecordsResponse resp_updateRecords = stub.updateRecords(updateRecords);
				resp_updateRecords.get_return();
				out.print("<script>alert(\"Thank you for using the system, you will arrive the appraisal page in 3 seconds !!\"</script>");
				out.print("<meta http-equiv=\"refresh\" content=\"1;url=Appraisal.jsp\" />");
				session.setAttribute("appraisal", "1");
			}
			if(submit4 != null)
			{
				updateRecords.setGoodsID(goodsID);
				updateRecords.setState("4");
				updateRecords.setSessionID(sid);
				TransactProcessStub.UpdateRecordsResponse resp_updateRecords = stub.updateRecords(updateRecords);
				resp_updateRecords.get_return();
				out.print("<script>alert(\"Thank you for using the system, you will arrive the appraisal page in 3 seconds !!\"</script>");
				out.print("<meta http-equiv=\"refresh\" content=\"1;url=Appraisal.jsp\" />");
				session.setAttribute("appraisal", "0");
			}
			
			try
			{
				
				TransactProcessStub.GetStateCode getStateCode = new TransactProcessStub.GetStateCode();
				getStateCode.setGoodsID(goodsID);
				getStateCode.setSessionID(sid);
				TransactProcessStub.GetStateCodeResponse resp_getStateCode = stub.getStateCode(getStateCode);
				
				String str = "";
				str = resp_getStateCode.get_return();
				
				String[] result = str.split(",");
				
				TransactProcessStub.IsDued isDued = new TransactProcessStub.IsDued();
				isDued.setGoodsID(goodsID);
				isDued.setSessionID(sid);
				TransactProcessStub.IsDuedResponse resp_isDued = stub.isDued(isDued);
				
				boolean isDued_result = !resp_isDued.get_return();
				//如果相同，代表瀏覽者為賣方
				if(result[1].equals(explorerID))
				{
					//狀態：已按下確認交易－顯示按鈕：賣家已將物品寄出
					if(result[0].equals("0"))
					{
						out.print("<script>document.getElementById(\"submit_1\").disabled = false;</script>");
						//out.print("<script>document.getElementById(\"submit_4\").disabled=\"disabled\";</script>");
					}
					//狀態：賣家已將物品寄出－顯示按鈕：買家確認收到物品
					else if(result[0].equals("1"))
					{
						//out.print("<script>document.getElementById(\"submit_1\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_4\").disabled=\"disabled\";</script>");
					}
					//狀態：買家確認收到物品－等到DuedDate到期－顯示按鈕：全部無法使用
					else if(result[0].equals("2") && !isDued_result)
					{
						//out.print("<script>document.getElementById(\"submit_1\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_4\").disabled=\"disabled\";</script>");
					}
					//狀態：買家確認收到物品－顯示按鈕：買家已歸還物品
					else if(result[0].equals("2") && isDued_result)
					{
						//out.print("<script>document.getElementById(\"submit_1\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_4\").disabled=\"disabled\";</script>");
					}
					//狀態：買家已歸還物品－顯示按鈕：賣家確認收到物品
					else if(result[0].equals("3") && isDued_result)
					{
						//out.print("<script>document.getElementById(\"submit_1\").disabled=\"disabled\";</script>");
						out.print("<script>document.getElementById(\"submit_4\").disabled = false;</script>");
					}
					//狀態：賣家確認收到物品－顯示按鈕：全部無法使用
					else if(result[0].equals("4"))
					{
						//out.print("<script>document.getElementById(\"submit_1\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_4\").disabled=\"disabled\";</script>");
					}
					//out.print("<script>document.getElementById(\"submit_2\").disabled=\"disabled\";</script>");
					//out.print("<script>document.getElementById(\"submit_3\").disabled=\"disabled\";</script>");
				}
				else if(result[1].equals("0"))
				{
					out.print("<meta http-equiv=\"refresh\" content=\"1;url=index.html\" />");
				}
				//如果不同，代表瀏覽者為買方
				else
				{
					//狀態：已按下確認交易－顯示按鈕：賣家已將物品寄出
					if(result[0].equals("0"))
					{
						//out.print("<script>document.getElementById(\"submit_2\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_3\").disabled=\"disabled\";</script>");
					}
					//狀態：賣家已將物品寄出－顯示按鈕：買家確認收到物品
					else if(result[0].equals("1"))
					{
						out.print("<script>document.getElementById(\"submit_2\").disabled = false;</script>");
						//out.print("<script>document.getElementById(\"submit_3\").disabled=\"disabled\";</script>");
					}
					//狀態：買家確認收到物品－等到DuedDate到期－顯示按鈕：全部無法使用
					else if(result[0].equals("2") && !isDued_result)
					{
						//out.print("<script>document.getElementById(\"submit_2\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_3\").disabled=\"disabled\";</script>");
					}
					//狀態：買家確認收到物品－顯示按鈕：買家已歸還物品
					else if(result[0].equals("2") && isDued_result)
					{
						//out.print("<script>document.getElementById(\"submit_2\").disabled=\"disabled\";</script>");
						out.print("<script>document.getElementById(\"submit_3\").disabled = false;</script>");
					}
					//狀態：買家已歸還物品－顯示按鈕：全部無法使用
					else if(result[0].equals("3") && isDued_result)
					{
						//out.print("<script>document.getElementById(\"submit_2\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_3\").disabled=\"disabled\";</script>");
					}
					//狀態：賣家確認收到物品－顯示按鈕：全部無法使用
					else if(result[0].equals("4"))
					{
						//out.print("<script>document.getElementById(\"submit_2\").disabled=\"disabled\";</script>");
						//out.print("<script>document.getElementById(\"submit_3\").disabled=\"disabled\";</script>");
					}
					//out.print("<script>document.getElementById(\"submit_1\").disabled=\"disabled\";</script>");
					//out.print("<script>document.getElementById(\"submit_4\").disabled=\"disabled\";</script>");
				}
			}
			catch(Exception e)
			{
				out.print(e.toString());
			}
		}
	%>
</body>
</html>