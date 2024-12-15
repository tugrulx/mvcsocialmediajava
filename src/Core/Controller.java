package Core;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Controller
{

    protected static final JFrame mainFrame = new JFrame();

    protected static final JFrame userPart = new JFrame();
    private static final JPanel viewsViewer = new JPanel(new CardLayout());

    private static final JPanel usersViewViewer = new JPanel(new CardLayout());

    private static final Map<String,Component> mainFrameComponents =new HashMap<>();
    private static final Map<String,Component> userPartComponents=new HashMap<>();

    {
        mainFrame.add(viewsViewer);
    }
    {
        userPart.add(usersViewViewer);
    }

    public abstract void run();

    public static final void addView(String viewName,View view) {
        viewsViewer.add((Component)view, viewName);
    }
    public static final void addUserView(String viewName,View view) {
        usersViewViewer.add((Component) view,viewName);
    }

    public static final void loadView(String viewName)
    {
        CardLayout clayout = (CardLayout) viewsViewer.getLayout();
        clayout.show(viewsViewer,viewName);
    }

    public static final void addComponent(String name,Component component)
    {
        mainFrameComponents.put(name,component);
    }

    public static final void removeComponent(String name)
    {
        mainFrameComponents.remove(name);
    }

    public static final Component getComponent(String name)
    {
        return mainFrameComponents.get(name);
    }

}
