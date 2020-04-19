package viewModel;

import model.Model;

public class ViewModelFactory
{
  private LogInViewModel loginViewModel;
  private SignUpViewModel signUpViewModel;
  public ViewModelFactory(Model model)
  {
    this.loginViewModel = new LogInViewModel(model);
    this.signUpViewModel = new SignUpViewModel(model);
  }

  public LogInViewModel getLogInViewModel()
  {
    return loginViewModel;
  }

  public SignUpViewModel getSignUpViewModel()
  {
    return signUpViewModel;
  }
}
