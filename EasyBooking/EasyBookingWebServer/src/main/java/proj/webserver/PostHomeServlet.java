package proj.webserver;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.json.JSONObject;

import project.core.Home;

/**
 * Servlet implementation class PostHomeServlet
 */
@WebServlet("/PostHomeServlet")
@MultipartConfig
public class PostHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static URI getBaseURI() {
		//TODO change the port to whatever is the server running on
		return UriBuilder.fromUri("http://localhost:8080/EasyBookingWebServicesServer/").build();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		WebTarget service = client.target(getBaseURI());

		//		 Response responser1 = service.path("api").path("users").path("id").path(user.getUsername()).request().accept(MediaType.APPLICATION_JSON)
		//				.get(Response.class);
		//		String my_id=responser1.readEntity(String.class);

		Response responser=service.path("api").path("users").path(session.getAttribute("userId").toString()).request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		String result = responser.readEntity(String.class);;
		if(result!="-1")
		{
			responser=service.path("api").path("home").path("fing_home_by_id").path(result).request().accept(MediaType.APPLICATION_JSON)
					.get(Response.class);
			String foundDetails=responser.readEntity(String.class);
			if(foundDetails!="")
			{
				JSONObject  obj = new JSONObject(foundDetails);

				session.setAttribute("my_home_name", obj.getString("name"));
				session.setAttribute("my_home_description", obj.getString("description"));
				session.setAttribute("my_home_price", obj.getString("price"));
				session.setAttribute("my_home_path_img", obj.getString("path_img"));
			}
		}
		request.getRequestDispatcher("addHome.jsp").forward(request, response);
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

		Response responser=service.path("api").path("users").path(session.getAttribute("userId").toString()).request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		String result = responser.readEntity(String.class);;
		if(result!="-1")
		{
			Home home=new Home(request.getParameter("name"),request.getParameter("description"),request.getParameter("price"),"0","0",request.getParameter("image_path"),result);
			Response responser1 = service.path("api").path("home").request(MediaType.APPLICATION_XML)		
					.post(Entity.entity(home, MediaType.APPLICATION_XML), Response.class);
		}
		doGet(request, response);
	}

}
