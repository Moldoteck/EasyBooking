package project.webservices.service;

import java.sql.SQLException;
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

import project.core.Home;
import project.webservices.db.ListHomeDAO;

//e.g., @ApplicationPath("api") + @Path("/books") =>
//http://localhost:8080/BookManagerWebServices/api/books
@Path("/home")
public class HomeResource {
	
	 private static Logger log = LoggerFactory.getLogger(HomeResource.class);

	 @GET
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public List<Home> getUsers() throws SQLException {
	        log.info("getHomes");
	        return ListHomeDAO.instance().getHomes();
	    }
	 
	 @GET
	    @Path("get_home_name/{name}")
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public List<Home> getHome(@PathParam("name") String name) throws SQLException {
	        return ListHomeDAO.instance().getHome(name);
	    }
	 
	 @GET
	    @Path("fing_home_by_id/{id_owner}")
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Home findHome(@PathParam("id_owner") String id_owner) throws SQLException {
	        return ListHomeDAO.instance().findHomeById(id_owner);
	    }
	 
	 @POST
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Response postHomes(@Context UriInfo uriInfo, Home home) throws IllegalArgumentException, UriBuilderException, SQLException {
	        log.info("postHomes: {}", home);
	        Response response;
	        if (ListHomeDAO.instance().addHome(home)) {
	            response =  Response.created(uriInfo.getRequestUriBuilder().build()).entity(home).build();
	        } else {
	            response = Response.seeOther(uriInfo.getRequestUriBuilder().build()).build();
	            response =Response.notModified().build();
	        }
	        log.info("[HomeResource] postHomes: response status: {} {}", response.getStatus(), response.getStatusInfo());
	        return response;
	    }

	 @PUT
	    @Path("{name}")
	    public Response putHome(@PathParam("name") String name, Home newHome) throws SQLException {
	        //Only the replace functionality is implemented!!!
	        Response response;
	        if (ListHomeDAO.instance().updateHome(name, newHome)) {
	            response = Response.noContent().build();
	        } else {
	            response = Response.status(Status.NOT_FOUND).build();
	        }
	        return response;
	    }
	 
	 @DELETE
	    @Path("{name}")
	    public Response deleteHome(@PathParam("name") String name) throws SQLException {
	        //Only the replace functionality is implemented
	        Response response;
	        if (ListHomeDAO.instance().deleteHome(name)) {
	            response = Response.noContent().build();
	        } else {
	            response = Response.status(Status.NOT_FOUND).build();
	        }
	        return response;
	    }
}
