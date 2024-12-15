package Controller.Sign;

import Controller.UserInterface.HomeUserController;
import Core.Controller;
import Models.User;
import Models.UserIO;
import View.Sign.SignInView;

import javax.swing.*;

public class SignInController extends Controller
{


    private SignInView signInView;
    private HomeSignController controller;
    @Override
    public void run() {
        signInView = new SignInView(this);
    }
    public SignInController(HomeSignController homeController) {
        this.controller = homeController;
    }
    public SignInView getSignInView() {
        return signInView;
    }

    public void SignIn(String mail,String password)
    {
        try {
            UserIO database = new UserIO();
            database.attach(signInView);
            int situation = database.SignIn(mail,password); // 1 for success 0 for failure
            if (situation==1)
            {
                controller.setLoggedIn(true);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error",e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }

    }

}
