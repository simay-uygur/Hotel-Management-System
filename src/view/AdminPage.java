package view;

import controller.UserController;
import core.Helper;
import entity.User;
import entity.UserType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminPage extends JFrame {
    private JPanel container;
    private JTextField fld_username;
    private JComboBox cbox_usertype;
    private JButton btn_search;
    private JButton btn_adduser;
    private JLabel lbl_username;
    private JLabel lbl_type;
    private JScrollPane scrl_user;
    private JTable tbl_user;
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

        uploadUsers(null);
        listAllListeners();
        loadCustomerPopUpMenu();

    }

    public void listAllListeners(){
        btn_adduser.addActionListener(e -> {

        });

        btn_search.addActionListener(e -> {

        });
    }

    public void uploadUsers(ArrayList<User> users){
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

    private void loadCustomerPopUpMenu(){

        this.tbl_user.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selectedRow,selectedRow); // to select the lines şn the table
            }
        });

        this.popup_user.add("Update").addActionListener( e -> {
            int selectId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString()); //mine was (int) tbl_customer.getValueAt(tbl_customer.getSelectedRow(),0);
            System.out.println(selectId);
            User editedCustomer = this.userController.findById(selectId);
//            CustomerUI customerUI = new CustomerUI(editedCustomer);
//            customerUI.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    loadCustomerTable(null );
//                    loadBasketCustomerCombo();
//                }
//            });

        });

        this.popup_user.add("Delete").addActionListener(e ->{
            int selectId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            if(Helper.confirm("sure"))
            {
                if(this.userController.deleteUser(selectId)){
                    Helper.showMessage("success");
                    uploadUsers(null);
                } else{
                    Helper.showMessage("error");
                }
            }
            System.out.println("Delete clicked!");
        } );

        this.tbl_user.setComponentPopupMenu(this.popup_user) ;


    }

/*
private void loadProductButtonEvent() {
        this.btn_add_product.addActionListener(e -> {
            ProductUI productUI = new ProductUI(new Product());
            productUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadProductTable(null);
                }
            });
        });

        this.btn_search_product.addActionListener(e -> {
            ArrayList<Product> filteredProducts = this.productController.filterProduct(
                    this.fld_customer_name_text.getText(),
                    this.fld_product_code.getText(),
                    (Item)this.cmb_product_stock.getSelectedItem()
            );
            loadProductTable(filteredProducts);
        });

        this.btn_clear_product.addActionListener(e -> {
             this.fld_product_code.setText(null);
             this.fld_product_name.setText(null);
             this.cmb_product_stock.setSelectedItem(null);
             loadProductTable(null);
        });
    }
 */

}
