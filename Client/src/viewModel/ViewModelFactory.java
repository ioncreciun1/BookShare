package viewModel;

import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelFactory
{
  private LogInViewModel loginViewModel;
  private SignUpViewModel signUpViewModel;
  private AddBookViewModel addBookViewModel;
  private MainViewModel mainViewModel;
  private SearchViewModel searchViewModel;
  private BookInfoViewModel bookInfoViewModel;
  private MyBooksViewModel myBooksViewModel;
  private CommentViewModel commentViewModel;
  private UserInfoViewModel userInfoViewModel;

  public ViewModelFactory(Model model) throws SQLException, RemoteException
  {
    this.loginViewModel = new LogInViewModel(model);
    this.signUpViewModel = new SignUpViewModel(model);
    this.addBookViewModel = new AddBookViewModel(model);
    this.mainViewModel = new MainViewModel(model);
    this.searchViewModel = new SearchViewModel(model);
    this.bookInfoViewModel = new BookInfoViewModel(model);
    this.myBooksViewModel = new MyBooksViewModel(model);
    this.commentViewModel = new CommentViewModel(model);
    this.userInfoViewModel = new UserInfoViewModel(model);
  }

  public LogInViewModel getLogInViewModel()
  {
    return loginViewModel;
  }

  public SignUpViewModel getSignUpViewModel()
  {
    return signUpViewModel;
  }

  public AddBookViewModel getAddBookViewModel()
  {
    return addBookViewModel;
  }

  public MainViewModel getMainViewModel()
  {
    return mainViewModel;
  }

  public SearchViewModel getSearchViewModel()
  {
    return searchViewModel;
  }

  public MyBooksViewModel getMyBooksViewModel() {return myBooksViewModel;}

  public BookInfoViewModel getBookInfoViewModel()
  {
    return bookInfoViewModel;
  }

  public CommentViewModel getCommentViewModel()
  {
    return commentViewModel;
  }

  public UserInfoViewModel getUserInfoViewModel() {return userInfoViewModel; }
}
