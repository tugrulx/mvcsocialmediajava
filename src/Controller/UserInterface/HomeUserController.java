package Controller.UserInterface;

import Core.Controller;
import View.Sign.SignInView;
import View.UserInterface.*;

public class HomeUserController extends Controller
{
    private HomeUserView userView;
    private ProfileViewController profileViewController = new ProfileViewController();
    private PostsViewController postsViewController = new PostsViewController();
    private SearchViewController searchViewController= new SearchViewController();
    private GroupsViewController groupsViewController = new GroupsViewController();
    private FriendsViewController friendsViewController = new FriendsViewController();
    private CreateGroupController createGroupController = new CreateGroupController(groupsViewController);
    private CreatePostController createPostController = new CreatePostController(postsViewController);
    private AddFriendController addFriendController = new AddFriendController(friendsViewController);

    @Override
    public void run()
    {
        profileViewController.run();
        postsViewController.run();
        searchViewController.run();
        groupsViewController.run();
        friendsViewController.run();
        createGroupController.run();
        createPostController.run();
        addFriendController.run();
        userView = new HomeUserView(userPart,this);
        addUserView("HomeUserController",userView);
        userPart.setVisible(true);
    }

    public ProfileView GetProfileView()
    {
        return profileViewController.getProfileView();
    }
    public PostsView GetPostsView() {
        return postsViewController.getPostsView();
    }
    public SearchView GetSearchView() {
        return searchViewController.getSearchView();
    }
    public GroupsView GetGroupView() {
        return groupsViewController.getGroupsView();
    }
    public FriendsView GetFriendsView() {
        return friendsViewController.getFriendsView();
    }
    public CreatePostView GetCreatePostView() {
        return createPostController.getCreatePostView();
    }
    public CreateGroupView GetCreateGroupView() {
        return createGroupController.getCreateGroupView();
    }
    public AddFriendView GetAddFriendView() {
        return addFriendController.getAddFriendView();
    }
}
