package View.UserInterface;

import Controller.UserInterface.CreateGroupController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class CreateGroupView extends JPanel implements View
{

    private CreateGroupController controller;
    private JList<String> friendList;
    private JTextField nameField;
    private JTextField descrField;

    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }
    public CreateGroupView(CreateGroupController controller,JList<String> friendList)
    {
        this.controller=controller;
        this.friendList=friendList;
        MakeFrame();
        MakeList();
        MakeNameField();
        MakeDescriptionField();
        MakeButton();
    }

    public void MakeFrame()
    {
        setLayout(null);
    }
    public void MakeList()
    {
        friendList.setBounds(150, 350, 400, 400);
        friendList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selections
        add(friendList);
    }

    public void MakeButton()
    {
        JButton button = new JButton("Create Group");
        button.setBounds(180,180,300,18);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String[] selectedFriends = friendList.getSelectedValuesList().toArray(new String[0]);
                ArrayList<String> members  = new ArrayList<>();
                for (int x=0; x<selectedFriends.length; x++) {
                    if (!members.contains(selectedFriends[x])) {
                        members.add(selectedFriends[x]);
                    }
                }
                //Vector<String> members = new Vector<>(Arrays.asList(selectedFriends));
                controller.SaveNewGroup(members,nameField.getText(),descrField.getText());
                //controller.SaveNewGroup(friendList.getSelectedValuesList(),nameField.getText(),descrField.getText());
                CleanFields();
            }
        });
    }
    public void MakeNameField() {
        JLabel nameDesc = new JLabel("Group Name");
        nameDesc.setBounds(50,50,250,18);
        nameDesc.setFont(new Font("Tahoma",Font.BOLD,14));
        add(nameDesc);

        nameField = new JTextField();
        nameField.setBounds(420,50,250,18);
        add(nameField);
        nameField.setColumns(10);
    }
    public void MakeDescriptionField()
    {
        JLabel label = new JLabel("Description");
        label.setFont(new Font("Tahoma",Font.BOLD,14));
        label.setBounds(50,125,250,18);
        add(label);

        descrField = new JTextField();
        descrField.setBounds(420,125,250,18);
        add(descrField);
        descrField.setColumns(10);
    }

    public void CleanFields() {
        nameField.setText("");
        descrField.setText("");
    }


}
