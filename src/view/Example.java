package view;

import controller.UserController;
import core.Helper;
import entity.User;
import entity.UserType;

import javax.swing.*;
import java.awt.*;

public class Example extends JFrame {
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;

    UserController userController;
    GUIController guiController;

    public Example() {
        userController =  new UserController();
        guiController = new GUIController();

        Helper.setTheme();
        add(container);

        addListener();
        setSize(400,350);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);

    }

    public void addListener(){

        btn_login.addActionListener(e -> {
            String username = fld_username.getText();
            String password = new String(fld_password.getPassword());

            User entered = userController.findByLogIn(username,password);

            if(entered != null){
                System.out.println("you successfully entered;");
                dispose();
                if(entered.getUsertype() == UserType.admin){
                    guiController.loadAdminPage();
                } else {
                    guiController.loadEmployeePage();
                }
            } else {
                guiController.loadNotSuccessfullEntrancePage();
            }
        });

    }
}
