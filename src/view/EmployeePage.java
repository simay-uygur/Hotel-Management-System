package view;

import controller.HotelController;
import entity.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel şbş_top;
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
    DefaultTableModel tbmdl_hotel = new DefaultTableModel();

    public EmployeePage() {
        add(container);

        setSize(1000,700);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);


        createButtonListeners();


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
                    hotel.getStars(),
                    hotel.isHasFreeParking(),
                    hotel.isHasFreeWifi(),
                    hotel.isHasSwimmingPool(),
                    hotel.isHasFitnessCenter(),
                    hotel.isHasHotelConcierge(),
                    hotel.isHasSpa(),
                    hotel.isHasRoomService()
            };

            tbmdl_hotel.addRow(row);

            this.tbl_hotel.setModel(tbmdl_hotel);
            this.tbl_hotel.getTableHeader().setReorderingAllowed(false);
            this.tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(15);
            this.tbl_hotel.setEnabled(false);
        }
    }

    public void createButtonListeners(){

        btn_filter_hotel.addActionListener(new ActionListener() {   //yeni bir ekran açılsın oradan tek tek özellik filtrelesen
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btn_clear_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btn_add_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
