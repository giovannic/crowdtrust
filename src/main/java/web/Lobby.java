package web;

import java.lang.StringBuilder;

public class Lobby extends WebPage{

  public Lobby(String name){
    super(name);
  }

  public void addCrowdTable(){
    body.add(new CrowdTable());
  }

  public void addClientTable(){
    body.add(new ClientTable());
  }

}
