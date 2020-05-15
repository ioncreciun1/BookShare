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
  private ViewModelFactory factory;

  public ViewHandler(ViewModelFactory factory)
  {
    this.factory = factory;
    this.currentScene = new Scene(new Region());
  }

  public void openView(String id)
  {
    Region root;
    root = ViewControllerFactory.getViewController(id, this, factory).getRoot();
    System.out.println("VIEW HANDLER");
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
      case "LogInView":  primaryStage.setWidth(610);
        break;
      case "SignUpView":  primaryStage.setWidth(710);
        break;
    }
    primaryStage.setHeight(500);
    primaryStage.show();
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("LogInView");
  }

}
