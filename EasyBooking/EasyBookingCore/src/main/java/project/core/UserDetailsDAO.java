
package project.core;

import java.sql.SQLException;
import java.util.List;


public interface UserDetailsDAO {
    UserDetails getUserDetails(String user_id);
    boolean addUserDetails(UserDetails user_details);
}