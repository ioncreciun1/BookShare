package viewModel;

import com.sun.tools.javac.Main;
import model.Model;

public class ViewModelFactory
{
  private LogInViewModel loginViewModel;
  private SignUpViewModel signUpViewModel;
  private AddBookViewModel addBookViewModel;
  private MainViewModel mainViewModel;

  public ViewModelFactory(Model model)
  {
    this.loginViewModel = new LogInViewModel(model);
    this.signUpViewModel = new SignUpViewModel(model);
    this.addBookViewModel = new AddBookViewModel(model);
    this.mainViewModel = new MainViewModel(model);
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
}
