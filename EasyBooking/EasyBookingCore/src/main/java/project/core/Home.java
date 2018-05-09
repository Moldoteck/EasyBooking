package project.core;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Home {
	private String name;
	private String description;
	private Double price;
	private Integer stars;
	private Integer nr_review;
	private String path_img;
	private Integer id_user;
	
	public Home(String name, String description, Double price, Integer stars, Integer nr_review, String path_img, Integer id_user)
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	public Integer getNr_review() {
		return nr_review;
	}
	public void setNr_review(Integer nr_review) {
		this.nr_review = nr_review;
	}
	public String getPath_img() {
		return path_img;
	}
	public void setPath_img(String path_img) {
		this.path_img = path_img;
	}
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	
	
}
