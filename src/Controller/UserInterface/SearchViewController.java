package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.SearchView;

import javax.swing.*;
import java.util.ArrayList;

public class SearchViewController extends Controller
{
    private SearchView searchView;
    @Override
    public void run() {
        searchView = new SearchView(this);
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public ArrayList<String> GetAllUsers() {
        try {
            UserIO database = new UserIO();
            database.attach(searchView);
            return database.GetUserAndUsernames();
        }
        catch (Exception e ) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}

