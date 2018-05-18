package project.webservices.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;


import project.core.Rental;
import project.core.RentalDAO;

public class ListRentalDAO implements RentalDAO{

	private static RentalDAO instance = new ListRentalDAO();
	
	public static RentalDAO instance() {
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
	
	private ListRentalDAO() {
		conn = null;
	}
	
	@Override
	public List<Rental> getRentals() {
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
		List<Rental> rentals  = Collections.synchronizedList(new ArrayList<>());

		try {
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM rental");

			while(resSet.next())
			{
				Date check_inr = resSet.getDate("check_in");
				Date check_outr = resSet.getDate("check_out");
				Integer discounterr = resSet.getInt("discounter");
				Double pricer = resSet.getDouble("price");
				Integer id_homer = resSet.getInt("id_home");
				Integer id_userr = resSet.getInt("id_user");
				rentals.add(new Rental(check_inr, check_outr, discounterr, pricer, id_homer, id_userr));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			if(dissconect_flag == true)
				disconnectDB();
		}

		if(dissconect_flag == true)
			disconnectDB();
		return rentals;
	}
	
	public Rental getRental(Date check_in) {
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
		List<Rental> rentals = Collections.synchronizedList(new ArrayList<>());
		try{
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM rental");
			while(resSet.next())
			{
				Date check_inr = resSet.getDate("check_in");
				Date check_outr = resSet.getDate("check_out");
				Integer discounterr = resSet.getInt("discounter");
				Double pricer = resSet.getDouble("price");
				Integer id_homer = resSet.getInt("id_home");
				Integer id_userr = resSet.getInt("id_user");
				rentals.add(new Rental(check_inr, check_outr, discounterr, pricer, id_homer, id_userr));
			}	
			if(dissconect_flag == true)
				disconnectDB();
			return rentals.stream().filter(o -> Objects.equals(o.getCheck_in(), check_in) ).findFirst().orElse(null);
		}
		catch(SQLException exc)
		{
			if(dissconect_flag == true)
				disconnectDB();
			return null;
		}
	}

	@Override
	public boolean findIdUser(Integer id_user) {
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
		List<Rental> rentals  = Collections.synchronizedList(new ArrayList<>());
		try{
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM rental");
			while(resSet.next())
			{
				Integer id_userr = resSet.getInt("id_user");
				rentals.add(new Rental( id_userr));
			}	
			for(Rental rental : rentals)
			{
				if(rental.getId_user().equals(id_user))
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
	
	public boolean findIdHome(Integer id_home) {
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
		List<Rental> rentals  = Collections.synchronizedList(new ArrayList<>());
		try{
			statmt = conn.createStatement();
			resSet = statmt.executeQuery("SELECT * FROM rental");
			while(resSet.next())
			{
				Integer id_homer = resSet.getInt("id_home");
				rentals.add(new Rental(id_homer));
			}	
			for(Rental rental : rentals)
			{
				if(rental.getId_home().equals(id_home))
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

	//you can use this like 'add a rent for user' ??
	@Override
	public boolean addRental(Rental rental) {
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
		if (getRental(rental.getCheck_in()) != null) {
			return false;
		}
		try {
			statmt = conn.createStatement();
			statmt.execute("INSERT INTO 'rental' ('check_in', 'check_out', 'discount', 'price', 'id_home', 'id_user') VALUES ('"+ rental.getCheck_in()+"', '" +
					rental.getCheck_out() + rental.getDiscount() + rental.getPrice() + rental.getId_home() + rental.getId_user() +"'); ");
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
	public boolean updateRental(Date check_in, Rental rental) {
		// TODO Auto-generated method stub
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
			resSet = statmt.executeQuery("SELECT * FROM rental");
			while(resSet.next())
			{
				Date check_inr = resSet.getDate("check_in");
				Date check_outr = resSet.getDate("check_out");
				if(Objects.equals(check_inr,check_in))
				{
					statmt.executeUpdate("UPDATE rental SET check_in=\'"+rental.getCheck_in()+"\' WHERE check_in=\'"+check_in+"\'");
					if(!Objects.equals(check_outr, rental.getCheck_out()))
							statmt.executeUpdate("UPDATE rental SET check_out=\'"+rental.getCheck_out()+"\' WHERE check_in=\'"+rental.getCheck_in()+"\'");
					
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

	//thinking that check_in and check_out are unique
	@Override
	public boolean deleteRental(Date check_in, Date check_out) {
		// TODO Auto-generated method stub
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
			resSet = statmt.executeQuery("SELECT * FROM rental");

			while(resSet.next())
			{
				Date check_inr = resSet.getDate("check_in");
				Date check_outr = resSet.getDate("check_out");
				if(Objects.equals(check_inr,check_in) && Objects.equals(check_outr,check_out))
				{
					statmt.executeQuery("DELETE FROM rental WHERE check_in=" + check_in + "AND check_out=" + check_out);
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
	
}
