package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.FriendsView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class FriendsViewController extends Controller {
    private FriendsView friendsView;
    private JTable table;
    @Override
    public void run() {
        table = new JTable(getDataColumns(),getNameColumns());
        friendsView = new FriendsView(this,table);
    }

    public FriendsView getFriendsView() {
        return friendsView;
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
        nameColumns.add("User ID");
        return nameColumns;
    }
    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> dataColumns = null;

        try {
            UserIO database = new UserIO();
            database.attach(friendsView);
            dataColumns = database.getFriendsOfUser();

        } catch (Exception ex) { }

        return dataColumns;
    }



}
