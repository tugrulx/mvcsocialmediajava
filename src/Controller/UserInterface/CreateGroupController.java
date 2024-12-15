package Controller.UserInterface;

import Core.Controller;
import Models.UserIO;
import View.UserInterface.CreateGroupView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class CreateGroupController extends Controller {
    private CreateGroupView createGroupView;
    private JList<String> list;
    private GroupsViewController groupsViewController;
    @Override
    public void run()
    {
        MakeList();
        createGroupView =new CreateGroupView(this,list);
    }
    public CreateGroupController(GroupsViewController groupsViewController)
    {
        this.groupsViewController=groupsViewController;
    }

    public CreateGroupView getCreateGroupView() {
        return createGroupView;
    }

    public void MakeList()
    {
        try {
            UserIO database = new UserIO();
            List<String> names = database.GetGroupAllowedFriendsOfUserAsNames();
            DefaultListModel model = new DefaultListModel();
            for (String s : names) {
                model.addElement(s);
            }
            list = new JList<>(model);
        }
        catch (Exception e ) {
            System.out.println(e);
        }
    }

    public void SaveNewGroup(List<String> selectedValuesList,String name,String desc)
    {
        try {
            UserIO database = new UserIO();
            List<String> namesOfFriend = database.GetGroupAllowedFriendsOfUserAsNames() ;
            List<String> idsOfFriend =database.GetGroupAllowedFriendsOfUserAsIDs();
            HashMap<String,String> map = new HashMap<>();
            for (int x=0; x<namesOfFriend.size(); x++) {
                map.put(idsOfFriend.get(x),namesOfFriend.get(x));
            }
            List<String> memberIDs = new ArrayList<>();
            for (String member : selectedValuesList) {
                for (int x=0; x<map.size(); x++)
                {
                    if (map.get(idsOfFriend.get(x)).equals(member)) memberIDs.add(idsOfFriend.get(x));
                }
            }
            Vector<Object> newRecord = database.SaveGroup(memberIDs,name,desc);
            Object[] metadata = new Object[newRecord.size()];
            for (int x=0; x<metadata.length; x++) {
                metadata[x] = newRecord.get(x);
            }
            groupsViewController.addNewRow(metadata);
            JOptionPane.showMessageDialog(null,"Success","",JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            return;
        }
    }
}
