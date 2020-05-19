package view.controllers;

import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

public class MainViewController extends ViewController
{
  public MainViewController()
  {
    super();
  }

  public void init(
      ViewHandler viewHandler, ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
  }

  public void openAddBookView()
  {
    super.getHandler().openView("AddBookView");
  }

}
