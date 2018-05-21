package proj.webserver;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class myBookingsServlet
 */
@WebServlet("/myBookingsServlet")
public class myBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myBookingsServlet() {
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
				

				Response responser1=service.path("api").path("users").path(session.getAttribute("userId").toString()).request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
				String result = responser1.readEntity(String.class);;
				if(result!="-1")
				{	 
					Response responser=service.path("api").path("rental").path("getRentals_from_idUser").path(result).request().accept(MediaType.APPLICATION_JSON)
							.get(Response.class);
					String lst=responser.readEntity(String.class);
					JSONObject  obj = new JSONObject("{\"data\" : "+lst+"}");
					JSONArray arr = obj.getJSONArray("data");
					String myTable="";
					StringBuilder sb= new StringBuilder();
					sb.append("<style>td {" + 
							"    padding: 15px;" + 
							"    text-align: left;" + 
							"}</style>");
					sb.append("<div style=\"overflow-x:auto;\">\r\n<table>");
					
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					// Am observat ca arr.getJSONObject(i) este gol, deci nu are ce afisa, nu intra in for
					//cred ca exista o problema cu path(rental), ruland in webservices nu imi afiseaza inchirierile
					
					for (int i = 0; i < arr.length(); i++)
					{
						sb.append("<tr><td>");
						sb.append("Check-in: "+arr.getJSONObject(i).getString("check_in"));
						sb.append("</td><td>Check-out: "+arr.getJSONObject(i).getString("check_out"));
						sb.append("</td><td>Discount: "+arr.getJSONObject(i).getString("discount"));
						sb.append("</td><td>Price: "+arr.getJSONObject(i).getString("price"));
						sb.append("</td><td>Delete: ");
						sb.append("</td></tr>");
					}
					sb.append("</table></div>");
					myTable=sb.toString();
					System.out.println(myTable);
					session.setAttribute("bookingResult", myTable);
	
					request.getRequestDispatcher("myBookings.jsp").forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
