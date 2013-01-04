package web;
import db.TaskDb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CrowdTable implements WebElement {

	private HttpServletRequest request;

	public CrowdTable(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public int getId() {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		int id = (Integer) session.getAttribute("account_id");
		return id;
	}

  public String generateHead(){
    return "";
  }
  public String generateBody(){
    String body = "<div id=\"crowd\" class=\"clientcrowd\"> <p>Crowd</p>";
		List<String> tasks = new ArrayList<String> ();
		tasks = getTaskList();
		for(String task : tasks) {
			body += "<p>" + task + "</p>";
		}
 		body += "</div>";
		return body;
  }

	public List<String> getTaskList() {
		return TaskDb.getTasksForCrowdId(getId());
	}

}
