package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.controllers.*;
import viewModel.ViewModelFactory;

import java.util.HashMap;
import java.util.Map;

public class ViewControllerFactory
{
  private static Map<String, ViewController> viewControllerMap = new HashMap<>();

  public static ViewController getViewController(String id,
      ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
  //  System.out.println("Factory");
   // System.out.println(id);
    ViewController viewController = viewControllerMap.get(id);
    //System.out.println(viewController);
    if (viewController == null)
    {
      viewController = createViewController(id);
      viewControllerMap.put(id, viewController);
    }

    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      //System.out.println(id+".fxml");
      loader.setLocation(ViewControllerFactory.class.getResource(id+".fxml"));
      root = loader.load();
      viewController = loader.getController();
      viewController.init(viewHandler, viewModelFactory, root);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    viewController.reset();
    return viewController;
  }



  private static ViewController createViewController(String id){
    switch (id)
    {
      case "LogInView": return new LogInViewController();
      case "SignUpView": return new SignUpViewController();
      case "AddBookView": return  new AddBookViewController();
      case "MainView": return new MainViewController();
      case "SearchView": return new SearchViewController();
      case "BookInfoView": return new BookInfoViewController();
      case "MyBooksView": return new MyBooksViewController();
      default: throw new IllegalArgumentException("No such id for view controller");
    }
  }
}
