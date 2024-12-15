package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.PostsView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class PostsViewController extends Controller {
    private PostsView postsView;
    private JTable table;



    @Override
    public void run() {
        table = new JTable(getDataColumns(), getNameColumns());
        postsView = new PostsView(this,table);
    }
    public void addNewRow(Object[] values)
    {
        ((DefaultTableModel) table.getModel()).addRow(values);
    }

    public PostsView getPostsView() {
        return postsView;
    }

    public Vector<String> getNameColumns()
    {
        Vector<String> nameColumns = new Vector<String>();
        nameColumns.add("Posts");
        return nameColumns;
    }

    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> dataColumns = null;
        try {
            UserIO database = new UserIO();
            database.attach(postsView);
            dataColumns = database.GetPostsOfLastLogged();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        if (dataColumns==null) {
            //JOptionPane.showMessageDialog(null,"User never posted","",JOptionPane.ERROR_MESSAGE);
        }
        return dataColumns;
    }

}
