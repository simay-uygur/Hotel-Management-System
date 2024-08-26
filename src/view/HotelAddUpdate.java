package view;

import controller.HotelController;
import core.Helper;
import entity.Hotel;
import entity.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelAddUpdate extends JFrame {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_area;
    private JTextField fld_hotel_full_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_stars;
    private JRadioButton rd_btn_yes_parking;
    private JRadioButton rd_btn_no_parking;
    private JRadioButton rd_btn_yes_wifi;
    private JRadioButton rd_btn_no_wifi;
    private JRadioButton rd_btn_yes_pool;
    private JRadioButton rd_btn_no_pool;
    private JRadioButton rd_btn_yes_fitness;
    private JRadioButton rd_btn_no_fitness;
    private JRadioButton rd_btn_yes_concierge;
    private JRadioButton rd_btn_yes_spa;
    private JRadioButton rd_btn_yes_roomservice;
    private JRadioButton rd_btn_no_roomservice;
    private JRadioButton rd_btn_no_concierge;
    private JRadioButton rd_btn_no_spa;
    private JButton btn_done;
    private JButton btn_exit;
    private JLabel lbl_top;
    private Hotel hotel;
    private HotelController hotelController = new HotelController();
    JScrollPane scrollPaneHotel;

    public HotelAddUpdate(Hotel hotel) {
        scrollPaneHotel = new JScrollPane(container);


        setTitle("Hotel Add Update");
        add(scrollPaneHotel);
        setSize(600, 500);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);


        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Helper.showMessage("Process exited without saving data");
            }
        });

        this.hotel = hotel;

        if(this.hotel.getId() == 0){
            this.lbl_top.setText("Add Hotel");
        } else {
            this.lbl_top.setText("Edit Hotel");
            this.fld_hotel_name.setText(hotel.getName());
            this.fld_hotel_city.setText(hotel.getCity());
            this.fld_hotel_area.setText(hotel.getArea());
            this.fld_hotel_full_address.setText(hotel.getFullAddress());
            this.fld_hotel_email.setText(hotel.getEmailAddress());
            this.fld_hotel_phone.setText(String.valueOf(hotel.getPhoneNumber()));
            this.fld_hotel_stars.setText(String.valueOf(hotel.getStars()));

            if(hotel.isHasFreeParking()){
                rd_btn_yes_parking.setSelected(true);
                rd_btn_no_parking.setSelected(false);
            } else {
                rd_btn_yes_parking.setSelected(false);
                rd_btn_no_parking.setSelected(true);
            }

            if(hotel.isHasFreeWifi()){
                rd_btn_yes_wifi.setSelected(true);
                rd_btn_no_wifi.setSelected(false);
            } else {
                rd_btn_yes_wifi.setSelected(false);
                rd_btn_no_wifi.setSelected(true);
            }

            if(hotel.isHasSwimmingPool()){
                rd_btn_yes_pool.setSelected(true);
                rd_btn_no_pool.setSelected(false);
            } else {
                rd_btn_yes_pool.setSelected(false);
                rd_btn_no_pool.setSelected(true);
            }

            if(hotel.isHasFitnessCenter()){
                rd_btn_yes_fitness.setSelected(true);
                rd_btn_no_fitness.setSelected(false);
            } else {
                rd_btn_yes_fitness.setSelected(false);
                rd_btn_no_fitness.setSelected(true);
            }

            if(hotel.isHasHotelConcierge()){
                rd_btn_yes_concierge.setSelected(true);
                rd_btn_no_concierge.setSelected(false);
            } else {
                rd_btn_yes_concierge.setSelected(false);
                rd_btn_no_concierge.setSelected(true);
            }

            if(hotel.isHasSpa()){
                rd_btn_yes_spa.setSelected(true);
                rd_btn_no_spa.setSelected(false);
            } else{
                rd_btn_yes_spa.setSelected(false);
                rd_btn_no_spa.setSelected(true);
            }

            if(hotel.isHasRoomService()){
                rd_btn_yes_roomservice.setSelected(true);
                rd_btn_no_roomservice.setSelected(false);
            } else {
                rd_btn_yes_roomservice.setSelected(false);
                rd_btn_no_roomservice.setSelected(true);
            }
            //hope this works

        }

        this.btn_done.addActionListener(e ->
        {
            JTextField[] checkList = {this.fld_hotel_name, this.fld_hotel_city, this.fld_hotel_area, this.fld_hotel_full_address, this.fld_hotel_phone, this.fld_hotel_email, this.fld_hotel_stars };

            if(Helper.isFieldListEmpty(checkList) ) {
                Helper.showMessage("fill");
            } else if (!Helper.isStarFieldCorrect(this.fld_hotel_stars.getText())){
                Helper.showMessage("Stars should be between 1 and 5");
            } else {
                boolean result = false;
                this.hotel.setName(this.fld_hotel_name.getText());
                this.hotel.setCity(this.fld_hotel_city.getText());
                this.hotel.setArea(this.fld_hotel_area.getText());
                this.hotel.setFullAddress(this.fld_hotel_full_address.getText());
                this.hotel.setEmailAddress(this.fld_hotel_email.getText());
                this.hotel.setPhoneNumber(Integer.parseInt(this.fld_hotel_phone.getText()));
                this.hotel.setStars(Integer.parseInt(this.fld_hotel_stars.getText()));
                this.hotel.setHasFreeParking(this.rd_btn_yes_parking.isSelected());
                this.hotel.setHasFreeWifi(this.rd_btn_yes_wifi.isSelected());
                this.hotel.setHasSwimmingPool(this.rd_btn_yes_pool.isSelected());
                this.hotel.setHasFitnessCenter(this.rd_btn_yes_fitness.isSelected());
                this.hotel.setHasHotelConcierge(this.rd_btn_yes_concierge.isSelected());
                this.hotel.setHasSpa(this.rd_btn_yes_spa.isSelected());
                this.hotel.setHasRoomService(this.rd_btn_yes_roomservice.isSelected());

                if(this.hotel.getId() == 0){
                    result = this.hotelController.saveHotel(hotel);

                } else {
                    result = this.hotelController.updateHotel(hotel);
                }

                if(result)
                {
                    Helper.showMessage("done");
                    dispose();
                    //employeepage
                } else {
                    Helper.showMessage("error");
                }

            }
        });

    }


}
