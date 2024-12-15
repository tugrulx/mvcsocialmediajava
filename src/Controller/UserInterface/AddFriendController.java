package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.AddFriendView;

import javax.swing.*;
import java.util.Vector;

public class AddFriendController extends Controller {
    private AddFriendView addFriendView;
    private FriendsViewController friendsViewController;
    @Override
    public void run() {
        addFriendView = new AddFriendView(this);
    }
    public AddFriendController(FriendsViewController fwc) {
        this.friendsViewController=fwc;
    }
    public AddFriendView getAddFriendView() {
        return addFriendView;
    }

    public void AddFriend(String userID) {
        try {
            UserIO database = new UserIO();
            database.attach(addFriendView);
            Vector<Object> userFields = database.GetUserFromId(userID);
            if (!((String) userFields.get(4)).equals("private")) {
                database.UpdateFriends(userID);
                Object[] metadata = new Object[userFields.size()];
                for (int x=0; x< metadata.length; x++) {
                    metadata[x] = userFields.get(x);
                }
                friendsViewController.addNewRow(metadata);
                JOptionPane.showMessageDialog(null, "added", "Success", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "user profile private", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error occurred", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
