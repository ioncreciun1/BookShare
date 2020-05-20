package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory factory;

  public ViewHandler(ViewModelFactory factory)
  {
    this.factory = factory;
    this.currentScene = new Scene(new Region());
  }

  public void openView(String id)
  {
    Region root = null;
    root = ViewControllerFactory.getViewController(id, this, factory).getRoot();
    System.out.println(id);
    System.out.println(root);
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
      case "LogInView":
        primaryStage.setWidth(610);
        primaryStage.setHeight(500);
      break;
      case "SignUpView":
        primaryStage.setWidth(710);
        primaryStage.setHeight(500);
      break;
      case "AddBookView":
        primaryStage.setHeight(580);
        primaryStage.setWidth(595);
      break;
      case "MainView":
        primaryStage.setHeight(620);
        primaryStage.setWidth(480);
      break;
    }

    primaryStage.show();
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("LogInView");
  }
}
