package project.core;

import java.util.Date;
import java.util.List;

public interface RentalDAO {

	List<Rental> getRentals();
	
	List<Rental> getRentals_from_idUser(Integer id_user);

    Rental getRental(Date check_in);
    
    boolean findIdUser(Integer id_user);
    
    boolean findIdHome(Integer id_home);

    boolean addRental(Rental rental);

    boolean updateRental(Date check_in, Rental rental);
    
    boolean deleteRental(Date check_in, Date check_out);
}
