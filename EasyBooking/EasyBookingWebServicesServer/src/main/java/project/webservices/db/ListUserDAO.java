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

public class ListUserDAO implements UserDAO {

	private static UserDAO instance = new ListUserDAO();

	public static UserDAO instance() {
		return instance;
	}

	public static Connection conn;
	public static ResultSet resSet;
	public static Statement statmt;

	public void disconnectDB() {
		try {
			conn.close();
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
			System.out.println("FUCK!!!");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\testdb.db");
		} catch (SQLException e) {
			System.out.println("BIG Problem!!!");
			e.printStackTrace();
		}
		System.out.println("Baza Conectata!");
	}
	 
	private ListUserDAO() {
		conn = null;
	}

	@Override
	public List<User> getUsers() throws SQLException {
		boolean dissconect_flag=false;
		if(conn==null||conn.isClosed()) {
			connectDB();
			dissconect_flag=true;
		}
		List<User> users  = Collections.synchronizedList(new ArrayList<>());

		statmt=conn.createStatement();
		resSet = statmt.executeQuery("SELECT * FROM USERS");

		while(resSet.next())
		{
			String  uname = resSet.getString("username");
			String  upassword = resSet.getString("password");
			users.add(new User(uname,upassword));
		}	

		if(dissconect_flag==true)
			disconnectDB();
		return users;
	}

	@Override
	public User getUser(String username) throws SQLException{
		boolean dissconect_flag=false;
		if(conn==null||conn.isClosed()) {
			System.out.println("connecting");
			connectDB();
			dissconect_flag=true;
		}
		List<User> users  = Collections.synchronizedList(new ArrayList<>());
		statmt=conn.createStatement();
		try{
			resSet = statmt.executeQuery("SELECT * FROM users");
			while(resSet.next())
			{
				String  uname = resSet.getString("username");
				String  upassword = resSet.getString("password");
				users.add(new User(uname,upassword));
			}	
			if(dissconect_flag==true)
				disconnectDB();
			return users.stream().filter(o -> Objects.equals(o.getUsername(), username)).findFirst().orElse(null);
		}
		catch(SQLException exc)
		{
			if(dissconect_flag==true)
				disconnectDB();
			return null;
		}
	}
	
	@Override
	public boolean findUser(String username, String password) throws SQLException{
		boolean dissconect_flag=false;
		if(conn==null||conn.isClosed()) {
			connectDB();
			dissconect_flag=true;
		}
		List<User> users  = Collections.synchronizedList(new ArrayList<>());
		boolean value= false;
		statmt=conn.createStatement();
		try{
			resSet = statmt.executeQuery("SELECT * FROM users");
			while(resSet.next())
			{
				String  uname = resSet.getString("username");
				String  upassword = resSet.getString("password");
				users.add(new User(uname,upassword));
			}	
			for(User usr : users)
			{
				if(usr.getUsername().equals(username)&&usr.getPassword().equals(password))
				{
					if(dissconect_flag==true)
						disconnectDB();
					return true;
				}
			}
			if(dissconect_flag==true)
				disconnectDB();
			return false;
		}
		catch(SQLException exc)
		{
			System.out.println("Exception"+exc);
			if(dissconect_flag==true)
				disconnectDB();
			return false;
		}	
	}

	@Override
	public boolean addUser(User user) throws SQLException {
		boolean dissconect_flag=false;
		if(conn==null||conn.isClosed()) {
			connectDB();
			dissconect_flag=true;
		}
		statmt=conn.createStatement();
		if (getUser(user.getUsername()) != null) {
			return false;
		}
		try {
			statmt.execute("INSERT INTO 'users' ('username', 'password') VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"'); ");
			System.out.println(dissconect_flag);
			if(dissconect_flag==true)
				disconnectDB();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(dissconect_flag==true)
				disconnectDB();
			return false;
		}
	}

	@Override
	public boolean updateUser(String username, User user) throws SQLException {
		boolean dissconect_flag=false;
		if(conn==null||conn.isClosed()) {
			connectDB();
			dissconect_flag=true;
		}
		boolean found = false;
		statmt=conn.createStatement();
		resSet = statmt.executeQuery("SELECT * FROM USERS");

		while(resSet.next())
		{
			String  uname = resSet.getString("username");
			String  upass = resSet.getString("password");
			if(Objects.equals(uname,username))
			{
				statmt.executeUpdate("UPDATE users SET username=\'"+user.getUsername()+"\' WHERE username=\'"+username+"\'");
				if(!Objects.equals(upass, user.getPassword()))
						statmt.executeUpdate("UPDATE users SET password=\'"+user.getPassword()+"\' WHERE username=\'"+user.getUsername()+"\'");
				
				found=true;
				break;
			}
		}	
		if(dissconect_flag==true)
			disconnectDB();
		return found;
	}
	
	@Override
	public boolean deleteUser(String username) throws SQLException {
		boolean dissconect_flag=false;
		if(conn==null||conn.isClosed()) {
			connectDB();
			dissconect_flag=true;
		}
		boolean found = false;
		statmt=conn.createStatement();
		resSet = statmt.executeQuery("SELECT * FROM users");

		while(resSet.next())
		{
			String  uname = resSet.getString("username");
			if(Objects.equals(uname,username))
			{
				statmt.executeQuery("DELETE FROM users WHERE username="+username);
				found=true;
				break;
			}
		}	
		if(dissconect_flag==true)
			disconnectDB();
		return found;
	}

	@Override
	public int getUserId(String username){
		boolean dissconect_flag=false;
		try {
			if(conn==null||conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int user_id=-1;
		try {
			statmt=conn.createStatement();
			resSet = statmt.executeQuery("SELECT id FROM users where username='"+username+"'");
			if(resSet.next())
			{
				user_id=resSet.getInt("id");
			}	
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
		if(dissconect_flag==true)
			disconnectDB();
		return user_id;	
	}
}
