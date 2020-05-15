package view;

import javafx.scene.layout.Region;
import viewModel.ViewModelFactory;

public class ViewController
{
  private Region root;
  private ViewHandler handler;
  private ViewModelFactory viewModels;

  public ViewController()
  {

  }

  public Region getRoot()
  {
    System.out.println("CONTROLLER");
    System.out.println(root);
    return root;
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    this.root = root;
    this.handler = viewHandler;
    this.viewModels = viewModels;
  }

  public void reset()
  {

  }

  public ViewHandler getHandler()
  {
    return handler;
  }

  public ViewModelFactory getViewModels()
  {
    return viewModels;
  }
}
