package Controller.Sign;

import Controller.UserInterface.HomeUserController;
import Core.Controller;
import View.Sign.HomeSignView;
import View.Sign.SignInView;
import View.Sign.SignUpView;
import View.UserInterface.HomeUserView;

public class HomeSignController extends Controller
{
    private boolean isLoggedIn= false;
    private HomeSignView homeView;

    private SignInController signInController = new SignInController(this);
    private SignUpController signUpController = new SignUpController(this);


    @Override
    public void run() {
        signInController.run();
        signUpController.run();
        homeView = new HomeSignView(mainFrame,this);
        addView("HomeSignController",homeView);
        mainFrame.setVisible(true);

    }

    public SignInView getSignInView()
    {
        return signInController.getSignInView();
    }
    public SignUpView getSignUpView() {
        return signUpController.getSignUpView();
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean IsLogged() {
        return isLoggedIn;
    }
    public void SetVisible(boolean value) {
        mainFrame.setVisible(value);
    }
}
