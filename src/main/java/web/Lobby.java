package web;

import java.lang.StringBuilder;

public class Lobby extends WebPage{

  public Lobby(String name, String context){
    super(name, context);
  }

  public void addCrowdTable(){
    body.add(new CrowdTable());
  }

  public void addClientTable(){
    body.add(new ClientTable());
  }

}
