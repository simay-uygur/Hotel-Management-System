package view;

import controller.HotelController;
import core.Helper;
import entity.Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HotelFilter extends JFrame{
    private JTextField fld_hotel_name;
    private JLabel lbl_top;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_area;
    private JTextField fld_hotel_stars;
    private JPanel container;
    private JRadioButton yesRadioButtonFreeParking;
    private JRadioButton doesnTMatterRadioButtonFreeParking;
    private JRadioButton yesRadioButtonFreeWifi;
    private JRadioButton doesnTMatterRadioButtonFreeWifi;
    private JRadioButton yesRadioButtonSwimmingPool;
    private JRadioButton doesnTMatterRadioButtonSwimmingPool;
    private JRadioButton yesRadioButtonFitness;
    private JRadioButton doesnTMatterRadioButtonFitness;
    private JRadioButton yesRadioButtonConcierge;
    private JRadioButton doesnTMatterRadioButtonConcierge;
    private JRadioButton yesRadioButtonSpa;
    private JRadioButton doesnTMatterRadioButtonSpa;
    private JRadioButton yesRadioButtonRoomService;
    private JRadioButton doesnTMatterRadioButtonRoomService;
    private JButton btn_done;
    private JButton btn_exit;
    private JScrollPane scrollPaneHotel;
    private HotelController hotelController = new HotelController();



    public HotelFilter(EmployeePage employeePage) {

        scrollPaneHotel = new JScrollPane(container);

        add(scrollPaneHotel);
        setSize(600, 500);
        setTitle("Patikadev");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);

        setAllFields();

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //Helper.setTheme();
            }
        });

        btn_done.addActionListener(e -> {
            if( !fld_hotel_stars.getText().isEmpty() && !Helper.isStarFieldCorrect(fld_hotel_stars.getText())){
                Helper.showMessage("Star must be between 1 and 5");
                return; //is it logical
            }
            int star =0;

            if(!fld_hotel_stars.getText().isEmpty()){
                star = Integer.parseInt(fld_hotel_stars.getText());
            }

             ArrayList<Hotel> hotels = this.hotelController.filterHotel(fld_hotel_name.getText(),
                    fld_hotel_city.getText(),
                    fld_hotel_area.getText(),
                    star,
                    yesRadioButtonFreeParking.isSelected(),
                    yesRadioButtonFreeWifi.isSelected(),
                    yesRadioButtonSwimmingPool.isSelected(),
                    yesRadioButtonFitness.isSelected(),
                    yesRadioButtonConcierge.isSelected(),
                    yesRadioButtonSpa.isSelected(),
                    yesRadioButtonRoomService.isSelected()
                    );
            dispose();
            employeePage.loadHotels(hotels);
        });
    }

    public void setAllFields(){

        doesnTMatterRadioButtonFreeParking.setSelected(true);
        doesnTMatterRadioButtonFreeWifi.setSelected(true);
        doesnTMatterRadioButtonSwimmingPool.setSelected(true);
        doesnTMatterRadioButtonConcierge.setSelected(true);
        doesnTMatterRadioButtonFitness.setSelected(true);
        doesnTMatterRadioButtonSpa.setSelected(true);
        doesnTMatterRadioButtonRoomService.setSelected(true);

        yesRadioButtonFreeParking.setSelected(false);
        yesRadioButtonFreeWifi.setSelected(false);
        yesRadioButtonSwimmingPool.setSelected(false);
        yesRadioButtonFitness.setSelected(false);
        yesRadioButtonConcierge.setSelected(false);
        yesRadioButtonSpa.setSelected(false);
        yesRadioButtonRoomService.setSelected(false);

    }
}
