package view;

import controller.HotelController;
import controller.SeasonController;
import core.Helper;
import entity.Hotel;
import entity.Season;
import exception.SeasonDateProblemException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date; // Import with alias to avoid confusion

public class EmployeePage extends JFrame {
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
    private JButton btn_season_add;
    private JTextField fld_season_hotel_id;

    private JButton btn_season_add_update;
    private HotelController hotelController = new HotelController();
    private SeasonController seasonController = new SeasonController();
    private DefaultTableModel tbmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tbmdl_season = new DefaultTableModel();
    private JPopupMenu popup_hotel = new JPopupMenu();
    private JPopupMenu popup_season = new JPopupMenu();
    private String state;
    private int selectedseasonId = 0;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private JFormattedTextField fld_formatted_season_start ;
    private JFormattedTextField fld_formatted_season_end ;

    public EmployeePage() {
        this.state = "add";
        add(container);

        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        fld_formatted_season_start.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
        fld_formatted_season_end.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));

        setSize(1000, 700);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);

        dateFormat.setLenient(false);

        //hotel processes
        loadButtonListenersHotel();
        loadHotels(null);
        loadPopUpMenuHotel();

        //season processes
        loadButtonListenersSeason();
        loadSeasons(null);
        loadPopUpMenuSeason();


        btn_exit.addActionListener(e -> {
            dispose();
            Example example = new Example();
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

        if (hotels == null || hotels.isEmpty()) {
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

    public void loadButtonListenersHotel() {

        btn_filter_hotel.addActionListener(e -> {
            HotelFilter hotelFilter = new HotelFilter(this);
        });

        btn_clear_hotel.addActionListener(e -> {
            loadHotels(null);

        });

        btn_add_hotel.addActionListener(e -> {
            HotelAddUpdate hotelUi = new HotelAddUpdate(new Hotel());
            hotelUi.setVisible(true);
            hotelUi.addWindowListener(new WindowAdapter() { //disposed
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotels(null);

                }
            });
        });
    }

    public void loadPopUpMenuHotel() {

        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectedRow, selectedRow); // to select the lines şn the table
            }
        });

        this.popup_hotel.add("Update").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0).toString()); //mine was (int) tbl_customer.getValueAt(tbl_customer.getSelectedRow(),0);
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

        this.popup_hotel.add("Delete").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")) {
                if (this.hotelController.deleteHotel(selectId)) {
                    Helper.showMessage("success");
                    loadHotels(null);
                } else {
                    Helper.showMessage("error");
                }
            }
            System.out.println("Delete clicked!");
        });

        this.tbl_hotel.setComponentPopupMenu(this.popup_hotel);
    }

    public void loadButtonListenersSeason() {

        btn_season_add_update.addActionListener(e -> {

            fld_formatted_season_start.requestFocusInWindow();
            fld_formatted_season_end.requestFocusInWindow();

            String hotelIdText = fld_season_hotel_id.getText().trim();
            String startDateText = fld_formatted_season_start.getText();
            String endDateText = fld_formatted_season_end.getText();

            if (startDateText == null || endDateText == null || hotelIdText.trim().isEmpty() || startDateText.trim().isEmpty() || endDateText.trim().isEmpty()) {
                Helper.showMessage("Please fill all the fields.");
                System.out.println("Debug: hotelIdText = '" + hotelIdText + "'");
                System.out.println("Debug: startDateText = '" + startDateText + "'");
                System.out.println("Debug: endDateText = '" + endDateText + "'");
                return;
            }

            try {
                int id = Integer.parseInt(hotelIdText);
                Hotel hotel = hotelController.findById(id);

                if (hotel == null) {
                    Helper.showMessage("Invalid hotel ID. Please select a valid hotel.");
                    return;
                }

                LocalDate startDateLocal = LocalDate.parse(startDateText, dateFormatter);
                LocalDate endDateLocal = LocalDate.parse(endDateText, dateFormatter);

                Season season = new Season();
                season.setHotelId(id);
                season.setStartDate(Date.valueOf(startDateLocal));
                season.setEndDate(Date.valueOf(endDateLocal));

                if (state.equalsIgnoreCase("update")) {
                    seasonController.updateSeason(season);
                    state = "add";
                    loadSeasons(null);
                } else {
                    try {
                        seasonController.saveSeason(season);
                        loadSeasons(null);
                    } catch (SeasonDateProblemException ex) {
                        Helper.showMessage("Season dates overlap with existing seasons. Please choose different dates.");
                    }
                }
            } catch (NumberFormatException ex) {
                Helper.showMessage("Please enter a valid number for hotel ID.");
            } catch (DateTimeParseException ex) {
                Helper.showMessage("Please enter the dates in the correct format (yyyy-MM-dd).");
            }
        });
    }

    public void loadSeasons(ArrayList<Season> seasons) {
        Object[] column = {
                "id",
                "startDate",
                "endDate",
                "hotelId"
        };

        if (seasons == null || seasons.isEmpty()) {
            seasons = this.seasonController.findAll();
        }

        DefaultTableModel model = (DefaultTableModel) tbl_season.getModel();
        model.setRowCount(0);

        tbmdl_season.setColumnIdentifiers(column);


        for (Season season : seasons) {
            Object[] row = {
                    season.getId(),
                    season.getStartDate(),
                    season.getEndDate(),
                    season.getHotelId()
            };

            tbmdl_season.addRow(row);

        }
        this.tbl_season.setModel(tbmdl_season);
        this.tbl_season.getTableHeader().setReorderingAllowed(false);
        this.tbl_season.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_season.setEnabled(false);
    }

    public void loadPopUpMenuSeason() {

        this.tbl_season.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_season.rowAtPoint(e.getPoint());
                tbl_season.setRowSelectionInterval(selectedRow, selectedRow); // to select the lines şn the table
            }
        });

        this.popup_season.add("Update").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_season.getValueAt(tbl_season.getSelectedRow(), 0).toString()); //mine was (int) tbl_customer.getValueAt(tbl_customer.getSelectedRow(),0);
            Season editedseason = this.seasonController.findById(selectId);
            selectedseasonId = selectId;

            fld_season_hotel_id.setText(String.valueOf(editedseason.getHotelId()));
            fld_formatted_season_start.setValue(editedseason.getStartDate());
            fld_formatted_season_end.setValue(editedseason.getEndDate());

            state = "update";
            loadSeasons(null);
        });

        this.popup_season.add("Delete").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_season.getValueAt(tbl_season.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")) {
                if (this.seasonController.deleteSeason(selectId)) {
                    Helper.showMessage("success");
                    loadSeasons(null);
                } else {
                    Helper.showMessage("error");
                }
            }
            System.out.println("Delete clicked!");
        });

        this.tbl_season.setComponentPopupMenu(this.popup_season);
    }



}


