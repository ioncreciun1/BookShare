package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import viewmodel.LogViewModel;

public class LogViewController
{
  @FXML private ListView<String> logList;
  private LogViewModel viewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, LogViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    logList.setItems(viewModel.getLogs());
  }

  public void reset()
  {
    // empty
  }

  public Region getRoot()
  {
    return root;
  }

  public void onEnter(ActionEvent event)
  {
  }
}
