package web;

import java.util.StringBuilder;

public class Lobby extends WebPage{

  Lobby(){
  }

  addCrowdTable(){
    body.add(new CrowdTable());
  }

  addClientTable(){
    body.add(new ClientTable());
  }

}
