package view;

import controller.HotelController;
import core.Helper;
import entity.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeePage extends JFrame{
    private JPanel container;
    private JTabbedPane pane_all;
    private JTable tbl_room;
    private JPanel pnl_room;
    private JPanel pnl_hotel;
    private JPanel pnl_season;
    private JPanel pnl_price;
    private JPanel pnl_reservation;
    private JTable tbl_hotel;
    private JPanel pnl_top_room;
    private JPanel pnl_top_hotel;
    private JTable tbl_season;
    private JPanel pnl_top_season;
    private JLabel lbl_top;
    private JButton btn_exit;
    private JTable tbl_price;
    private JScrollPane scrl_room;
    private JScrollPane scrl_hotel;
    private JScrollPane scrl_season;
    private JPanel pnl_top_price;
    private JScrollPane scrl_price;
    private JPanel pnl_top_reservation;
    private JTable tbl_reservation;
    private JScrollPane scrl_reservation;
    private JButton btn_add_hotel;
    private JButton btn_search_hotel;
    private JButton btn_clear_hotel;
    private JLabel lbl_hotel_name;
    private JButton btn_filter_hotel;
    private HotelController hotelController = new HotelController();
    private DefaultTableModel tbmdl_hotel = new DefaultTableModel();
    private JPopupMenu popup_hotel = new JPopupMenu();

    public EmployeePage() {
        add(container);

        setSize(1500,700);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);


        //hotel processes
        loadButtonListenersHotel();
        loadHotels(null);
        loadPopUpMenuHotel();


        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Example example = new Example();
            }
        });

    }

    public void loadHotels(ArrayList<Hotel> hotels) {

        Object[] column = {
        "id",
        "name",
        "city",
        "area",
        "fullAddress",
        "emailAddress",
        "phoneNumber",
        "stars",
        "hasFreeParking",
        "hasFreeWifi",
        "hasSwimmingPool",
        "hasFitnessCenter",
        "hasHotelConcierge",
        "hasSpa",
        "hasRoomService"
        };

        if(hotels == null  || hotels.isEmpty()) {
            hotels = this.hotelController.findAll();
            System.out.println(hotels);
        }

        DefaultTableModel model = (DefaultTableModel) tbl_hotel.getModel();
        model.setRowCount(0);

        tbmdl_hotel.setColumnIdentifiers(column);


        for (Hotel hotel : hotels) {
            Object[] row = {
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getCity(),
                    hotel.getArea(),
                    hotel.getFullAddress(),
                    hotel.getEmailAddress(),
                    hotel.getPhoneNumber(),
                    hotel.getStars(), //bakalım
                    hotel.isHasFreeParking(),
                    hotel.isHasFreeWifi(),
                    hotel.isHasSwimmingPool(),
                    hotel.isHasFitnessCenter(),
                    hotel.isHasHotelConcierge(),
                    hotel.isHasSpa(),
                    hotel.isHasRoomService()
            };

            tbmdl_hotel.addRow(row);

        }
        this.tbl_hotel.setModel(tbmdl_hotel);
        this.tbl_hotel.getTableHeader().setReorderingAllowed(false);
        this.tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_hotel.setEnabled(false);
    }

    public void loadButtonListenersHotel(){

        //yeni bir ekran açılsın oradan tek tek özellik filtrelesen
        btn_filter_hotel.addActionListener(e -> {
            HotelFilter hotelFilter = new HotelFilter(this);
            //setVisible(true);//may dispose
        });

        btn_clear_hotel.addActionListener(e -> {
            loadHotels(null);
            //delete filters maybe from the
        });

        btn_add_hotel.addActionListener(e -> {
            HotelAddUpdate hotelUi = new HotelAddUpdate(new Hotel());
            hotelUi.setVisible(true);
            hotelUi.addWindowListener(new WindowAdapter() { //disposed
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotels(null);
                    //needs maybe another things
                }
            });
        });
    }

    public void loadPopUpMenuHotel(){

        this.tbl_hotel.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectedRow,selectedRow); // to select the lines şn the table
            }
        });

        this.popup_hotel.add("Update").addActionListener( e -> {
            int selectId = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0).toString()); //mine was (int) tbl_customer.getValueAt(tbl_customer.getSelectedRow(),0);
            System.out.println(selectId);
            Hotel editedHotel = this.hotelController.findById(selectId);
            HotelAddUpdate hotelUi = new HotelAddUpdate(editedHotel);
            hotelUi.addWindowListener(new WindowAdapter() { //disposed
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotels(null);
                    //needs maybe another things
                }
            });

        });

        this.popup_hotel.add("Delete").addActionListener(e ->{
            int selectId = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0).toString());
            if(Helper.confirm("sure"))
            {
                if(this.hotelController.deleteHotel(selectId)){
                    Helper.showMessage("success");
                    loadHotels(null);
                } else{
                    Helper.showMessage("error");
                }
            }
            System.out.println("Delete clicked!");
        } );

        this.tbl_hotel.setComponentPopupMenu(this.popup_hotel) ;
    }
}


