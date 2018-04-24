/*
 * Copyright (C) 2018 Adrian Alexandrescu. All rights reserved.
 * ADRIAN ALEXANDRESCU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * See <license.txt> for more details.
 */
package project.core;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Adrian
 * @created 25 feb. 2018
 * @version 1.0
 */
public interface UserDAO {

    List<User> getUsers() throws SQLException;

    User getUser(String username) throws SQLException;
    boolean findUser(String username, String password) throws SQLException;

    /**
     * 
     * @param book
     *            is updated with the id inside this method
     * @return <code>true</code> if the book was added successfully, <code>false</code> if the book
     *         already exists
     * @throws SQLException 
     */
    

    //int getID(User user) throws SQLException;
    boolean addUser(User user) throws SQLException;

    boolean updateUser(String username, User user) throws SQLException;
    
    boolean deleteUser(String username) throws SQLException;
}
