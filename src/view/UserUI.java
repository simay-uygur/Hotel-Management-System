package view;

import controller.UserController;
import core.Helper;
import entity.User;
import entity.UserType;

import javax.swing.*;
import java.awt.*;

public class UserUI extends JFrame{
    private JLabel lbl_top;
    private JPanel container;
    private JLabel lbl_name;
    private JTextField fld_username;
    private JTextField fld_password;
    private JComboBox cbox_usertype;
    private JButton btn_done;
    private User user;
    private UserController userController;

    public UserUI(User user) {

        this.user = user;
        this.userController = new UserController();

        this.add(container);
        this.setTitle("Add/Edit Customer");
        this.setSize(500,500);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width-this.getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height-this.getSize().height)/2;
        this.setLocation(x,y);
        this.setVisible(true);

        this.cbox_usertype.setModel(new DefaultComboBoxModel<>(UserType.values()));

        if(this.user.getId() == 0){
            this.lbl_top.setText("Add User");
        } else {
            this.lbl_name.setText("Edit User");
            this.fld_username.setText(this.user.getUsername());
            this.fld_password.setText(this.user.getPassword());
            this.cbox_usertype.getModel().setSelectedItem(this.user.getUsertype()) ;
        }

        this.btn_done.addActionListener(e ->
        {
            JTextField[] checkList = {this.fld_username, this.fld_password};
            if(Helper.isFieldListEmpty(checkList)) {
                Helper.showMessage("fill");
            }
            else if(Helper.isFieldEmpty(this.fld_username) || Helper.isFieldEmpty(this.fld_password)) {
                Helper.showMessage("All areas should be filled");
            } else {
                boolean result = false;
                this.user.setUsername(this.fld_username.getText());
                this.user.setPassword(this.fld_password.getText());
                this.user.setUsertype((UserType) this.cbox_usertype.getSelectedItem());

                if(this.user.getId() == 0){
                    result = this.userController.saveUser(user);
                } else {
                    result = this.userController.updateUser(user);
                }

                if(result)
                {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }

            }
        });
    }
}
