package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private LogInViewController logViewController;
  private SignUpViewController signUpViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("LogIn");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "LogIn":
        root = loadLogInView("LOGIN.fxml");
        break;
      case "SignUp":
        root = loadSignUpView("SIGN UP.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    switch (id)
    {
      case "LogIn":
        primaryStage.setWidth(610);
      case "SignUp":
        primaryStage.setWidth(700);
    }
    primaryStage.setHeight(500);
    primaryStage.show();
  }


  private Region loadLogInView(String fxmlFile)
  {
    if (logViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        logViewController = loader.getController();
        logViewController.init(this, viewModelFactory.getLogInViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      logViewController.reset();
    }
    return logViewController.getRoot();
  }
  private Region loadSignUpView(String fxmlFile)
  {
    if (signUpViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        signUpViewController = loader.getController();
        signUpViewController.init(this, viewModelFactory.getSignUpViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      signUpViewController.reset();
    }
    return signUpViewController.getRoot();
  }

}
