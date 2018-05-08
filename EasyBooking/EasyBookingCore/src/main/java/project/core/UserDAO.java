package project.core;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    User getUser(String username);
    
    boolean findUser(String username, String password);

    boolean addUser(User user);

    boolean updateUser(String username, User user);
    
    boolean deleteUser(String username);
    
    int getUserId(String username);
}
