<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Register</title>
<script type="text/javascript" language="JavaScript">
function checkFormat()
{
	var alert_text;
	var isAlert = true;
	var iCount_name = document.form.name_regist.value.length;
	if(iCount_name > 50)
		{
			alert_text = "The name length: " + iCount + ", you shold not using the name > 50 ";
			isAlert = false;
		}
	if(iCount_name == 0)
	{
		alert_text = "Name can not be empty !";
		isAlert = false;
	}
	
	var iCount_account = document.form.account_regist.value.length;
	if(iCount_account > 50)
		{
			alert_text = "The account length: " + iCount_account + ", you shold not using the account > 50 ";
			isAlert = false;
		}
	if(iCount_account == 0)
	{
		alert_text = "Account can not be empty !";
		isAlert = false;
	}
	
	var iCount_email = document.form.email_regist.value.length;
	if(iCount_email > 60)
		{
			alert_text = "The email length: " + iCount_email + ", you shold not using the email > 60 ";
			isAlert = false;
		}
	
	var iCount_phone = document.form.phone_regist.value.length;
	if(iCount_phone > 10)
		{
			alert_text = "The phone length: " + iCount_phone + ", you shold not using the phone > 10 ";
			isAlert = false;
		}
	if(iCount_phone == 0)
	{
		alert_text = "Phone can not be empty !";
		isAlert = false;
	}
	
	var iCount_address = document.form.address_regist.value.length;
	if(iCount_address > 150)
		{
			alert_text = "The address length: " + iCount_address + ", you shold not using the address > 150 ";
			isAlert = false;
		}
	if(iCount_address == 0)
	{
		alert_text = "Address can not be empty !";
		isAlert = false;
	}
	
	var iCount_password = document.form.password_regist.value.length;
	if(iCount_password > 30)
		{
			alert_text = "The password length: " + iCount_password + ", you shold not using the password > 30 ";
			isAlert = false;
		}
	if(iCount_password == 0)
	{
		alert_text = "Password can not be empty !";
		isAlert = false;
	}
	
	var text_email = document.form.email_regist.value;
	if (text_email.search(/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/)!=-1)
	{
	 
	} else
	{
		if(text_email.length == 0)
			{
				alert_text = "Email can not be empty !";
				isAlert = false;
			}
		else
			{
				alert_text = "Email format error ! Please try again !\nEX: M10309106@mail.ntust.edu.tw ";
				isAlert = false;
			}
	}
	if(!isAlert)
		{
			alert(alert_text);
			return false;
		}
	else
		{
			return true;
		}
}
</script>

</head>
<body>
	<table align="center">
		<tbody>
			<tr>
				<td>
					<form name="form" action="regist-result.jsp" method="POST" onsubmit="return checkFormat();">
						<table>
							<tr>
								<td colspan="2">
									<H1>Register</H1>
								</td>
							</tr>
							<tr>
							<tr>
								<td>Account：</td>
								<td>
									<input type="text" id="account_regist" name="account_regist">
								</td>
							</tr>
							<tr>
								<td>Password：</td>
								<td>
									<input type="password" name="password_regist">
								</td> 
							</tr>
							<tr>
								<td>Name：</td>
								<td>
									<input type="text" id="name_regist" name="name_regist">
								</td> 
							</tr>
							<tr>
								<td>Email：</td>
								<td>
									<input type="text" id="email_regist" name="email_regist">
								</td> 
							</tr>
							<tr>
								<td>Phone：</td>
								<td>
									<input type="text" id="phone_regist" name="phone_regist">
								</td> 
							</tr>
							<tr>
								<td>Address：</td>
								<td>
									<input type="text" id="address_regist" name="address_regist">
								</td> 
							</tr>
							</table>
							
							<input type="submit" name="Regist" value="Regist">
							<input type="reset" value="Reset"/>
							<br/>
							
							</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	
</body>
</html>