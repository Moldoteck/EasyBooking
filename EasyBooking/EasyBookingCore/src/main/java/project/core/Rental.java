package project.core;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rental {
	private Date check_in;
	private Date check_out;
	private Integer discount;
	private Double price;
	private Integer id_home;
	private Integer id_user;
	
	public Rental(Date check_in, Date check_out, Integer discount, Double price, Integer id_home, Integer id_user)
	{
		this.check_in = check_in;
		this.check_out = check_out;
		this.discount = discount; 
		this.price = price;
		this.id_home = id_home;
		this.id_user = id_user;
	}
	
	 @Override
	    public String toString() {
	        StringBuilder builder = new StringBuilder();
	        builder.append("Rental [check_in=").append(check_in).append(", check_out=").append(check_out).append(", dicount=").append(discount).append(", price=")
	        	.append(price).append(", id_home=").append(id_home).append(", id_user=").append(id_user).append("]");
	        return builder.toString();
	    }

	public Date getCheck_in() {
		return check_in;
	}

	public void setCheck_in(Date check_in) {
		this.check_in = check_in;
	}

	public Date getCheck_out() {
		return check_out;
	}

	public void setCheck_out(Date check_out) {
		this.check_out = check_out;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getId_home() {
		return id_home;
	}

	public void setId_home(Integer id_home) {
		this.id_home = id_home;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	 
}
