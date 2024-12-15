package View.UserInterface;

import Controller.UserInterface.AddFriendController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriendView extends JPanel implements View
{
    private AddFriendController controller;
    private JTextField id_field;

    public AddFriendView(AddFriendController controller) {
        this.controller=controller;
        MakeFrame();
        MakeIDInputField();
        MakeAddButton();
    }

    @Override
    public void update(Model model, Object data) {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(null, notice);
        }
    }

    private void MakeFrame() {
        setLayout(null);
    }

    private void MakeIDInputField()
    {
        JLabel ID_desc = new JLabel("Enter ID of the user");
        ID_desc.setFont(new Font("Tahoma",Font.BOLD,22));
        ID_desc.setBounds(40,40,350,26);
        add(ID_desc);

        id_field = new JTextField();
        id_field.setBounds(70,100,200,20);
        add(id_field);
        id_field.setColumns(10);
    }

    private void MakeAddButton()
    {
        JButton addButton = new JButton("Add User");
        addButton.setBounds(40,160,150,22);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String id = id_field.getText().trim();
                controller.AddFriend(id);
                id_field.setText("");
            }
        });
    }
}
