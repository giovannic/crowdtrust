package crowdtrust;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.servlet.ServletException;

//@MultipartConfig
public class TaskServlet extends HttpServlet
{

  protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                            throws ServletException, IOException
  {
    //String name = request.getParameter("name");

    // Extract images
    //Part imagesPart = request.getPart("images");
    //String imagesFilename = getFilename(imagesPart);

    //task = new BinaryImageClassificationTask(name, question, images);

    //int msg = request.getParts().size();

    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();

    //out.print(msg);
    out.print("bonjour monde");
  }

};
