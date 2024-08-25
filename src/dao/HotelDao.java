package dao;

import core.Database;
import core.Helper;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private Connection con;

    public HotelDao() {
        this.con = Database.getInstance();
    }

    public ArrayList<Hotel> findAll() {

        ArrayList<Hotel> users = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next())
            {
                Hotel hotel = this.match(rs);
                users.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel();

        hotel.setId(rs.getInt("id"));
        hotel.setName(rs.getString("name"));
        hotel.setCity(rs.getString("city"));
        hotel.setArea(rs.getString("area"));
        hotel.setFullAddress(rs.getString("full_address"));
        hotel.setEmailAddress(rs.getString("email_address"));
        hotel.setPhoneNumber(rs.getInt("phone_number"));
        hotel.setId(rs.getInt("stars"));
        hotel.setHasFreeParking(rs.getBoolean("has_free_parking"));
        hotel.setHasFreeWifi(rs.getBoolean("has_free_wifi"));
        hotel.setHasSwimmingPool(rs.getBoolean("has_swimming_pool"));
        hotel.setHasFitnessCenter(rs.getBoolean("has_fitness_center"));
        hotel.setHasHotelConcierge(rs.getBoolean("has_hotel_concierge"));
        hotel.setHasSpa(rs.getBoolean("has_spa"));
        hotel.setHasRoomService(rs.getBoolean("has_room_service"));

        return hotel;
    }

    public boolean save(Hotel hotel){  //check logic
        String query = "INSERT INTO hotel(" +
                "id, " +
                "name, " +
                "city, " +
                "area, " +
                "full_address" +
                "email_address" +
                "phone_number" +
                "stars" +
                "has_free_parking" +
                "has_free_wifi" +
                "has_swimming_pool" +
                "has_fitness_center" +
                "has_hotel_concierge" +
                "has_spa" +
                "has_room_service" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, hotel.getId());
            pr.setString(2, hotel.getName());
            pr.setString(3, hotel.getCity());
            pr.setString(4, hotel.getArea());
            pr.setString(5, hotel.getFullAddress());
            pr.setString(6, hotel.getEmailAddress());
            pr.setInt(7, hotel.getPhoneNumber());
            pr.setInt(8, hotel.getStars());
            pr.setBoolean(9, hotel.isHasFreeParking());
            pr.setBoolean(10, hotel.isHasFreeWifi());
            pr.setBoolean(11, hotel.isHasSwimmingPool());
            pr.setBoolean(12, hotel.isHasFitnessCenter());
            pr.setBoolean(13, hotel.isHasHotelConcierge());
            pr.setBoolean(14, hotel.isHasSpa());
            pr.setBoolean(15, hotel.isHasRoomService());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Hotel findById(int id){
        Hotel hotel = null;
        String query = "SELECT * FROM hotel WHERE id = ?" ;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                hotel = this.match(rs);
            }
            return hotel;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteHotel(int id){
        String query = "DELETE FROM hotel WHERE id = " + id;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Hotel hotel) {
        String query = "UPDATE hotel SET " +
                "name=?, " +
                "city=?, " +
                "area=?, " +
                "full_address=?," +
                "email_address=?," +
                "phone_number=?," +
                "stars=?," +
                "has_free_parking=?," +
                "has_free_wifi=?," +
                "has_swimming_pool=?," +
                "has_fitness_center=?," +
                "has_hotel_concierge=?," +
                "has_spa=?," +
                "has_room_service=? " +
                "WHERE id = ? " ;
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getCity());
            pr.setString(3, hotel.getArea());
            pr.setString(4, hotel.getFullAddress());
            pr.setString(5, hotel.getEmailAddress());
            pr.setInt(6, hotel.getPhoneNumber());
            pr.setInt(7, hotel.getStars());
            pr.setBoolean(8, hotel.isHasFreeParking());
            pr.setBoolean(9, hotel.isHasFreeWifi());
            pr.setBoolean(10, hotel.isHasSwimmingPool());
            pr.setBoolean(11, hotel.isHasFitnessCenter());
            pr.setBoolean(12, hotel.isHasHotelConcierge());
            pr.setBoolean(13, hotel.isHasSpa());
            pr.setBoolean(14, hotel.isHasRoomService());
            pr.setInt(15, hotel.getId());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Hotel> query(String query) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                hotels.add(this.match(rs));
            }
        } catch (SQLException e) {
            Helper.showMessage("This could not found for filtering");
        }
        return hotels;
    }

}




