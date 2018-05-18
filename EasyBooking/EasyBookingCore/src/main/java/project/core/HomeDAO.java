package project.core;

import java.util.List;

public interface HomeDAO {
	
	List<Home> getHomes();

    Home getHome(String name);
    
    boolean findHome(String name);

    boolean addHome(Home home);

    boolean updateHome(String name, Home home);
    
    boolean deleteHome(String name);
    
    //see it
}
