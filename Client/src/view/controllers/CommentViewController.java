package view.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

public class CommentViewController extends ViewController
{
  public TextArea comment;
  public Label error;

  public CommentViewController()
  {
    super();
  }

  public void init(
      ViewHandler viewHandler, ViewModelFactory viewModel, Region root)
  {
    super.init(viewHandler, viewModel, root);
    super.getViewModels().getCommentViewModel().reset();
    comment.textProperty().bindBidirectional(super.getViewModels().getCommentViewModel().getCommentProperty());
    error.textProperty().bindBidirectional(super.getViewModels().getCommentViewModel().getError());
    //reset();
  }

//  public void reset()
//  {
//    super.getViewModels().getCommentViewModel().reset();
//    comment.clear();
//  }

  public void cancelMain() // ble hz,  ar trebui sa deschida acela care era cand a apasat write nu unul random
  {
    super.getHandler().closeView();
    super.getHandler().openView("BookInfoView");
  }

  public void postComment()
  {
    if (super.getViewModels().getCommentViewModel().checkComment())
    {
      super.getHandler().openView("BookInfoView");
    }
  }
}
