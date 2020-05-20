package viewModel;

import model.Model;

public class ViewModelFactory
{
  private LogInViewModel loginViewModel;
  private SignUpViewModel signUpViewModel;
  private AddBookViewModel addBookViewModel;

  public ViewModelFactory(Model model)
  {
    this.loginViewModel = new LogInViewModel(model);
    this.signUpViewModel = new SignUpViewModel(model);
    this.addBookViewModel = new AddBookViewModel(model);
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
}
