package project.core;

import java.util.Date;
import java.util.List;

public interface RentalDAO {

	List<Rental> getRentals();

    Rental getRental(Date check_in);
    
    boolean findRental(Date check_in, Date check_out, Integer discount, Double price, Integer id_home, Integer id_user);

    boolean addRental(Rental rental);

    boolean updateRental(Date check_in, Rental rental);
    
    boolean deleteRental(Date check_in, Date check_out);
    
   // int getRentalId(Date check_in);
    
}
