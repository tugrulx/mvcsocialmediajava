package Controller.Sign;

import Controller.UserInterface.HomeUserController;
import Core.Controller;
import Models.User;
import Models.UserIO;
import View.Sign.SignUpView;

import javax.swing.*;

public class SignUpController extends Controller
{
    private HomeSignController controller;
    private SignUpView signUpView;
    @Override
    public void run() {
        signUpView = new SignUpView(this);
    }

    public SignUpController(HomeSignController homeController) {
        this.controller = homeController;
    }
    public SignUpView getSignUpView() {
        return signUpView;
    }

    public void addUser(User user)
    {
        try {
            UserIO saver = new UserIO();
            saver.attach(signUpView);
            int situation = saver.SaveUser(user); // 1 for success 0 for failure
            if (situation==1) {
                controller.setLoggedIn(true);
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error",e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }
}
