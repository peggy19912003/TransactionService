<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service_DB.GoodsRecordsStub" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail Content</title>
</head>
<body>
	<%
		String confirm = request.getParameter("Confirm");
		String back = request.getParameter("GoBack");
		String sid = session.getId();
		
		//session.setAttribute("LessorID", "9");
		//session.setAttribute("GoodsID", "1");
		//session.setAttribute("usr_id", "8");
		
		String goodsID = (String) session.getAttribute("GoodsID");
		String explorerID = (String) session.getAttribute("usr_id");
		String lessorID = (String) session.getAttribute("LessorID");
		
		GoodsRecordsStub stub = new GoodsRecordsStub();
		
		GoodsRecordsStub.IsValid isValid = new GoodsRecordsStub.IsValid();
		isValid.setSessionID(sid);
		GoodsRecordsStub.IsValidResponse resp_isValid = stub.isValid(isValid);
				
		if(!resp_isValid.get_return())
		{
			out.print("<script>alert(\"Invalid Login, please login again !!\")</script>");
			out.print("<meta http-equiv=\"refresh\" content=\"3;url=login.jsp\" />");
		}
		else
		{
			if(back == null)
			{
				//由此判斷是否已按下確認
				//如果沒有按下確認
				if(confirm == null)
				{
					GoodsRecordsStub.IsOwner isOwner = new GoodsRecordsStub.IsOwner();
					isOwner.setGoodsID(goodsID);
					isOwner.setId(explorerID);
					isOwner.setSessionID(sid);
					GoodsRecordsStub.IsOwnerResponse resp = stub.isOwner(isOwner);
					boolean result = resp.get_return();
					
					//是擁有者
					if(result)
					{
						GoodsRecordsStub.GetLessorRecord getLessorRecord = new GoodsRecordsStub.GetLessorRecord();
						getLessorRecord.setLessorID(lessorID);
						getLessorRecord.setSessionID(sid);
						GoodsRecordsStub.GetLessorRecordResponse resp_get = stub.getLessorRecord(getLessorRecord);
						String str = resp_get.get_return();
						
						String[] array = str.split(",");
						
						out.println("<table style=\"border: 5px double rgb(109, 2, 107); height: 100px; background-color: rgb(255, 255, 255); width: 300px;\" align=\"left\" cellpadding=\"5\" cellspacing=\"5\" frame=\"border\" rules=\"all\">");
						out.println("\t\t<tbody>");
						out.println("\t\t\t<tr>");
						out.println("\t\t\t\t<td align=\"center\">賣家資料</td>");
						out.println("\t\t\t</tr>");
						
						for(String element : array)
						{
							out.println("\t\t\t<tr>");
							out.println("\t\t\t\t<td align=\"center\">" + element + "</td>");
							out.println("\t\t\t</tr>");
						}
						out.println("\t\t</tbody>");
						out.println("\t</table>");
					}
					
					//是買家
					else
					{
						GoodsRecordsStub.GetLessorRecord getLessorRecord = new GoodsRecordsStub.GetLessorRecord();
						getLessorRecord.setLessorID(lessorID);
						getLessorRecord.setSessionID(sid);
						GoodsRecordsStub.GetLessorRecordResponse resp_getLessor = stub.getLessorRecord(getLessorRecord);
						String str = resp_getLessor.get_return();
						
						GoodsRecordsStub.GetTenantRecord getTenantRecord = new GoodsRecordsStub.GetTenantRecord();
						getTenantRecord.setTenantID(explorerID);
						getTenantRecord.setSessionID(sid);
						GoodsRecordsStub.GetTenantRecordResponse resp_getTenant = stub.getTenantRecord(getTenantRecord);
						String str2 = resp_getTenant.get_return();
						
						String[] array = {"Name", "Phone", "Address", "E-Mail"};
						String[] array1 = str.split(",");
						String[] array2 = str2.split(",");
						
						
						out.println("<table style=\"border: 5px double rgb(109, 2, 107); height: 100px; background-color: rgb(255, 255, 255); width: 80%;\" align=\"center\" cellpadding=\"5\" cellspacing=\"5\" frame=\"border\" rules=\"all\">");
						out.println("\t\t<tbody>");
						out.println("\t\t\t<tr>");
						out.println("\t\t\t\t<td></td>");
						out.println("\t\t\t\t<td align=\"center\">賣家資料</td>");
						out.println("\t\t\t\t<td align=\"center\">買家資料</td>");
						out.println("\t\t\t</tr>");
						
						for(int i = 0; i < array.length; i++)
						{
							out.println("\t\t\t<tr>");
							out.println("\t\t\t\t<td align=\"center\">" + array[i] + "</td>");
							out.println("\t\t\t\t<td align=\"center\">" + array1[i] + "</td>");
							out.println("\t\t\t\t<td align=\"center\">" + array2[i] + "</td>");
							out.println("\t\t\t</tr>");
						}
						
						out.println("\t\t</tbody>");
						out.println("\t</table>");
					}
					
					GoodsRecordsStub.GetStateCode getStateCode = new GoodsRecordsStub.GetStateCode();
					getStateCode.setGoodsID(goodsID);
					getStateCode.setSessionID(sid);
					GoodsRecordsStub.GetStateCodeResponse resp_getStateCode = stub.getStateCode(getStateCode);
					String str_result = resp_getStateCode.get_return();
					
					out.println("<form name=\"form\" method=\"post\">");
					out.println("\t<table style=\"width: 80%;\" align = \"center\">");
					out.println("\t\t<tbody>");
					out.println("\t\t\t<tr>");
					out.println("\t\t\t\t<td></td>");
					out.println("\t\t\t\t<td></td>");
									
					if(str_result.equals("0") 
							|| str_result.equals("1") 
							|| str_result.equals("2") 
							|| str_result.equals("3") ) 
					{
						out.println("\t\t\t\t<td align=\"right\"><input type=\"submit\" name=\"GoBack\" value=\"back\"></td>");
					} 
					else
					{
						out.print("\t\t\t\t<td align=\"right\"><input type=\"submit\" name=\"Confirm\" value=\"confirm\">"
								+ "<input type=\"submit\" name=\"GoBack\" value=\"back\"></td>");
					}
					
					out.println("\t\t\t</tr>");
					out.println("\t\t</tbody>");
					out.println("\t</table>");
					out.println("</form>");
				}
				
				//按下確認交易後，開始交易並導入
				else
				{
					GoodsRecordsStub.CreateNewDeal createNewDeal = new GoodsRecordsStub.CreateNewDeal();
					createNewDeal.setGoodsID(goodsID);
					createNewDeal.setLessorID(lessorID);
					createNewDeal.setTenantID(explorerID);
					createNewDeal.setSessionID(sid);
					GoodsRecordsStub.CreateNewDealResponse resp_create = stub.createNewDeal(createNewDeal);
					resp_create.get_return();
					out.print("<script>alert(\"Success !! Now you can go Member Center check the Transaction !!\")</script>");
					out.print("<meta http-equiv=\"refresh\" content=\"3;url=index.html\" />");
				}
			}
			else
			{
				out.print("<meta http-equiv=\"refresh\" content=\"1;url=javascript:history.go(-1)\" />");
			}
		}		
	%>
</body>
</html>