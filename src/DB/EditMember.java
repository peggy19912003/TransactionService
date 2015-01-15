package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditMember {
	private Connection con = null; //Database objects 
	  //連接object
	private ResultSet rs = null; 
	  //結果集 
	private PreparedStatement pst = null;
	  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	  //先利用?來做標示 
	
	public void openDB() {
		try {
		      Class.forName("com.mysql.jdbc.Driver"); 
		      //註冊driver 
		      con = DriverManager.getConnection( 
		      "jdbc:mysql://140.118.109.39/webservice?useUnicode=true&amp;characterEncoding=Big5", 
		      "hsnl","hsnl1130"); 
		    }
		    catch(ClassNotFoundException e) //有可能會產生sqlexception
		    {
		      System.out.println("DriverClassNotFound :"+e.toString()); 
		    }
		    catch(SQLException x) { 
		      System.out.println("Exception :"+x.toString()); 
		}
	}
	
	public boolean isValid(String SessionID)
	{
		openDB();
		
		String sql = "";
		sql = "SELECT * FROM usr_token WHERE usr_token_id = ? ";
		
		try {
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, SessionID);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
								
				return true;

			} else {
				
				System.out.println("無效的token！");
				
				return false;
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("Message Exception :" + e.toString()); 
		}
		finally
		{
			close();
		}
		
		return false;
	}
	
	public boolean isCorrect(String account, String old_password)
	{
		openDB();
		
		String selectSQL = "";
		selectSQL = "SELECT * FROM usr_list "
				+ "WHERE usr_account = ? AND usr_pw = ? ";
		
		try {
			pst = con.prepareStatement(selectSQL); 
			pst.setString(1, account);
			pst.setString(2, old_password);
			rs = pst.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("createNewDeal Exception :" + e.toString()); 
		}
		finally
		{
			close();
		}
		return false;
	}
		
	public String editMemberData(String account, String old_password, String new_password, String name, String email, String phone, String address, String sessionID)
	{
		if(!isValid(sessionID)) return "You have not logged in yet, please login again !";
		if(!isCorrect(account, old_password)) return "Wrong account or password !";

		openDB();
		
		try
		{
			String selectSQL = "";
			selectSQL = "SELECT * FROM usr_list "
					+ "WHERE usr_account = ? AND usr_pw = ? ";
			
			pst = con.prepareStatement(selectSQL); 
			pst.setString(1, account);
			pst.setString(2, old_password);
			rs = pst.executeQuery();
			
			rs.next();
			
			if(new_password == null || new_password == "")
			{
				new_password = rs.getString("usr_pw");
			}
			if(name == null || name == "")
			{
				name = rs.getString("usr_name");
			}
			if(email == null || email == "")
			{
				email = rs.getString("usr_email");
			}
			if(phone == null || phone == "")
			{
				phone = rs.getString("usr_phone");
			}
			if(address == null || address == "")
			{
				address = rs.getString("usr_address");
			}
			
		}
		catch(SQLException ee)
		{
			System.out.println("editMemberData Exception :" + ee.toString()); 
		}

		
		String updateSQL = "";
		updateSQL = "UPDATE usr_list SET usr_name = ?, usr_email = ?, usr_phone = ?, usr_pw = ?, usr_address = ? "
				+ "WHERE usr_account = ? ";
		
		try {
			pst = con.prepareStatement(updateSQL); 
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, phone);
			pst.setString(4, new_password);
			pst.setString(5, address);
			pst.setString(6, account);
			pst.executeUpdate();
			
			return "Edit success !! Now change page to Index page !!";
															
		} catch (SQLException e)
		{
			System.out.println("editMemberData Exception :" + e.toString()); 
		}
		
		return "Edit error ! Some information may not correct !";
	}
		
	private void close()
	{ 
		try 
		{ 
		if(rs!=null) 
		{ 
			rs.close(); 
			rs = null; 
		} 
		if(pst!=null) 
		{ 
			pst.close(); 
			pst = null; 
		} 
		} 
		catch(SQLException e) 
		{ 
		System.out.println("Close Exception :" + e.toString()); 
		} 
	}
	
	public static void main(String[] args)
	{
		EditMember test = new EditMember();
		System.out.println(test.editMemberData("MinChuan", "a88444821", "test", "ken", "a33999512@gmail.com", "0910511396", "台北市大安區基隆路四段43號", "aaa"));
	}
}
