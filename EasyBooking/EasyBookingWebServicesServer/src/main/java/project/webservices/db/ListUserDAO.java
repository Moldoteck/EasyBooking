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
	public List<User> getUsers(){
		boolean dissconect_flag=false;
		try {
			if(conn==null||conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		List<User> users  = Collections.synchronizedList(new ArrayList<>());

		try {
			statmt=conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM USERS");

			while(resSet.next())
			{
				String  uname = resSet.getString("username");
				String  upassword = resSet.getString("password");
				users.add(new User(uname,upassword));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			if(dissconect_flag==true)
				disconnectDB();
		}

		if(dissconect_flag==true)
			disconnectDB();
		return users;
	}

	@Override
	public int getUser(String username){
		boolean dissconect_flag=false;
		try {
			if(conn==null||conn.isClosed()) {
				System.out.println("connecting");
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		List<User> users  = Collections.synchronizedList(new ArrayList<>());
		List<Integer> usersId = Collections.synchronizedList(new ArrayList<>());
		try{
			statmt=conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM users");
			while(resSet.next())
			{
				String  uname = resSet.getString("username");
				String  upassword = resSet.getString("password");
				usersId.add(resSet.getInt("ID"));
				users.add(new User(uname,upassword));
			}	
			if(dissconect_flag==true)
				disconnectDB();
			int i=0;
			for (User element : users) {
			    if(element.getUsername().equals(username))
			    {
			    	return usersId.get(i);
			    }
			    ++i;
			}
			return -1;
		}
		catch(SQLException exc)
		{
			if(dissconect_flag==true)
				disconnectDB();
			return -1;
		}
	}
	
	@Override
	public boolean findUser(String username, String password){
		boolean dissconect_flag=false;
		try {
			if(conn==null||conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		List<User> users  = Collections.synchronizedList(new ArrayList<>());
		try{
			statmt=conn.createStatement();
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
	public boolean addUser(User user){
		boolean dissconect_flag=false;
		try {
			if(conn==null||conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		if (findUser(user.getUsername(),user.getPassword()) != false) {
			return false;
		}
		try {
			statmt=conn.createStatement();
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
	public boolean updateUser(String username, User user){
		boolean dissconect_flag=false;
		try {
			if(conn==null||conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		boolean found = false;
		try {
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
		} catch (SQLException e) {
			if(dissconect_flag==true)
				disconnectDB();
			e.printStackTrace();
		}
		
		if(dissconect_flag==true)
			disconnectDB();
		return found;
	}
	
	@Override
	public boolean deleteUser(String username){
		boolean dissconect_flag=false;
		try {
			if(conn==null||conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		boolean found = false;
		try {
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
		} catch (SQLException e) {
			if(dissconect_flag==true)
				disconnectDB();
			e.printStackTrace();
		}
		
		if(dissconect_flag==true)
			disconnectDB();
		return found;
	}

	@Override
	public int getUserId(String username){
		boolean dissconect_flag=false;
		int user_id=-1;
		try {
			if(conn==null||conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return user_id;
		}
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
