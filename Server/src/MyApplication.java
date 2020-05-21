import javafx.application.Application;
import javafx.stage.Stage;
import mediator.RemoteModel;
import mediator.ServerModel;
import model.Model;
import model.ModelManage;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyApplication extends Application
{
  private RemoteModel server;
  public void start(Stage primaryStage) throws IOException, SQLException
  {
    Model model = new ModelManage();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

    try
    {
      server = new ServerModel(model);
    }
    catch (RemoteException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }
}
