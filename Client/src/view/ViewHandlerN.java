package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

public class ViewHandlerN
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory factory;
  private ViewController currentVC;

  public ViewHandlerN(ViewModelFactory factory)
  {
    this.factory = factory;
    this.currentScene = new Scene(new Region());
  }

  public void openView(String id)
  {
    Region root = null;
    root = ViewControllerFactory.getViewController(id, this, factory).getRoot();
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("LogIn");
  }
}
