package View.UserInterface;

import Controller.UserInterface.FriendsViewController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class FriendsView extends JPanel implements View
{
    @SuppressWarnings("unused")
    private FriendsViewController friendsViewController;
    private JTable table;
    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }

    public FriendsView(FriendsViewController friendsViewController,JTable table)
    {
        this.friendsViewController=friendsViewController;
        this.table =table;
        MakeFrame();
    }

    public void MakeFrame() {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }



}
