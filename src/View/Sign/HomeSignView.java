package View.Sign;

import Controller.Sign.HomeSignController;
import Core.Model;
import Core.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
@SuppressWarnings("serial")

public class HomeSignView extends JPanel implements View
{
    private JFrame main_frame;
    private HomeSignController home_controller;
    private final static int MAIN_FRAME_WIDTH = 500;
    private final static int MAIN_FRAME_HEIGHT = 350;
    private final static int MAIN_FRAME_X = 100;
    private final static int MAIN_FRAME_Y = 100;
    public HomeSignView(JFrame main_frame, HomeSignController controller) {
        this.main_frame=main_frame;
        this.home_controller=controller;

        make_main_frame();
        make_tabs();
    }

    @Override
    public void update(Model model, Object data) {

    }

    private void make_main_frame() {
        main_frame.setOpacity(1.0f);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setBounds(MAIN_FRAME_X,MAIN_FRAME_Y,MAIN_FRAME_WIDTH,MAIN_FRAME_HEIGHT);
        main_frame.setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));
        setBorder(new EmptyBorder(5,5,5,5));
        setLayout(new BorderLayout(0,0));
    }

    private void make_tabs()
    {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Sign Up",home_controller.getSignUpView()); //TODO burayı controllerdan alacağız
        tabbedPane.addTab("Sign In",home_controller.getSignInView());
        add(tabbedPane,BorderLayout.CENTER);
    }
}
