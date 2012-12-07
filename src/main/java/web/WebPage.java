package web;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public abstract class WebPage {

  protected String name;

  protected String head;

  protected String top;

  protected List <WebElement> body;

  protected String context;
  
  public WebPage(String user, String context) {
	this.context = context;
    head = "<link href=\"" + context + "/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\">" +
              "\n" + "<title>" + "\n" + "CrowdTrust - " + name + "\n" +
              "\n" + "</title>" + "\n";

    top =     "<div class=\"header\">" + "\n" + "<div class=\"left\">" +
              "\n" + "<h1>CrowdTrust</h1>" + "\n" + "</div>" + 
              "\n" + "<div class=\"right\">" + "\n" + "<p>Logged in as: " + user + "</p>" +
              "\n" + "<p>Sign Out</p>" + "\n" + "</div>" + "\n" + "</div>";
    body = new ArrayList<WebElement>();
  }

  public String generate(){
    StringBuilder page = new StringBuilder();
    page.append("<!doctype html>\n");
    page.append("<html>\n");
    page.append("<head>\n");
    page.append(head);
    for(WebElement e : body){
      page.append(e.generateHead());
    }
    page.append("</head>");
    page.append("<body>");
    page.append(top);
    for(WebElement e : body){
      page.append(e.generateBody());
    }
    page.append("</body>");
    page.append("</html>");
    return page.toString();
  }

}
