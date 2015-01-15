package DB;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

public class LoginMember {
	
	private Connection con = null; //Database objects 
	  //�s��object 
	private Statement stat = null; 
	  //����,�ǤJ��sql������r�� 
	private ResultSet rs = null; 
	  //���G�� 
	private PreparedStatement pst = null; 
	  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
	  //���Q��?�Ӱ��Х� 
	
	public LoginMember()
	{
		openDB();
	}
	
	public void openDB() {
		try { 
		      Class.forName("com.mysql.jdbc.Driver"); 
		      //���Udriver 
		      con = DriverManager.getConnection( 
		      "jdbc:mysql://140.118.109.39/webservice?useUnicode=true&amp;characterEncoding=UTF-8", 
		      "hsnl","hsnl1130"); 
		      
		    } 
		    catch(ClassNotFoundException e) //���i��|����sqlexception
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
				
				System.out.println("�L�Ī�token�I");
				
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
	
	public String getID(String account)
	{
		openDB();
		
		String sql = "";
		sql = "SELECT * FROM usr_list WHERE usr_account = ? ";
		
		try {
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, account);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("ID = " + rs.getString("usr_id"));
				
				return rs.getString("usr_id");

			} else {
				
				System.out.println("�L���ϥΪ̡I");
				
				return "";
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
		
		return "";
	}
	
	public boolean login(String account, String password, String SessionID)
	{
		openDB();
		
		String sql = "";
		sql = "SELECT * FROM usr_list WHERE usr_account = ? AND usr_pw = ? ";
		
		try {
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, account);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("�n�J���\�I");
				
				sql = "DELETE  FROM usr_token WHERE usr_account = ?; ";
				
				pst = con.prepareStatement(sql); 
				
				pst.setString(1, account);
				
				pst.executeUpdate();
				
				sql = "INSERT INTO usr_token(usr_account, usr_token_id) VALUES(?, ?); ";
				
				pst = con.prepareStatement(sql); 
				
				pst.setString(1, account);
				pst.setString(2, SessionID);
				
				pst.executeUpdate();
				
				return true;
			} else {
				
				System.out.println("�L���ϥΪ̡I");
				
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("LoginMember Exception :" + e.toString()); 
		}
		
		return false;
	}
	public boolean logout(String SessionID)
	{
		openDB();
		
		String sql = "";
		sql = "DELETE FROM usr_token WHERE usr_token_id = ? ";
		
		try
		{
			pst = con.prepareStatement(sql); 
			
			pst.setString(1, SessionID);
			
			pst.executeUpdate();
			
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("logout exception : " + e.toString());
		}
		finally
		{
			close();
		}
		
		return false;
	}
	//����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
	//�_�h�b����Timeout��,�i��|��Connection poor�����p 
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
		System.out.println("close Exception :" + e.toString()); 
		} 
	} 	
}

