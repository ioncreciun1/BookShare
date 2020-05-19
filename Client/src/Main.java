import javafx.application.Application;

import java.rmi.RMISecurityManager;

public class Main
{
  public static void main(String[] args)
  {
    System.setProperty("java.security.policy","all.policy");
    if(System.getSecurityManager() == null)
    {
      System.setSecurityManager(new RMISecurityManager());
    }
    Application.launch(MyApplication.class);
  }
}
