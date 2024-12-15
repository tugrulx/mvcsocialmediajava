package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.ProfileView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class ProfileViewController extends Controller
{
    private ProfileView profileView;
    private JTable table;

    @Override
    public void run()
    {
        table = new JTable(getDataColumns(),getNameColumns());
        profileView = new ProfileView(this,table);
    }

    public ProfileView getProfileView() {
        return profileView;
    }

    public void addNewRow(Object[] values)
    {
        ((DefaultTableModel) table.getModel()).addRow(values);
    }
    public Vector<String> getNameColumns()
    {
        Vector<String> nameColumns = new Vector<String>();

        nameColumns.add("E-mail");
        nameColumns.add("Name");
        nameColumns.add("Username");
        nameColumns.add("Birth date");
        nameColumns.add("Private");
        nameColumns.add("Group Free");
        nameColumns.add("User Id");
        return nameColumns;
    }
    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> dataColumns = null;

        try {
            UserIO database = new UserIO();
            database.attach(profileView);
            dataColumns = database.getLastLogged();

        } catch (Exception ex) { }

        return dataColumns;
    }

}
