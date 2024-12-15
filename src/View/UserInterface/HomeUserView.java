package View.UserInterface;

import Controller.UserInterface.HomeUserController;
import Core.Model;
import Core.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
@SuppressWarnings("serial")
public class HomeUserView extends JPanel implements View
{
    private JFrame mainFrame;
    private HomeUserController userController;
    private final static int MAIN_FRAME_WIDTH = 700;
    private final static int MAIN_FRAME_HEIGHT = 500;
    private final static int MAIN_FRAME_X = 200;
    private final static int MAIN_FRAME_Y = 200;

    public HomeUserView(JFrame mainFrame,HomeUserController controller)
    {
        this.mainFrame=mainFrame;
        this.userController=controller;
        MakeMainFrame();
        MakeTabs();
    }

    @Override
    public void update(Model model, Object data) {

    }

    private void MakeMainFrame()
    {
        mainFrame.setOpacity(1.0f);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(MAIN_FRAME_X,MAIN_FRAME_Y,MAIN_FRAME_WIDTH,MAIN_FRAME_HEIGHT);
        mainFrame.setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));
        setBorder(new EmptyBorder(5,5,5,5));
        setLayout(new BorderLayout(0,0));
    }
    private void MakeTabs()
    {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Profile",userController.GetProfileView());
        tabbedPane.addTab("Posts",userController.GetPostsView());
        tabbedPane.add("Friends",userController.GetFriendsView());
        tabbedPane.add("Groups",userController.GetGroupView());
        tabbedPane.add("Search",userController.GetSearchView());
        tabbedPane.add("Create Post",userController.GetCreatePostView());
        tabbedPane.add("Create Group",userController.GetCreateGroupView());
        tabbedPane.add("Add friend",userController.GetAddFriendView());
        add(tabbedPane,BorderLayout.CENTER);
    }
}
