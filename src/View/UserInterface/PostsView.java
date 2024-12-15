package View.UserInterface;

import Controller.UserInterface.PostsViewController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PostsView extends JPanel implements View
{
    @SuppressWarnings("unused")
    private PostsViewController controller;
    private JTable table;

    @Override
    public void update(Model model, Object data) {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }
    public PostsView(PostsViewController controller,JTable table) {
        this.controller= controller;
        this.table=table;
        MakeFrame();
    }

    private void MakeFrame() {
        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);
    }
}
