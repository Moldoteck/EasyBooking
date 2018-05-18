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

import project.core.Home;
import project.core.HomeDAO;

public class ListHomeDAO implements HomeDAO{

	private static HomeDAO instance = new ListHomeDAO();
	
	public static HomeDAO instance() {
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
		System.out.println("Baza Conectataa!");
	}
	
	private ListHomeDAO() {
		conn = null;
	}
	
	@Override
	public List<Home> getHomes() {
		// TODO Auto-generated method stub
		boolean dissconect_flag = false;
		try {
			if(conn == null || conn.isClosed()) {
				connectDB();
				dissconect_flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		List<Home> homes  = Collections.synchronizedList(new ArrayList<>());

		try {
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM home");

			while(resSet.next())
			{
				String  nameh = resSet.getString("name");
				String  descriptionh = resSet.getString("description");
				String priceh = resSet.getString("price");
				String starsh = resSet.getString("stars");
				String nr_reviewh = resSet.getString("nr_review");
				String path_imgh = resSet.getString("path_img");
				String id_userh = resSet.getString("id_user");
				homes.add(new Home(nameh,descriptionh,priceh,starsh,nr_reviewh,path_imgh,id_userh));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			if(dissconect_flag == true)
				disconnectDB();
		}

		if(dissconect_flag == true)
			disconnectDB();
		return homes;
	}

	@Override
	public Home getHome(String name) {
		// TODO Auto-generated method stub
		boolean dissconect_flag = false;
		try {
			if(conn == null || conn.isClosed()) {
				System.out.println("connecting");
				connectDB();
				dissconect_flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		List<Home> homes = Collections.synchronizedList(new ArrayList<>());
		try{
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM home");
			while(resSet.next())
			{
				String  nameh = resSet.getString("name");
				String  descriptionh = resSet.getString("description");
				String priceh = resSet.getString("price");
				String starsh = resSet.getString("stars");
				String nr_reviewh = resSet.getString("nr_review");
				String path_imgh = resSet.getString("path_img");
				String id_userh = resSet.getString("id_user");
				homes.add(new Home(nameh,descriptionh,priceh,starsh,nr_reviewh,path_imgh,id_userh));
			}	
			if(dissconect_flag == true)
				disconnectDB();
			return homes.stream().filter(o -> Objects.equals(o.getName(), name)).findFirst().orElse(null);
		}
		catch(SQLException exc)
		{
			if(dissconect_flag == true)
				disconnectDB();
			return null;
		}
	}

	@Override
	public boolean findHome(String name) {
		// TODO Auto-generated method stub
		boolean dissconect_flag=false;
		try {
			if(conn == null || conn.isClosed()) {
				connectDB();
				dissconect_flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		List<Home> homes  = Collections.synchronizedList(new ArrayList<>());
		try{
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM home");
			while(resSet.next())
			{
				String  nameh = resSet.getString("name");
				homes.add(new Home(nameh));
			}	
			for(Home home : homes)
			{
				if(home.getName().equals(name))
				{
					if(dissconect_flag==true)
						disconnectDB();
					return true;
				}
			}
			if(dissconect_flag == true)
				disconnectDB();
			return false;
		}
		catch(SQLException exc)
		{
			System.out.println("Exception"+exc);
			if(dissconect_flag == true)
				disconnectDB();
			return false;
		}	
	}

	@Override
	public boolean addHome(Home home) {
		// TODO Auto-generated method stub
		boolean dissconect_flag=false;
		try {
			if(conn == null || conn.isClosed()) {
				connectDB();
				dissconect_flag = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		if (getHome(home.getName()) != null) {
			return false;
		}
		try {
			statmt = conn.createStatement();
			statmt.execute("INSERT INTO 'home' ('name', 'description', 'price', 'stars', 'nr_review', 'path_img', 'id_user') VALUES ('"+ home.getName()+"', '" +
			home.getDescription() + home.getPrice() + home.getStars() + home.getNr_review() + home.getPath_img() + home.getId_user() +"'); ");
			System.out.println(dissconect_flag);
			if(dissconect_flag == true)
				disconnectDB();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(dissconect_flag == true)
				disconnectDB();
			return false;
		}
	}

	@Override
	public boolean updateHome(String name, Home home) {
		// TODO Auto-generated method stub
		boolean dissconect_flag = false;
		try {
			if(conn == null || conn.isClosed()) {
				connectDB();
				dissconect_flag = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		boolean found = false;
		try {
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM home");

			while(resSet.next())
			{
				String  nameh = resSet.getString("name");
				String  descriptionh = resSet.getString("description");
				String priceh = resSet.getString("price");
				String starsh = resSet.getString("stars");
				String nr_reviewh = resSet.getString("nr_review");
				String path_imgh = resSet.getString("path_img");
				String id_userh = resSet.getString("id_user");
				if(Objects.equals(nameh,name))
				{
					statmt.executeUpdate("UPDATE home SET name=\'"+home.getName()+"\' WHERE name=\'"+name+"\'");
					if(!Objects.equals(descriptionh, home.getDescription()))
					{
						statmt.executeUpdate("UPDATE home SET description=\'"+home.getDescription()+"\' WHERE name=\'"+home.getName()+"\'");
						if(!Objects.equals(priceh, home.getPrice()))
						{
							statmt.executeUpdate("UPDATE home SET price=\'"+home.getPrice()+"\' WHERE name=\'"+home.getName()+"\'");
							if(!Objects.equals(starsh, home.getStars()))
							{
								statmt.executeUpdate("UPDATE home SET stars=\'"+home.getStars()+"\' WHERE name=\'"+home.getName()+"\'");
								if(!Objects.equals(nr_reviewh, home.getNr_review()))
								{
									statmt.executeUpdate("UPDATE home SET nr_review=\'"+home.getNr_review()+"\' WHERE name=\'"+home.getName()+"\'");
									if(!Objects.equals(path_imgh, home.getPath_img()))
									{
										statmt.executeUpdate("UPDATE home SET path_img=\'"+home.getPath_img()+"\' WHERE name=\'"+home.getName()+"\'");
										if(!Objects.equals(id_userh, home.getId_user()))
										{
											statmt.executeUpdate("UPDATE home SET id_user=\'"+home.getId_user()+"\' WHERE name=\'"+home.getName()+"\'");
										}
									}

								}
							}

						}
					}
					found = true;
					break;
				}
			}	
		} catch (SQLException e) {
			if(dissconect_flag == true)
				disconnectDB();
			e.printStackTrace();
		}
		
		if(dissconect_flag == true)
			disconnectDB();
		return found;
	}

	@Override
	public boolean deleteHome(String name) {
		// TODO Auto-generated method stub
		boolean dissconect_flag=false;
		try {
			if(conn == null || conn.isClosed()) {
				connectDB();
				dissconect_flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		boolean found = false;
		try {
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM home");

			while(resSet.next())
			{
				String  nameh = resSet.getString("name");
				if(Objects.equals(nameh,name))
				{
					statmt.executeQuery("DELETE FROM home WHERE name="+name);
					found=true;
					break;
				}
			}	
		} catch (SQLException e) {
			if(dissconect_flag == true)
				disconnectDB();
			e.printStackTrace();
		}
		
		if(dissconect_flag == true)
			disconnectDB();
		return found;
	}

}
