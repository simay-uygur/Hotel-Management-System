package dao;

import core.Database;
import core.Helper;
import entity.Hotel;
import entity.PensionType;
import entity.Type;
import entity.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionTypeDao {

    private Connection con;

    public PensionTypeDao() {
        this.con = Database.getInstance();
    }

    public ArrayList<PensionType> findAll() {

        ArrayList<PensionType> pensionTypes = new ArrayList<>();
        String query = "SELECT * FROM pension_type";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next())
            {
                PensionType pensionType = this.match(rs);
                pensionTypes.add(pensionType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pensionTypes;
    }

    public PensionType match(ResultSet rs) throws SQLException {
        PensionType pensionType = new PensionType();
        pensionType.setId(rs.getInt("id"));
        pensionType.setPensionType(Type.valueOf(rs.getString("pension_type")));
        pensionType.setHotelId(rs.getInt("hotel_id"));

        return pensionType;
    }

    public boolean save(PensionType pensionType){  //check logic
        String query = "INSERT INTO pension_type(" +
                "name, " +
                "city" +
                ") VALUES (?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);

            pr.setString(1, pensionType.getPensionType().toString());
            pr.setInt(2, pensionType.getHotelId());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PensionType findById(int id){
        PensionType pensionType = null;
        String query = "SELECT * FROM pensionType WHERE id = ?" ;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                pensionType = this.match(rs);
            }
            return pensionType;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePensionType(int id){
        String query = "DELETE FROM pension_type WHERE id = " + id;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(PensionType pensionType) {
        String query = "UPDATE pensionType SET " +
                "pension_type=?, " +
                "hotel_id=? " +
                "WHERE id = ? " ;
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, pensionType.getPensionType().toString());
            pr.setInt(2, pensionType.getHotelId());
            pr.setInt(3, pensionType.getId());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<PensionType> query(String query) {
        ArrayList<PensionType> pensionTypes = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                pensionTypes.add(this.match(rs));
            }
        } catch (SQLException e) {
            Helper.showMessage("This could not found for filtering");
        }
        return pensionTypes;
    }

}
