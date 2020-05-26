import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
      throws IOException, NotBoundException, SQLException
  {

    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);
  }
}
