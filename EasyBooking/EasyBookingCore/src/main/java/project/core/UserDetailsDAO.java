
package project.core;

import java.sql.SQLException;
import java.util.List;


public interface UserDetailsDAO {


    UserDetails getUserDetails(String user_id) throws SQLException;
   
 
    boolean addUserDetails(UserDetails user_details) throws SQLException;
}
