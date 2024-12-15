package View.Sign;

import Controller.Sign.SignUpController;
import Core.Model;
import Models.User;
import Core.View;
import Models.UserUtil;


import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static java.awt.Font.*;
@SuppressWarnings("serial")
public class SignUpView extends JPanel implements View
{
    private SignUpController sup_controller;
    private JTextField username,real_name,mail;
    private JPasswordField passwd;
    private JFormattedTextField birth_date;
    private JCheckBox private_box; //if this checkbox signed, then user no longer appear in search.
    private JCheckBox group_free; // if this checkbox signed, then user won't be able to append groups by others.


    public SignUpView(SignUpController sup_controller)
    {
        this.sup_controller=sup_controller;
        make_frame();
        make_filed_real_name();
        make_field_username();
        make_field_mail();
        make_field_passwd();
        make_field_date();
        make_field_privatebox();
        make_field_groupbox();
        sign_up_button();
        //sign_in_button();
        clean_button();
    }
    @Override
    public void update(Model model, Object data) {
        if (data!=null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(null,notice);
        }
    }


    public void make_frame(){setLayout(null);}
    private void make_field_username() {
        JLabel username_descr = new JLabel("User name ");
        username_descr.setFont(new Font("Tohama", BOLD,11));
        username_descr.setBounds(30,70,150,15);
        add(username_descr);
        username = new JTextField();
        username.setBounds(200,70,200,20);
        add(username);
        username.setColumns(25);
    }

    private void make_filed_real_name()
    {
        JLabel rname_desc = new JLabel("Real Name ");
        rname_desc.setFont(new Font("Tahoma", BOLD,11));
        rname_desc.setBounds(30,30,150,15);
        add(rname_desc);

        real_name = new JTextField();
        real_name.setBounds(200,30,200,20);
        add(real_name);
        real_name.setColumns(25);
    }
    private void make_field_mail()
    {
        JLabel mail_desc = new JLabel("E-mail adress");
        mail_desc.setFont(new Font("Tahoma",BOLD,11));
        mail_desc.setBounds(30,110,150,15);
        add(mail_desc);
        mail= new JTextField();
        mail.setBounds(200,110,200,20);
        add(mail);
        mail.setColumns(25);
    }

    private void make_field_passwd()
    {
        JLabel passwd_desc = new JLabel("Password");
        passwd_desc.setFont(new Font("Tahoma",BOLD,11));
        passwd_desc.setBounds(30,150,155,15);
        add(passwd_desc);
        passwd = new JPasswordField();
        passwd.setEchoChar('*');
        passwd.setBounds(200,150,200,20);
        add(passwd);
        passwd.setColumns(25);
    }

    private void make_field_date() {
        JLabel label  =new JLabel("Birth date");
        label.setFont(new Font("Tahoma",BOLD,11));
        label.setBounds(30,190,150,15);
        add(label);

        try {
            birth_date =new JFormattedTextField(new MaskFormatter("##/##/####"));
            birth_date.setBounds(200,190,100,20);
            add(birth_date);
            birth_date.setColumns(25);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void make_field_privatebox()
    {
        private_box= new JCheckBox("Private Account");
        private_box.setBounds(30,240,150,25);
        add(private_box);
    }
    private void make_field_groupbox() {
        group_free = new JCheckBox("Group Free");
        group_free.setBounds(210,240,100,25);
        add(group_free);

    }

    private void sign_up_button()
    {
        JButton sign_up_btn=  new JButton("Sign Up");
        sign_up_btn.setBounds(400,300,90,25);
        add(sign_up_btn);
        UserUtil util = new UserUtil();
        sign_up_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                User user = new User();
                user.setBirth_date(util.getDateFromAString(birth_date.getText()));
                user.setMail(mail.getText());
                char[] password = passwd.getPassword();
                String passwordSTR = new String(password);
                user.setPasswd(passwordSTR);
                user.setUser_name(username.getText());
                user.setName(real_name.getText());
                user.setPrivate(private_box.isSelected());
                user.setGroupFree(group_free.isSelected());
                sup_controller.addUser(user);
                cleanFields();
            }
        });
    }

    private void clean_button() {
        JButton clean_btn = new JButton("Clean Fields");
        clean_btn.setBounds(200,300,150,25);
        add(clean_btn);

        clean_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
            }
        });
    }
    private void cleanFields() {
        username.setText("");
        real_name.setText("");
        passwd.setText("");
        mail.setText("");
        birth_date.setText("");
        private_box.setSelected(false);
        group_free.setSelected(false);
    }
}
