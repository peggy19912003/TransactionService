package DB;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GoodsRecords {
	
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
		
	public String getStateCode(String goodsID, String sessionID)
	{
		if(!isValid(sessionID)) return "";
		
		openDB();
		
		String selectSQL = "";
		selectSQL = "SELECT * FROM records WHERE Deal_GoodsID = ? ";
		
		String str = "";
		
		try {
			pst = con.prepareStatement(selectSQL); 
			
			pst.setString(1, goodsID);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				str = rs.getString("Deal_State");
				
				System.out.println("Deal_State : " + str); 
				
				return str;
				
			} else {
								
				return "5";
			}
			
		} catch (SQLException e) {
			System.out.println("getLessor Exception :" + e.toString()); 
		}
		return "";
	}
	
	public String getLessorRecord(String LessorID, String sessionID)
	{
		if(!isValid(sessionID)) return "";
		
		openDB();
		
		String selectSQL = "";
		selectSQL = "SELECT * FROM usr_list WHERE usr_id = ? ";
		
		String str = "";
		
		try {
			pst = con.prepareStatement(selectSQL); 
			
			pst.setString(1, LessorID);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("ID = " + rs.getString("usr_id"));
				
				str = rs.getString("usr_name") + "," 
				+ rs.getString("usr_phone") + ","
				+ rs.getString("usr_address") + ","
				+ rs.getString("usr_email");
				
				return str;

			} else {
				
				System.out.println("無此使用者！");
				
				return "";
			}
			
		} catch (SQLException e) {
			System.out.println("getLessor Exception :" + e.toString()); 
		}
		
		return str;
	}
	
	public String getTenantRecord(String TenantID, String sessionID)
	{
		if(!isValid(sessionID)) return "";

		openDB();
		
		String selectSQL = "";
		selectSQL = "SELECT * FROM usr_list WHERE usr_id = ? ";
		
		String str = "";
		
		try {
			pst = con.prepareStatement(selectSQL); 
			
			pst.setString(1, TenantID);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("ID = " + rs.getString("usr_id"));
				
				str = rs.getString("usr_name") + "," 
				+ rs.getString("usr_phone") + ","
				+ rs.getString("usr_address") + ","
				+ rs.getString("usr_email");
				
				return str;

			} else {
				
				System.out.println("無此使用者！");
				
				return "";
			}
			
		} catch (SQLException e) {
			System.out.println("getTenant Exception :" + e.toString()); 
		}
		
		return str;
	}
	
	public boolean createNewDeal(String goodsID, String lessorID, String tenantID, String sessionID)
	{
		if(!isValid(sessionID)) return false;

		openDB();
		
		String insertSQL = "";
		insertSQL = "INSERT INTO records(Deal_DateTime, Deal_GoodsID, Deal_Lessor, Deal_Tenant, Deal_State) "
				+ "VALUES(?, ?, ?, ?, ?) ";
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
		String today = df.format(new java.util.Date());
		
		try {
			pst = con.prepareStatement(insertSQL); 
			pst.setString(1, today);
			pst.setString(2, goodsID);
			pst.setString(3, lessorID);
			pst.setString(4, tenantID);
			pst.setString(5, "0");
			pst.executeUpdate();
															
		} catch (SQLException e)
		{
			System.out.println("createNewDeal Exception :" + e.toString()); 
		}
		
		return true;
	}
	
	public boolean isOwner(String goodsID, String id, String sessionID)
	{
		if(!isValid(sessionID)) return false;

		openDB();
		
		String selectSQL = "";
		
		selectSQL = "SELECT goods.id, usr_list.usr_id "
				+ "FROM goods INNER JOIN usr_list "
				+ "ON goods.account = usr_list.usr_account "
				+ "WHERE goods.id = ? AND usr_list.usr_id = ? ";
		
		try {
			
			pst = con.prepareStatement(selectSQL);
			
			pst.setString(1, goodsID);
			pst.setString(2, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) return true;
			else return false;
			
		} catch (SQLException e) {
			System.out.println("RecordsOwner Exception :" + e.toString()); 
		}
		
		return false;
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
