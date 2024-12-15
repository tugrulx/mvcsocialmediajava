package View.UserInterface;

import Controller.UserInterface.CreatePostController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePostView extends JPanel implements View {
    private CreatePostController controller;
    private JTextField postField;
    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(null, notice);
        }
    }

    public CreatePostView(CreatePostController controller) {
        this.controller=controller;
        make_frame();
        MakePostField();
        MakeSaveButton();
    }
    private void make_frame() { setLayout(null); }

    private void MakePostField()
    {
        JLabel desc = new JLabel("Enter your post");
        desc.setFont(new Font("Tahoma",Font.BOLD,20));
        desc.setBounds(50,40,300,25);
        add(desc);
        postField = new JTextField();
        postField.setBounds(60,100,270,20);
        postField.setAutoscrolls(true);
        add(postField);
        postField.setColumns(10);
    }
    private void MakeSaveButton()
    {
        JButton saveBtn = new JButton("Add Post");
        saveBtn.setBounds(50,200,200,22);
        add(saveBtn);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String post = postField.getText();
                controller.AddPost(post);
                postField.setText("");
            }
        });
    }
}
