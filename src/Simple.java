import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Simple extends HttpServlet {

	private static final long serialVersionUID = 5412833768798945941L;
int accesses = 0;

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      accesses++;
      out.print("Number of times this servlet has been accessed:" + accesses);
   }
}
