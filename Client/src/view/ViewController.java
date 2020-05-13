package view;

import javafx.scene.layout.Region;
import viewModel.ViewModelFactory;

public class ViewController
{
  private Region root;
  private ViewHandler currentVC;
  private ViewModelFactory viewModels;

  public Region getRoot()
  {
    return root;
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModel, Region root)
  {
    this.root = root;
    this.currentVC = viewHandler;
    this.viewModels = viewModels;
  }

  public void root()
  {

  }
}
