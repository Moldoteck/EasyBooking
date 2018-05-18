package project.core;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Home {
	private String name;
	private String description;
	private String price;
	private String stars;
	private String nr_review;
	private String path_img;
	private String id_user;
	
	public Home(String name, String description, String price, String stars, String nr_review, String path_img, String id_user)
	{
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stars = stars;
		this.nr_review = nr_review;
		this.path_img = path_img;
		this.id_user = id_user;
	}
	public Home(String name)
	{
		this.name = name;
	}
	//it's ok?
	 @Override
	    public String toString() {
	        StringBuilder builder = new StringBuilder();
	        builder.append("Home [name=").append(name).append(", description=").append(description).append(", price=").append(price).append(", stars=")
	        	.append(stars).append(", nr_review=").append(nr_review).append(", path_img=").append(path_img).append(", id_user=").append(id_user).append("]");
	        return builder.toString();
	    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getNr_review() {
		return nr_review;
	}
	public void setNr_review(String nr_review) {
		this.nr_review = nr_review;
	}
	public String getPath_img() {
		return path_img;
	}
	public void setPath_img(String path_img) {
		this.path_img = path_img;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	
	
}
