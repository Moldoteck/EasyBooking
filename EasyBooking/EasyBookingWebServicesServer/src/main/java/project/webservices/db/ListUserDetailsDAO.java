package project.webservices.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import project.core.User;
import project.core.UserDAO;
import project.core.UserDetails;
import project.core.UserDetailsDAO;

public class ListUserDetailsDAO implements UserDetailsDAO {

	private static UserDetailsDAO instance = new ListUserDetailsDAO();

	public static UserDetailsDAO instance() {
		return instance;
	}

	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;

	public void disconnectDB() {
		try {
			conn.close();
			statmt.close();
			resSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Totul e inchis");
	}
	
	public void connectDB()
	{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			System.out.println("FUCK!!!");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\testdb.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("BIG Problem!!!");
			e.printStackTrace();
		}
		System.out.println("Baza Conectata!");
	}
	 
	private ListUserDetailsDAO() {
		conn = null;
	}

	@Override
	public UserDetails getUserDetails(String user_id) throws SQLException{
		connectDB();
		List<UserDetails> users_details = Collections.synchronizedList(new ArrayList<>());
		statmt=conn.createStatement();
		try{
			resSet = statmt.executeQuery("SELECT * FROM user_details");
			while(resSet.next())
			{
				String  uuser_id = resSet.getString("user_id");
				String  ufirst_name = resSet.getString("first_name");
				String  ulast_name = resSet.getString("last_name");
				String  uemail= resSet.getString("email");
				String  uphone_number= resSet.getString("phone_number");
				String  upath_img = resSet.getString("path_img");
				users_details.add(new UserDetails(uuser_id,ufirst_name,ulast_name,uemail,uphone_number,upath_img));
			}	
			disconnectDB();
			return users_details.stream().filter(o -> Objects.equals(o.getUser_id(), user_id)).findFirst().orElse(null);
		}
		catch(SQLException exc)
		{
			disconnectDB();
			return null;
		}
	}
	
	
	@Override
	public boolean addUserDetails(UserDetails user_details) throws SQLException {
		connectDB();
		statmt=conn.createStatement();
		try {
			statmt.execute("INSERT INTO 'user_details' ('user_id','first_name', 'last_name', 'email', 'phone_number', 'path_img') VALUES ('"+user_details.getUser_id()+"','"+user_details.getFirst_name()+"','"+user_details.getLast_name()+"','"+user_details.getEmail()+"','"+user_details.getPhone_number()+"','"+user_details.getPath_img()+"'); ");
			disconnectDB();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			disconnectDB();
			return false;
		}
	}

}
