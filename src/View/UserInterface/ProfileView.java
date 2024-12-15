package View.UserInterface;

import Controller.UserInterface.ProfileViewController;
import Core.Model;
import Core.View;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
public class ProfileView  extends JPanel implements View
{
    @SuppressWarnings("unused")
    private ProfileViewController profileViewController;
    private JTable table;

    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }

    public ProfileView(ProfileViewController profileViewController,JTable table)
    {
        this.profileViewController=profileViewController;
        this.table=table;
        make_frame();;
    }

    private void make_frame() {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

}
