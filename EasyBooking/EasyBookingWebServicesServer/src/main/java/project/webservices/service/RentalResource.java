package project.webservices.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.core.Rental;
import project.webservices.db.ListRentalDAO;

@Path("/rental")
public class RentalResource {
	 private static Logger log = LoggerFactory.getLogger(RentalResource.class);
 
 	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Rental> getRentals() throws SQLException {
        log.info("getUsers");
        return ListRentalDAO.instance().getRentals();
    }
 
	 @GET
	 @Path("{check_in}")
	 @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	 public Rental getRental(@PathParam("check_in") Date check_in) throws SQLException {
	     return ListRentalDAO.instance().getRental(check_in);
	 }
 	 
 	 @GET
     @Path("{check_in}/{check_out}/{discount}/{price}/{id_home}/{id_user}")
     @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
     public boolean findRental(@PathParam("username") Date check_in, @PathParam("check_out") Date check_out,@PathParam("discount") Integer discount,
    		 @PathParam("price") Double price, @PathParam("id_home") Integer id_home, @PathParam("id_user") Integer id_user) throws SQLException {
         return ListRentalDAO.instance().findRental(check_in, check_out, discount, price, id_home, id_user);
     }
 	 
   @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postRentals(@Context UriInfo uriInfo, Rental rental) throws IllegalArgumentException, UriBuilderException, SQLException {
        log.info("postRentals: {}", rental);
        Response response;
        if (ListRentalDAO.instance().addRental(rental)) {
            response =  Response.created(uriInfo.getRequestUriBuilder().build()).entity(rental).build();
        } else {
            response = Response.seeOther(uriInfo.getRequestUriBuilder().build()).build();
            response =Response.notModified().build();
        }
        log.info("[RentalResource] postRentals: response status: {} {}", response.getStatus(), response.getStatusInfo());
        return response;
    }
 	   
 	@POST
    @Path("{check_in}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postRental(@PathParam("check_in") Date check_in) {
        return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "PUT", "DELETE", "PATCH").build();
    }
 	  
 	 @PUT
     public Response putRentals(List<Rental> rentals) {
         //In this context having this method makes no sense, because usually one does not replace the entire book collection
         return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
     }

   @PUT
    @Path("{check_in}")
    public Response putUser(@PathParam("check_in") Date check_in, Rental newRental) throws SQLException {
        //Only the replace functionality is implemented
        Response response;
        if (ListRentalDAO.instance().updateRental(check_in, newRental)) {
            response = Response.noContent().build();
        } else {
            response = Response.status(Status.NOT_FOUND).build();
        }
        return response;
    }
   
   @DELETE
   public Response deleteRentals() {
       return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
   }
   
   @DELETE
   @Path("{check_in}/{check_out}")
   public Response deleteRental(@PathParam("check_in") Date check_in, @PathParam("check_out") Date check_out) throws SQLException {
       //Only the replace functionality is implemented
       Response response;
       if (ListRentalDAO.instance().deleteRental(check_in, check_out)) {
           response = Response.noContent().build();
       } else {
           response = Response.status(Status.NOT_FOUND).build();
       }
       return response;
   }

   /*@PATCH
   public Response patchUsers() {
       return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
   }
   
   @PATCH
   @Path("{username}")
   @Consumes("application/x-www-form-urlencoded")
   public Response patchUsername(@PathParam("username") String username, MultivaluedMap<String, String> map) throws SQLException {
       log.info("patchUsername: {}", username);
       //Only the replace functionality is implemented
       Response response;
       User user = ListUserDAO.instance().getUser(username);
       if (user == null) {
           return Response.status(Status.NOT_FOUND).build();
       }
       for (Entry<String, List<String>> entry : map.entrySet()) {
           String value = entry.getValue().get(0);
           switch (entry.getKey()) {
               case "username":
                   user.setUsername(value);
                   break;
               case "password":
                   user.setPassword(value);
                   break;
               default:
                   return Response.status(Status.BAD_REQUEST).build();
           }
       }
       if (ListUserDAO.instance().updateUser(username, user)) {
           response = Response.noContent().build();
       } else {
           response = Response.status(Status.NOT_FOUND).build();
       }
       return response;
   }*/

}
