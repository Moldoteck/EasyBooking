package proj.webserver;

import java.io.IOException;
import java.net.URI;
import java.util.List;

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

import project.core.Home;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
@MultipartConfig
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		
		//Response responser = responser = service.path("api").path("users").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		Response responser=service.path("api").path("home").path("get_home_name").path(request.getParameter("search")).request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		//pw.append("Book collection").append(  responser.toString()+"<br/>").append(responser.readEntity(String.class)).append("<br/>");

		request.setAttribute("result", "This is the result of the servlet call");
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
		for (int i = 0; i < arr.length(); i++)
		{
			sb.append("<tr><td>");
			sb.append("<img src=\""+arr.getJSONObject(i).getString("path_img")+"\" style=\"width:200px;height:200px;\">" );
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<h2 font-family=\"Script MT Bold\">"+arr.getJSONObject(i).getString("name")+"<h2><br/>");
			sb.append(arr.getJSONObject(i).getString("description"));
			sb.append("</td></tr>");
		}

		sb.append("</table></div>");
		myTable=sb.toString();
		session.setAttribute("searchResult", myTable);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
