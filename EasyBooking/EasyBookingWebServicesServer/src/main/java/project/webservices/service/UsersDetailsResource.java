package project.webservices.service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.core.User;
import project.core.UserDetails;
import project.webservices.db.ListUserDAO;
import project.webservices.db.ListUserDetailsDAO;

// e.g., @ApplicationPath("api") + @Path("/books") =>
// http://localhost:8080/BookManagerWebServices/api/books
@Path("/users_details")
public class UsersDetailsResource {

    private static Logger log = LoggerFactory.getLogger(UsersDetailsResource.class);


    @GET
    @Path("{user_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UserDetails getUserDetails(@PathParam("user_id") String user_id) throws SQLException {
        return ListUserDetailsDAO.instance().getUserDetails(user_id);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postUsersDetails(@Context UriInfo uriInfo, UserDetails user_details) throws IllegalArgumentException, UriBuilderException, SQLException {
        log.info("postUsersDetails: {}", user_details);
        Response response;
        if (ListUserDetailsDAO.instance().addUserDetails(user_details)) {
            response =  Response.created(uriInfo.getRequestUriBuilder().build()).entity(user_details).build();
        } else {
            response = Response.seeOther(uriInfo.getRequestUriBuilder().build()).build();
        }
        log.info("[UserResource] postUsersDetails: response status: {} {}", response.getStatus(), response.getStatusInfo());
        return response;
    }
}