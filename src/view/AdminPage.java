package view;

import javax.swing.*;
import java.awt.*;

public class AdminPage extends JFrame {
    private JPanel container;
    private JTextField fld_username;
    private JCheckBox cbox_usertype;
    private JButton btn_search;
    private JButton btn_adduser;
    private JLabel lbl_username;
    private JLabel lbl_type;
    private JScrollPane scrl_user;

    public AdminPage() {

        setSize(600,500);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);

        listAllListeners();
    }

    public void listAllListeners(){
        btn_adduser.addActionListener(e -> {

        });

        btn_search.addActionListener(e -> {

        });
    }
}
