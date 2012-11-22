package web;

public abstract class WebPage {

  public String name;

  public String head;

  public String top;

  public List <WebElement> body;

  Webpage(String user) {
    header = "<link href=\"stylesheet.css\" rel=\"stylesheet\" type=\"text/css\">" +
              "\n" + "<title>" + "\n" + "CrowdTrust - " + name + "\n" +
              "\n" + "</title>" + "\n";

    top =     "<div class=\"header\">" + "\n" + "<div class=\"left\">" +
              "\n" + "<h1>CrowdTrust</h1>" + "\n" + "</div>" + 
              "\n" + "<div class=\"right\">" + "\n" + "<p>Logged in as: " + user + "</p>" +
              "\n" + "<p>Sign Out</p>" + "\n" + "</div>" + "\n" + "</div>";
  }

  public String generate(){
    StringBuilder page = new StringBuilder();
    page.append("<!doctype html>\n");
    page.append("<html>\n");
    page.append("<head>\n");
    page.append(header);
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
