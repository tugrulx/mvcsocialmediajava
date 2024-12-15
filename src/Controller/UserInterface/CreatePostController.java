package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.CreatePostView;

import javax.swing.*;
import java.util.Vector;


public class CreatePostController extends Controller {
    private CreatePostView createPostView;
    private PostsViewController postViewController;

    public CreatePostController(PostsViewController controller) {
        this.postViewController =controller;
    }
    @Override
    public void run() {
        createPostView = new CreatePostView(this);
    }

    public CreatePostView getCreatePostView() {
        return createPostView;
    }

    public void AddPost(String post)
    {
        if (post==null || post=="") {
            JOptionPane.showMessageDialog(null,"Trying to post empty","Null", JOptionPane.ERROR_MESSAGE);
        }
        try {
            UserIO database = new UserIO();
            database.attach(createPostView);
            Vector<Object> newRow = database.SavePost(post);
            Object[] values = new Object[newRow.size()];
            for (int x=0; x<newRow.size(); x++ ) {
                values[x]=newRow.get(x);
            }
            postViewController.addNewRow(values);
            JOptionPane.showMessageDialog(null,"Posted succesfully","",JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);

        }
    }
}
