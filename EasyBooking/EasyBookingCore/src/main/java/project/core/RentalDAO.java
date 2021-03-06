package project.core;

import java.util.Date;
import java.util.List;

public interface RentalDAO {

	List<Rental> getRentals();

    Rental getRental(Date check_in);
    
    List<Rental> findIdUser(Integer id_user);
    
    boolean findIdHome(Integer id_home);

    boolean addRental(Rental rental);

    boolean updateRental(Date check_in, Rental rental);
    
    boolean deleteRental(String id_home, String id_user);
}
