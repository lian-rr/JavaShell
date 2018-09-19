package com.javashell.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Logic {

  boolean windows;
  
  public Logic() {
    this.windows = isWindows();
  }

  public String executeCommand(String cmd) {
    return execute(cmd);
  }
  
  public String executeCommands(String[] cmds){
    StringBuilder resultBuilder = new StringBuilder();
    
    Arrays.stream(cmds).forEach(cmd -> resultBuilder.append(execute(cmd)));
    
    return resultBuilder.toString();
  }

  private String execute(String cmd) {
    String command = cmd;
    
    Process process;
    StringBuilder resultBuilder = new StringBuilder();
    
    if(this.windows)
      command = String.format("cmd /c \"%s\"", cmd);
    
    try {
      process = Runtime.getRuntime().exec(command);

      BufferedReader br = new BufferedReader(new InputStreamReader((process.getInputStream())));

      String line;

      while ((line = br.readLine()) != null) {
        resultBuilder.append(line).append("\n");
      }

    } catch (IOException ex) {
      System.out.println(ex);
      return String.format(ex.getMessage(), ex.getCause());
    }

    return resultBuilder.toString();
  }
  
  private boolean isWindows(){
    return System.getProperty("os.name").startsWith("Windows");
  }
}
