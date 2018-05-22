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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class myBookingsServlet
 */
@WebServlet("/myBookingsServlet")
@MultipartConfig
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
			Response responser=service.path("api").path("rental").path("get_rental_user").path(result).request().accept(MediaType.APPLICATION_JSON)
					.get(Response.class);
			String lst=responser.readEntity(String.class);
			if(lst!=null)
			{
				if(lst!="")
				{
					JSONObject  obj = new JSONObject("{\"data\" : "+lst+"}");
					JSONArray arr = obj.getJSONArray("data");
					String myTable="";
					StringBuilder sb= new StringBuilder();
					sb.append("<style>td {" + 
							"    padding: 15px;" + 
							"    text-align: left;" + 
							"}</style>");
					sb.append("<div style=\"overflow-x:auto;\">\r\n<table>");
					for (int i = 0; i < arr.length(); i++)
					{

						int myid=arr.getJSONObject(i).getInt("id_home");
						Response responser2=service.path("api").path("home").path("fing_home_by_id").path(""+myid).request().accept(MediaType.APPLICATION_JSON)
								.get(Response.class);
						String lst2=responser2.readEntity(String.class);
						
						JSONObject  obj2 = new JSONObject(lst2);
						String image=obj2.getString("path_img");
						
						
						sb.append("<tr><td>");

						sb.append("<img src='"+image+"' style=\\\"width:200px;height:200px;padding:10px; />");
						sb.append("<b>Check-in: </b>"+arr.getJSONObject(i).getString("check_in"));
						sb.append("</td><td><b>Check-out: </b>"+arr.getJSONObject(i).getString("check_out"));
						sb.append("</td><td><b>Discount: </b>"+arr.getJSONObject(i).getInt("discount"));
						sb.append("</td><td><b>Price: </b>"+arr.getJSONObject(i).getDouble("price"));
						sb.append("</td><td>");
						sb.append("<form action=\"BookDeleteServlet\" method=\"post\" enctype=\"multipart/form-data\"><button type=\"submit\">Delete" );
						sb.append("</button><input type=\"hidden\" name=\"deleteHome\" value=\""+arr.getJSONObject(i).getInt("id_home")+"\"></form></td>");
						sb.append("</td></tr>");
					}
					sb.append("</table></div>");
					myTable=sb.toString();
					System.out.println(myTable);
					session.setAttribute("bookingResult", myTable);
					
				}
			}
			request.getRequestDispatcher("myBookings.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
