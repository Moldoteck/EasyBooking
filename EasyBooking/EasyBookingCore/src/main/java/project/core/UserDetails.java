package project.core;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDetails {

	private String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String path_img;

    public UserDetails() {
    }

    public UserDetails(String user_id, String first_name, String last_name, String email, String phone_number, String path_img) {
        super();
        this.user_id=user_id;
        this.first_name=first_name ;
        this.last_name=last_name ;
        this.email=email;
        this.phone_number=phone_number;
        this.path_img=path_img;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDetails [first_name=").append(first_name).append(", last_name=").append(last_name).append(", email=").append(email).append(", phone_number=").append(phone_number).append(", last_name=").append(last_name).append(", photo_path=").append(path_img).append("]");
        return builder.toString();
    }

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPath_img() {
		return path_img;
	}

	public void setPath_img(String path_img) {
		this.path_img = path_img;
	}
}