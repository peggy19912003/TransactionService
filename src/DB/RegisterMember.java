package DB;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RegisterMember {
	
	private Connection con = null; //Database objects 
	  //連接object 
	private Statement stat = null; 
	  //執行,傳入之sql為完整字串 
	private ResultSet rs = null; 
	  //結果集 
	private PreparedStatement pst = null; 
	  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	  //先利用?來做標示 
	
	private String dropdbSQL = "DROP TABLE usr_list "; 
	
	private String truncateSQL = "TRUNCATE TABLE usr_list";
	
	public RegisterMember()
	{
		openDB();
	}
	
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
	
	public boolean register(String account, String name, String email, String phone, String password, String address)
	{
		openDB();
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
		String today = df.format(new java.util.Date());
		
		String sql = "";
		sql = "SELECT * FROM usr_list WHERE usr_account = ? ";
		
		try {
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, account);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("註冊失敗，已有重複帳號名稱！");
				
				return false;
			} else {
				sql = "INSERT INTO usr_list(usr_account, usr_name, usr_email, usr_phone, usr_pw, usr_address, creat_date, memo) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
				pst = con.prepareStatement(sql);
				
				pst.setString(1, account);
				pst.setString(2, name);
				pst.setString(3, email);
				pst.setString(4, phone);
				pst.setString(5, password);
				pst.setString(6, address);
				pst.setString(7, today);
				pst.setString(8, "");
				
				pst.executeUpdate();
				
				System.out.println("註冊成功");
				
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Register Exception :" + e.toString()); 
		}
		
		return false;
	}
	
	//刪除Table, 
	//跟建立table很像 
	public void dropTable() 
	{ 
		openDB();
		try 
		{ 
			stat = con.createStatement(); 
			stat.executeUpdate(dropdbSQL); 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("DropDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			close(); 
		} 
	}
	
	public void truncateTable() 
	{ 
		openDB();
		try 
		{ 
			stat = con.createStatement(); 
			stat.executeUpdate(truncateSQL); 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("truncate Exception :" + e.toString()); 
		} 
		finally 
		{ 
			close(); 
		} 
	}
	
	//完整使用完資料庫後,記得要關閉所有Object 
	//否則在等待Timeout時,可能會有Connection poor的狀況 
	private void close() 
	{ 
		try 
		{ 
		if(rs!=null) 
		{ 
			rs.close(); 
			rs = null; 
		} 
		if(stat!=null) 
		{ 
			stat.close(); 
			stat = null; 
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
		//測看看是否正常 
		RegisterMember test = new RegisterMember(); 
		test.register("a33999512", "kenchang", "a33999512@gmail.com", "0910511396", "a123456", "台北");
	
	} 
	
}
