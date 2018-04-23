package project.core;

import java.sql.SQLException;
import java.util.List;

public interface UserDetailsDAO {
	List<UserDetails> getUsersDetails() throws SQLException;

    UserDetails getUserDetails(String first_name,String last_name, String email, String phone_number, String path_img, String username) throws SQLException;

    /**
     * 
     * @param book
     *            is updated with the id inside this method
     * @return <code>true</code> if the book was added successfully, <code>false</code> if the book
     *         already exists
     * @throws SQLException 
     */
    

    //int getID(User user) throws SQLException;
    boolean addUserDetails(String first_name,String last_name, String email, String phone_number, String path_img, User user) throws SQLException;

    boolean updateUserDetails(String first_name,String last_name, String email, String phone_number, String path_img, User user) throws SQLException;
    
    boolean deleteUser(String first_name,String last_name, String email, String phone_number, String path_img, String username) throws SQLException;
}
