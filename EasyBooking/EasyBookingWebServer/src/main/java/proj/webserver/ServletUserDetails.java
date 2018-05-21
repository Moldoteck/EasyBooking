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
import org.json.JSONArray;
import org.json.JSONObject;

import project.core.User;
import project.core.UserDetails;

/**
 * Servlet implementation class ServletUserDetails
 */
@WebServlet("/ServletUserDetails")
@MultipartConfig
public class ServletUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUserDetails() {
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
		// TODO Auto-generated method stub
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
			responser=service.path("api").path("users_details").path(result).request().accept(MediaType.APPLICATION_JSON)
					.get(Response.class);
			String foundDetails=responser.readEntity(String.class);
			if(foundDetails!="")
			{
				JSONObject  obj = new JSONObject(foundDetails);

				session.setAttribute("user_firstname", obj.getString("first_name"));
				session.setAttribute("user_lastname", obj.getString("last_name"));
				session.setAttribute("user_email", obj.getString("email"));
				session.setAttribute("user_phone_number", obj.getString("phone_number"));
				session.setAttribute("user_path_img", obj.getString("path_img"));
			}
			request.getRequestDispatcher("userDetails.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
