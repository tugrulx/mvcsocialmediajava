    package View.UserInterface;

    import Controller.UserInterface.SearchViewController;
    import Core.Model;
    import Core.View;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Vector;

    public class SearchView extends JPanel implements View
    {
        public static final int USERNAME_FIELD = 1;
        public static final int NAME_FIELD = 0;
        public static final int PRIVATE_FIELD = 2;
        private ArrayList<String> allUsers; // buraya gelen arrayın formatı username,realname şeklinde yani arraydaki her bir
        private SearchViewController controller;    // eleman böyle olacak.
        private JTextField search_text;
        private JList<String> results_list;
        @Override
        public void update(Model model, Object data) {
            if (data != null) {
                String notice = (String) data;
                JOptionPane.showMessageDialog(null, notice);
            }
        }
        public SearchView(SearchViewController controller) {
            this.controller=controller;
            allUsers = controller.GetAllUsers();
            MakeFrame();
            MakeScrollPane();
            MakeList();

            MakeSearchText();
        }

        public void MakeFrame() {
            setLayout(null);
        }

        public void MakeSearchText()
        {
            JLabel desc = new JLabel("Search by username");
            desc.setBounds(100,40,250,30);
            desc.setFont(new Font("Tahoma",Font.BOLD,24));
            add(desc);
            search_text = new JTextField();
            search_text.setBounds(120,90,200,20);
            add(search_text);
            search_text.setColumns(10);

            search_text.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    String text = search_text.getText().toLowerCase().trim();
                    if (results_list==null || allUsers==null) {
                        results_list.setListData(new Vector<>());
                    }
                    if (!text.isEmpty()) {
                        Vector<String> search_results = new Vector<>();
                        for (String user : allUsers) {
                            String username = user.split(",")[USERNAME_FIELD];
                            if (username.contains(text) && user.split(",")[PRIVATE_FIELD].equals("public"))
                            {
                                String result = username + "("+ user.split(",")[NAME_FIELD]+")";
                                search_results.add(result);
                            }
                        }
                        DefaultListModel model = new DefaultListModel();
                        for (String s : search_results) {
                            model.addElement(s);
                        }
                        results_list.setModel(model);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }

        public void MakeList() {
            DefaultListModel model = new DefaultListModel();
            model.addElement("No search result");
            results_list = new JList(model);
            results_list.setBounds(200,200,300,300);
            add(results_list);
        }
        public void MakeScrollPane() {
            JScrollPane pane = new JScrollPane(results_list);
            add(pane);
        }

    }
