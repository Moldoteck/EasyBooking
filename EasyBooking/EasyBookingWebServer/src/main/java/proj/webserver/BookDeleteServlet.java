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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

/**
 * Servlet implementation class BookDeleteServlet
 */
@WebServlet("/BookDeleteServlet")
@MultipartConfig
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookDeleteServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		System.out.println(request.getParameter("deleteHome")+"9999");
		System.out.println(session.getAttribute("userId").toString()+"9999");

		Response responser1=service.path("api").path("users").path(session.getAttribute("userId").toString()).request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		String result = responser1.readEntity(String.class);;
		if(result!="-1")
		{	
			Response responser2=service.path("api").path("rental").path("id_all").path(request.getParameter("deleteHome")).path(result).request().accept(MediaType.APPLICATION_JSON)
					.get(Response.class);
			result = responser1.readEntity(String.class);
			request.getRequestDispatcher("myBookings.jsp").forward(request, response);
		}
	}

}
