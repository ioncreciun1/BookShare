package view;

import viewModel.ViewModelFactory;

import java.util.HashMap;
import java.util.Map;

public class ViewControllerFactory
{
  private static Map<String, ViewController> viewControllerMap = new HashMap<>();

  public static ViewController getViewController(String id, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
  }
}
