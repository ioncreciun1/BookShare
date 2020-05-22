package viewModel;

import com.sun.tools.javac.Main;
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

  public ViewModelFactory(Model model) throws SQLException, RemoteException
  {
    this.loginViewModel = new LogInViewModel(model);
    this.signUpViewModel = new SignUpViewModel(model);
    this.addBookViewModel = new AddBookViewModel(model);
    this.mainViewModel = new MainViewModel(model);
    this.searchViewModel = new SearchViewModel(model);
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
}
