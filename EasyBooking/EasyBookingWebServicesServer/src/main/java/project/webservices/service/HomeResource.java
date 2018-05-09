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
	    @Path("/id/{name_id}")
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public int getHomeId(@PathParam("name_id") String name_id) throws SQLException {
	        return ListHomeDAO.instance().getHomeId(name_id);
	    }
	 
	 @GET
	    @Path("{name}")
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Home getHome(@PathParam("name") String name) throws SQLException {
	        return ListHomeDAO.instance().getHome(name);
	    }
	 
	 @GET
	    @Path("{username}/{password}/{description}/{price}/{stars}/{nr_review}/{path_img}/{id_user}")
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public boolean findUser(@PathParam("name") String name, @PathParam("description") String description,@PathParam("price") Double price,@PathParam("stars") Integer stars
	    		,@PathParam("nr_review") Integer nr_review,@PathParam("path_img") String path_img,@PathParam("id_user") Integer id_user) throws SQLException {
	        return ListHomeDAO.instance().findHome(name,description,price,stars,nr_review,path_img,id_user);
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
	 
	 @POST
	    @Path("{name}")
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Response postHome(@PathParam("name") String name) {
	        return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "PUT", "DELETE", "PATCH").build();
	    }
	 
	 @PUT
	    public Response putHomes(List<Home> homes) {
	        //In this context having this method makes no sense, because usually one does not replace the entire book collection
	        return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
	    }

	 @PUT
	    @Path("{name}")
	    public Response putHome(@PathParam("name") String name, Home newHome) throws SQLException {
	        //Only the replace functionality is implemented
	        Response response;
	        if (ListHomeDAO.instance().updateHome(name, newHome)) {
	            response = Response.noContent().build();
	        } else {
	            response = Response.status(Status.NOT_FOUND).build();
	        }
	        return response;
	    }
	 
	 @DELETE
	    public Response deleteHomes() {
	        return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
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
	 
	 @PATCH
	    public Response patchHomes() {
	        return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
	    }

	 @PATCH
	    @Path("{name}")
	    @Consumes("application/x-www-form-urlencoded")
	    public Response patchName(@PathParam("name") String name, MultivaluedMap<String, String> map) throws SQLException {
	        log.info("patchName: {}", name);
	        //Only the replace functionality is implemented
	        Response response;
	        Home home = ListHomeDAO.instance().getHome(name);
	        if (home == null) {
	            return Response.status(Status.NOT_FOUND).build();
	        }
	        for (Entry<String, List<String>> entry : map.entrySet()) {
	            String value = entry.getValue().get(0);
	            switch (entry.getKey()) {
	                case "name":
	                    home.setName(value);
	                    break;
	                case "description":
	                    home.setDescription(value);
	                    break;
	                case "price":
	                    home.setPrice(Double.parseDouble(value));
	                    break;
	                case "stars":
	                    home.setStars(Integer.parseInt(value));
	                    break;
	                case "nr_review":
	                    home.setNr_review(Integer.parseInt(value));
	                    break;
	                case "path_img":
	                    home.setPath_img(value);
	                case "id_user":
	                    home.setId_user(Integer.parseInt(value));
	                    break;
	                default:
	                    return Response.status(Status.BAD_REQUEST).build();
	            }
	        }
	        if (ListHomeDAO.instance().updateHome(name, home)) {
	            response = Response.noContent().build();
	        } else {
	            response = Response.status(Status.NOT_FOUND).build();
	        }
	        return response;
	    }

}
