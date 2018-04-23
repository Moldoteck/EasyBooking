package project.core;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDetails {
	
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private String path_img;
	
	public UserDetails(){
		
	}
	 /**
     * 
     * @param first_name
     * @param last_name
     * @param email
     * @param phone_number
     * @param path_img
     */
	public UserDetails(String first_name, String last_name, String email, String phone_number, String path_img) {
		super();
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.phone_number=phone_number;
		this.path_img=path_img;
	}
	public String GetFirst_name() {
		return first_name;
	}
	public String GetLast_name() {
		return last_name;
	}
	public String GetEmail() {
		return email;
	}
	public String GetPhone_number() {
		return phone_number;
	}
	public String GetPath_img() {
		return path_img;
	}
	public void SetFirst_name(String first_name) {
		this.first_name=first_name;
	}
	public void SetLast_name(String last_name) {
		this.last_name=last_name;
	}
	public void SetEmail(String email) {
		this.email=email;
	}
	public void SetPhone_number(String phone_number) {
		this.phone_number=phone_number;
	}
	public void SetPath_img(String path_img) {
		this.path_img=path_img;
	}
	
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [first name = ").append(first_name).append(", last name = ").append(last_name).append(", email = ").append(email).append(", phone number = ").append(phone_number).append("\n").append(path_img).append("]");
        return builder.toString();
    }
	
	
}
