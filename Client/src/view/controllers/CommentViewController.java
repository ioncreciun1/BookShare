package view.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

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

  }



  public void cancelMain()
  {
    super.getHandler().closeView();
    super.getHandler().openView("BookInfoView");
  }

  public void postComment() throws SQLException, RemoteException
  {
    if (super.getViewModels().getCommentViewModel().checkComment())
    {
      super.getViewModels().getCommentViewModel().addComment(getViewModels().getBookInfoViewModel().getBook(), comment.getText());
      super.getHandler().openView("BookInfoView");
    }
  }
}
