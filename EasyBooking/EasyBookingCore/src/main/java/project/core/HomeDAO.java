package project.core;

import java.util.List;

public interface HomeDAO {
	
	List<Home> getHomes();

    Home getHome(String name);
    
    boolean findHome(String name, String description, Double price, Integer stars, Integer nr_review, String path_img, Integer id_user);

    boolean addHome(Home home);

    boolean updateHome(String name, Home home);
    
    boolean deleteHome(String name);
    
    int getHomeId(String name);
    
}
