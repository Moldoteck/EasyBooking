package proj.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import project.core.User;
import project.core.UserDetails;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ServletSignUp")
@MultipartConfig
public class ServletSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static URI getBaseURI() {
		//TODO change the port to whatever is the server running on
		return UriBuilder.fromUri("http://localhost:8080/EasyBookingWebServicesServer/").build();
	}


	public static void main(String[] args) {

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		
		//-----ADAUGARE IN USER---
		User user=new User(request.getParameter("username"),request.getParameter("password"));
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		WebTarget service = client.target(getBaseURI());
		
		Response responser1 = service.path("api").path("users").request(MediaType.APPLICATION_XML)		
				.post(Entity.entity(user, MediaType.APPLICATION_XML), Response.class);
		
		responser1 = service.path("api").path("users").path("id").path(user.getUsername()).request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		String my_id=responser1.readEntity(String.class);
		//----ADAUGARE IN USER_DETAIL---//functie in userDAO care sa mi returneze id-ul pe care il dau mai jos
		UserDetails user_details=new UserDetails(my_id,request.getParameter("first_name"),request.getParameter("last_name"),request.getParameter("email"),request.getParameter("phone_number"),request.getParameter("path_img"));
		Response responser2 = service.path("api").path("users_details").request(MediaType.APPLICATION_XML)		
				.post(Entity.entity(user_details, MediaType.APPLICATION_XML), Response.class);
		doGet(request,response);
		//----SETARE SESIUNE PT USERNAME SI REDIRECTARE
		session.setAttribute("userId", request.getParameter("username"));
		request.getRequestDispatcher("index.jsp").forward(request, response);	
	}
	private static String getValue(Part part) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
	    StringBuilder value = new StringBuilder();
	    char[] buffer = new char[1024];
	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
	        value.append(buffer, 0, length);
	    }
	    return value.toString();
	}

}