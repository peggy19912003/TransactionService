package DB;

import java.sql.SQLException;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Message {
	
	private Connection con = null; //Database objects 
	  //連接object 
	private ResultSet rs = null; 
	  //結果集 
	private ResultSet rs2 = null; 
	  //結果集 2
	private PreparedStatement pst = null; 
	  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	  //先利用?來做標示 
	
	public void openDB() {
		try { 
		      Class.forName("com.mysql.jdbc.Driver"); 
		      //註冊driver 
		      con = DriverManager.getConnection( 
		      "jdbc:mysql://140.118.109.39/webservice?useUnicode=true&amp;characterEncoding=UTF-8", 
		      "hsnl", "hsnl1130"); 
		      
		    } 
		    catch(ClassNotFoundException e) //有可能會產生sqlexception
		    { 
		      System.out.println("DriverClassNotFound :"+e.toString()); 
		    } 
		    catch(SQLException x) { 
		      System.out.println("Exception :" + x.toString()); 
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
	
	public String getAccount(String id, String sessionID)
	{
		if(!isValid(sessionID)) return "";

		openDB();
		
		String sql = "";
		sql = "SELECT * FROM usr_list WHERE usr_id = ? ";
		
		try {
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, id);
			
			rs2 = pst.executeQuery();
			
			if(rs2.next()) {
				
				System.out.println("Account = " + rs2.getString("usr_account"));
				
				return rs2.getString("usr_account");

			} else {
				
				System.out.println("無此ID！");
				
				return "";
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("GetAccount Exception :" + e.toString()); 
		}

		return "";
	}
	
	public String getItemownerID(String goodsID, String sessionID)
	{
		if(!isValid(sessionID)) return "";
		
		openDB();
		
		String str = "";
		
		String sql = "";
		sql = "SELECT account FROM goods WHERE id = ? ";
		
		try {
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, goodsID);
			
			rs = pst.executeQuery();
			
			if(rs.next())
			{
				str = rs.getString("account");
			}
			else
			{
				System.out.println("無此物品ID");
			}
			
			return str;
			
		}
		catch (SQLException e)
		{
			System.out.println("getItemowner Exception :" + e.toString()); 
		}
		finally
		{
			close();
		}
		return str;
	}
	
	public boolean inputMessage(String senderID, String transactID, String message, String sessionID)
	{
		if(!isValid(sessionID)) return false;

		openDB();
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
		String today = df.format(new java.util.Date());
		
		String insertMessageSQL = "INSERT INTO transact_message"
				+ "( transact_id"
				+ ", send_date"
				+ ", message_record"
				+ ", sender_id) "
				+ "VALUES(?, ?, ?, ?)";
		
		try 
		{ 
			pst = con.prepareStatement(insertMessageSQL); 
			
			pst.setString(1, transactID);
			pst.setString(2, today); 
			pst.setString(3, message);
			pst.setString(4, senderID);
			
			pst.executeUpdate(); 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("InsertMessage Exception :" + e.toString()); 
		} 
		finally 
		{ 
			close(); 
		} 
		return false;
	}
	
	public String getMessage(String transactID, String sessionID)
	{
		if(!isValid(sessionID)) return "";

		openDB();
		
		String str = "";
				
		String sql = "";
		sql = "SELECT * FROM "
				+ "transact_message INNER JOIN usr_list "
				+ "ON transact_message.sender_id = usr_list.usr_id "
				+ "WHERE transact_id = ? ";
		
		try {
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, transactID);
			
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				str = str + rs.getString("send_date")
						+ "\t\t" + rs.getString("usr_account")
						+ "：" + rs.getString("message_record")
						+ "\n";
				}
			
			return str;
			
		}
		catch (SQLException e)
		{
			System.out.println("getMessage Exception :" + e.toString()); 
		}
		finally
		{
			close();
		}
		
		return "";
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
}
