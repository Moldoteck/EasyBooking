package project.core;

import java.util.List;

public interface HomeDAO {
	
	List<Home> getHomes();

	List<Home> getHome(String name);
    
    Home findHomeById(String id_owner);

    boolean addHome(Home home);

    boolean updateHome(String name, Home home);
    
    boolean deleteHome(String name);
}
