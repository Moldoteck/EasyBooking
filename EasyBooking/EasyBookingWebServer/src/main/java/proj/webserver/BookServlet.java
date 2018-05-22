package proj.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
import org.json.JSONArray;
import org.json.JSONObject;

import project.core.Home;
import project.core.Rental;
import project.core.User;

@WebServlet("/BookServlet")
@MultipartConfig
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
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

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		WebTarget service = client.target(getBaseURI());


		if(request.getParameter("search").isEmpty())
		{
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else
		{
			Response responser1=service.path("api").path("users").path(session.getAttribute("userId").toString()).request().accept(MediaType.APPLICATION_JSON)
					.get(Response.class);
			Response responser2 = service.path("api").path("home").path("fing_home_by_id").path(session.getAttribute("id_Home").toString()).request().accept(MediaType.APPLICATION_JSON)
					.get(Response.class);

			String my_id_user=responser1.readEntity(String.class);

			String my_id_home=responser2.readEntity(String.class);

			DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
			Integer numar_integer = null;
			Double numar_double = null;
			try {
				Rental rental=new Rental(
						format.parse(request.getParameter("check_in")),
						format.parse(request.getParameter("check_out")),
						Integer.parseInt(request.getParameter("discount")),
						Double.parseDouble(request.getParameter("final_price")),
						Integer.parseInt(session.getAttribute("id_Home").toString()),Integer.parseInt(my_id_user));
				Response responser3 = service.path("api").path("rental").request(MediaType.APPLICATION_XML)		
						.post(Entity.entity(rental, MediaType.APPLICATION_XML), Response.class);

			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//----SETARE SESIUNE PT USERNAME SI REDIRECTARE
			System.out.println("Check_in: " + request.getParameter("check_in")+ ", check_out: "+ request.getParameter("check_out")+", discount: "+ 
					request.getParameter("discount")+", price: "+ request.getParameter("final_price")+ ", id home: " + my_id_home + ", id_user" + my_id_user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}


	}

	//in cazul in care vrei sa modifici	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		WebTarget service = client.target(getBaseURI());

		User user=new User(request.getParameter("username"),request.getParameter("password"));
		Response responser1 = service.path("api").path("users").request(MediaType.APPLICATION_XML)		
				.post(Entity.entity(user, MediaType.APPLICATION_XML), Response.class);

		responser1 = service.path("api").path("users").path("id").path(user.getUsername()).request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		String my_id_user=responser1.readEntity(String.class);

		Integer numar_integer = null;
		Rental rental = new Rental(numar_integer.parseInt(my_id_user));
		Response responser = service.path("api").path("rental").path("check_in").request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(rental, MediaType.APPLICATION_JSON), Response.class);
		pw.append("Replace rental with check_in rental").append(  responser.toString()+"<br/>").append(responser.readEntity(String.class));;

		responser = service.path("api").path("rental").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		pw.append("Rental collection").append(  responser.toString()+"<br/>").append(responser.readEntity(String.class)).append("<br/>");		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		WebTarget service = client.target(getBaseURI());
		Response responser = service.path("api").path("rental").request().accept(MediaType.APPLICATION_JSON).delete(Response.class);
		pw.append("DELETE request is not allowed").append(  responser.toString()+"<br/>").append(responser.readEntity(String.class)).append("<br/>");

		responser = service.path("api").path("rental").path("check_in_out").request().accept(MediaType.APPLICATION_JSON)
				.delete(Response.class);
		pw.append("Delete home with check_in_out").append( responser.toString()+"<br/>").append(responser.readEntity(String.class)).append("<br/>");

		responser = service.path("api").path("users").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		pw.append("Rentalr collection").append( responser.toString()+"<br/>").append(responser.readEntity(String.class));;
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
