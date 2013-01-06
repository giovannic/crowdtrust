package servlet;



import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.DbInitialiser;

public class InitDBServlet extends HttpServlet{

		private static final long serialVersionUID = -2917901106721177733L;

		protected void doPost(HttpServletRequest request,
	              HttpServletResponse response) throws IOException {

			DbInitialiser.init();
			
			response.sendRedirect("/index.jsp");
	        
		}

}
