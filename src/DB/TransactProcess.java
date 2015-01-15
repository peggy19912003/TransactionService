package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactProcess {
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
	
	public boolean isDued(String goodsID, String sessionID)
	{
		if(!isValid(sessionID)) return false;

		openDB();
		
		//判斷是否已到期的SQL
		String selectSQL = "";
		selectSQL = "SELECT * FROM "
				+ "records INNER JOIN goods "
				+ "ON records.Deal_GoodsID = goods.id "
				+ "WHERE records.Deal_GoodsID = ? "
				+ "AND DATEDIFF(CURDATE(), DATE_ADD(records.Deal_DateTime, INTERVAL goods.days DAY)) >= 0;";
		
		try {
			pst = con.prepareStatement(selectSQL); 
			
			pst.setString(1, goodsID);
			
			rs = pst.executeQuery();
			
			//若是找到筆數代表有到期的資料，回傳TRUE
			if(rs.next()) {
								
				System.out.println("isDued !!"); 
				
				return true;
			
			}
			else
			{
				System.out.println("isn't dued !!"); 
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("isDued Exception :" + e.toString()); 
		}
		
		return false;
	}

	
	//return state, lessorID
	public String getStateCode(String goodsID, String sessionID)
	{
		if(!isValid(sessionID)) return ",";

		openDB();
		
		String selectSQL = "";
		selectSQL = "SELECT * FROM records WHERE Deal_GoodsID = ? ";
		
		String str = "";
		
		try {
			pst = con.prepareStatement(selectSQL); 
			
			pst.setString(1, goodsID);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				str = rs.getString("Deal_State")
						+ "," + rs.getString("Deal_Lessor");
				
				System.out.println("Deal_State : " + str);
				
				return str;
				
			} else {
								
				return "5,0";
			}
			
		} catch (SQLException e) {
			System.out.println("getLessor Exception :" + e.toString()); 
		}
		return "5,0";
	}
	
	public boolean updateRecords(String goodsID, String state, String sessionID)
	{
		if(!isValid(sessionID)) return false;

		openDB();
		
		String updateSQL = "";
		updateSQL = "UPDATE records SET Deal_State = ? WHERE Deal_GoodsID = ? ";
				
		try {
			pst = con.prepareStatement(updateSQL); 
			
			pst.setString(1, state);
			pst.setString(2, goodsID);

			pst.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("updateRecords Exception :" + e.toString()); 
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
