package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.GroupsView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class GroupsViewController extends Controller {
    private GroupsView groupsView;
    private JTable table;

    @Override
    public void run()
    {
        table = new JTable(getDataColumns(),getNameColumns());
        groupsView = new GroupsView(this,table);
    }

    public GroupsView getGroupsView() {
        return groupsView;
    }
    public void addNewRow(Object[] values)
    {
        ((DefaultTableModel) table.getModel()).addRow(values);
    }

    public Vector<Object> getNameColumns()
    {
        Vector<Object> nameColumns = new Vector<>();
        nameColumns.add("Group Name");
        nameColumns.add("Description");
        nameColumns.add("Members");
        return nameColumns;
    }

    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> dataColumns = null;
        try
        {
            UserIO database = new UserIO();
            database.attach(groupsView);
            dataColumns = database.GetGroupList();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }

        return dataColumns;
    }

}
