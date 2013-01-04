package web;

import java.lang.StringBuilder;
import javax.servlet.http.HttpServletRequest;

public class Lobby extends WebPage{

  public Lobby(String name, String context){
    super(name, context);
  }

  public void addCrowdTable(HttpServletRequest request){
    body.add(new CrowdTable(request));
  }

  public void addClientTable(){
    body.add(new ClientTable());
  }

}
