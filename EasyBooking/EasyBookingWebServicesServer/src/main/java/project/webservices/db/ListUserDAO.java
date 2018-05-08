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
	 
	private ListUserDAO() {
		conn = null;
	}

	@Override
	public List<User> getUsers() throws SQLException {

		connectDB();
		List<User> users  = Collections.synchronizedList(new ArrayList<>());

		statmt=conn.createStatement();
		resSet = statmt.executeQuery("SELECT * FROM USERS");

		while(resSet.next())
		{
			String  uname = resSet.getString("username");
			String  upassword = resSet.getString("password");
			users.add(new User(uname,upassword));
		}	

		disconnectDB();
		return users;
	}

	@Override
	public User getUser(String username) throws SQLException{
		connectDB();
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
			disconnectDB();
			return users.stream().filter(o -> Objects.equals(o.getUsername(), username)).findFirst().orElse(null);
		}
		catch(SQLException exc)
		{
			disconnectDB();
			return null;
		}
	}
	
	@Override
	public boolean findUser(String username, String password) throws SQLException{
		connectDB();
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
					disconnectDB();
					return true;
				}
			}
			disconnectDB();
			return false;
		}
		catch(SQLException exc)
		{
			System.out.println("Exception"+exc);
			disconnectDB();
			return false;
		}	
	}

	@Override
	public boolean addUser(User user) throws SQLException {
		connectDB();
		statmt=conn.createStatement();
		if (getUser(user.getUsername()) != null) {
			return false;
		}
		try {
			statmt.execute("INSERT INTO 'users' ('username', 'password') VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"'); ");
			disconnectDB();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			disconnectDB();
			return false;
		}
	}

	@Override
	public boolean updateUser(String username, User user) throws SQLException {
		connectDB();
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
		disconnectDB();
		return found;
	}
	
	@Override
	public boolean deleteUser(String username) throws SQLException {
		connectDB();
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
		disconnectDB();
		return found;
	}

	@Override
	public int getUserId(String username){
		connectDB();
		int user_id=-1;
		System.out.println("*******************"+username);		
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

		disconnectDB();
		return user_id;	
	}
}
