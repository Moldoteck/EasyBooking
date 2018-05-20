package project.core;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private String    username;
    private String    password;

    public User() {
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
    	return this.username;
    }
    public String getPassword()
    {
    	return this.password;
    }
    
    public void setUsername(String username)
    {
    	this.username=username;
    }
    public void setPassword(String password)
    {
    	this.password=password;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [username=").append(username).append(", password=").append(password).append("]");
        return builder.toString();
    }
}
