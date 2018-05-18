package proj.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

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

import project.core.Home;

@WebServlet("/TestRentalAndHome")
@MultipartConfig
public class TestRentalAndHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestRentalAndHome() {
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
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		WebTarget service = client.target(getBaseURI());
		Response responser1;
		responser1 = service.path("api").path("users").path("id").path("admin").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		System.out.println("blabla");
		String my_id=responser1.readEntity(String.class);
		//----ADAUGARE IN USER_DETAIL---//functie in userDAO care sa mi returneze id-ul pe care il dau mai jos
		Home home=new Home(request.getParameter("name"),request.getParameter("description"),request.getParameter("price"),request.getParameter("stars"),request.getParameter("nr_review"),request.getParameter("path_img"),my_id);
		Response responser2 = service.path("api").path("home").request(MediaType.APPLICATION_XML)		
				.post(Entity.entity(home, MediaType.APPLICATION_XML), Response.class);
		//----SETARE SESIUNE PT USERNAME SI REDIRECTARE
		request.setAttribute("result", "This is the result of the servlet call");
		String temp=responser2.readEntity(String.class);
		System.out.println(request.getParameter("name")+request.getParameter("description")+request.getParameter("price")+request.getParameter("stars")
		+request.getParameter("nr_review")+request.getParameter("path_img")+temp);

		System.out.println(request.getParameter("username")+request.getParameter("password")+temp);
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
//isn't ok