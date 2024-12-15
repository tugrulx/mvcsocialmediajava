package View.Sign;

import Controller.Sign.SignInController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")

public class SignInView extends JPanel implements View
{
    private SignInController signInController;
    private JTextField mail;
    private JPasswordField passwd;


    public SignInView(SignInController signInController)
    {
        this.signInController = signInController;
        make_frame();
        make_field_mail();
        make_field_passwd();
        make_signIn_button();
        make_clean_button();

    }
    @Override
    public void update(Model model, Object data) {
        if (data!=null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(null,notice);
        }
    }

    private void make_frame() {setLayout(null);}

    private void make_field_mail() {
        JLabel mail_desc = new JLabel("E-mail");
        mail_desc.setFont(new Font("Tohama",Font.BOLD,18));
        mail_desc.setBounds(30,30,150,21);
        add(mail_desc);
        mail = new JTextField();
        mail.setBounds(200,30,200,21);
        add(mail);
        mail.setColumns(10);
    }

    private void make_field_passwd()
    {
        JLabel passwd_desc = new JLabel("Password");
        passwd_desc.setFont(new Font("Tohama",Font.BOLD,11));
        passwd_desc.setBounds(30,80,150,21);
        add(passwd_desc);
        passwd = new JPasswordField();
        passwd.setEchoChar('*');
        passwd.setBounds(200,80,200,21);
        add(passwd);
        passwd.setColumns(10);
    }

    private void CleanFields() {
        passwd.setText("");
        mail.setText("");
    }
    private void make_signIn_button()
    {
        JButton button = new JButton("Sign In");
        button.setBounds(150,160,150,25);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mailStr = mail.getText();
                char[] password = passwd.getPassword();
                String passwordStr = new String(password);
                signInController.SignIn(mailStr,passwordStr);
                CleanFields();
            }
        });
    }
    private void make_clean_button()
    {
        JButton clean_btn = new JButton("Clean");
        clean_btn.setBounds(330,160,150,25);
        add(clean_btn);
        clean_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CleanFields();
            }
        });
    }

}
