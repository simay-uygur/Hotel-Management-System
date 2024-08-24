package view;

import controller.UserController;
import core.Helper;
import entity.User;
import entity.UserType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminPage extends JFrame {
    private JPanel container;
    private JTextField fld_username;
    private JComboBox cbox_usertype;
    private JButton btn_search;
    private JButton btn_adduser;
    private JButton btn_clear;
    private JLabel lbl_username;
    private JLabel lbl_type;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JButton btn_exit;

    private JPopupMenu popup_user = new JPopupMenu();
    UserController userController = new UserController();
    private DefaultTableModel tbmdl_user = new DefaultTableModel();

    public AdminPage() {
        add(container);

        setSize(600,500);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);

        this.cbox_usertype.setModel(new DefaultComboBoxModel<>(UserType.values()));
        this.cbox_usertype.setSelectedItem(null);

        loadUserMenu(null);
        listAllButtonListeners();
        loadUserPopUpMenu();

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Example example = new Example();
                //Helper.setTheme();
            }
        });

    }

    public void listAllButtonListeners(){
        btn_adduser.addActionListener(e -> {
            UserUI userUI = new UserUI(new User()); //adding user
            userUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserMenu(null);
                }
            });

        });

        btn_search.addActionListener(e -> {
            ArrayList<User> filteredProducts = this.userController.filterUser(
                    this.fld_username.getText(),
                    (UserType) this.cbox_usertype.getSelectedItem()  //hope no problem occurs
            );
            loadUserMenu(filteredProducts);
        });

        this.btn_clear.addActionListener(e -> {
            this.fld_username.setText(null);
            this.cbox_usertype.setSelectedItem(null);
            loadUserMenu(null);
        });
    }

    public void loadUserMenu(ArrayList<User> users){
        Object[] columnUser = { "id" ,"username", "password", "type"};

        if(users == null  || users.isEmpty()) {
            users = this.userController.findAll();
        }

        DefaultTableModel clearModel = (DefaultTableModel) tbl_user.getModel();
        clearModel.setRowCount(0);

        this.tbmdl_user.setColumnIdentifiers(columnUser);

        for(User user : users) {
            Object[] rowproduct = {
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getUsertype()
            };
            this.tbmdl_user.addRow(rowproduct);
        }

        this.tbl_user.setModel(tbmdl_user);
        this.tbl_user.getTableHeader().setReorderingAllowed(false);
        this.tbl_user.getColumnModel().getColumn(0).setMaxWidth(30);
        this.tbl_user.setEnabled(false);

    }

    private void loadUserPopUpMenu(){

        this.tbl_user.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selectedRow,selectedRow); // to select the lines şn the table
            }
        });

        this.popup_user.add("Update").addActionListener( e -> {
            int selectId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString()); //mine was (int) tbl_customer.getValueAt(tbl_customer.getSelectedRow(),0);
            System.out.println(selectId); //not necessary
            User editedCustomer = this.userController.findById(selectId);
            UserUI userUI = new UserUI(editedCustomer);

            userUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserMenu(null);
                    //if there is another to add, add here
                }
            });

        });

        this.popup_user.add("Delete").addActionListener(e ->{
            int selectId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            if(Helper.confirm("sure"))
            {
                if(this.userController.deleteUser(selectId)){
                    Helper.showMessage("success");
                    loadUserMenu(null);
                } else{
                    Helper.showMessage("error");
                }
            }
            System.out.println("Delete clicked!");
        } );

        this.tbl_user.setComponentPopupMenu(this.popup_user) ;



    }


}
