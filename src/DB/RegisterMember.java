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
	  //�s��object 
	private Statement stat = null; 
	  //����,�ǤJ��sql������r�� 
	private ResultSet rs = null; 
	  //���G�� 
	private PreparedStatement pst = null; 
	  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
	  //���Q��?�Ӱ��Х� 
	
	private String dropdbSQL = "DROP TABLE usr_list "; 
	
	private String truncateSQL = "TRUNCATE TABLE usr_list";
	
	public RegisterMember()
	{
		openDB();
	}
	
	public void openDB() {
		try {
		      Class.forName("com.mysql.jdbc.Driver"); 
		      //���Udriver 
		      con = DriverManager.getConnection( 
		      "jdbc:mysql://140.118.109.39/webservice?useUnicode=true&amp;characterEncoding=Big5", 
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
				
				System.out.println("���U���ѡA�w�����Ʊb���W�١I");
				
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
				
				System.out.println("���U���\");
				
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Register Exception :" + e.toString()); 
		}
		
		return false;
	}
	
	//�R��Table, 
	//��إ�table�ܹ� 
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
		System.out.println("Close Exception :" + e.toString()); 
		} 
	}
	
	
	public static void main(String[] args) 
	{
		//���ݬݬO�_���` 
		RegisterMember test = new RegisterMember(); 
		test.register("a33999512", "kenchang", "a33999512@gmail.com", "0910511396", "a123456", "�x�_");
	
	} 
	
}
