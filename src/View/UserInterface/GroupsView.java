package View.UserInterface;

import Controller.UserInterface.GroupsViewController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
public class GroupsView extends JPanel implements View
{
    @SuppressWarnings("unused")
    private GroupsViewController controller;
    private JTable table;
    @Override
    public void update(Model model, Object data) {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }
    public GroupsView(GroupsViewController controller,JTable table) {
        this.controller = controller;
        this.table=table;
        MakeFrame();
    }

    private void MakeFrame()
    {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
