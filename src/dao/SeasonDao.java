package dao;

import core.Database;
import core.Helper;
import entity.Season;
import exception.SeasonDateProblemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class SeasonDao {

    private Connection con;

    public SeasonDao() {
        this.con = Database.getInstance();
    }

    public ArrayList<Season> findAll() {

        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM season";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next())
            {
                Season season = this.match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return seasons;
    }

    public Season match(ResultSet rs) throws SQLException {
        Season season = new Season();

        season.setId(rs.getInt("id"));
        season.setStartDate(rs.getDate("start_date"));
        season.setEndDate(rs.getDate("end_date"));
        season.setHotelId(rs.getInt("hotel_id"));

        return season;
    }

    public boolean save(Season season) throws SeasonDateProblemException {  //check logic

        ArrayList<Season> seasons = this.findAllSeasonsByHotelId(season.getHotelId());

        boolean found = seasons.stream().anyMatch(season1 ->
                isDateOnTheScope(season.getStartDate(), season.getEndDate(), season1.getStartDate(), season1.getEndDate())
        );

        if (found) {
            throw new SeasonDateProblemException("This season coincides with another season please select another date.");
        }

        String query = "INSERT INTO season(" +
                "start_date," +
                "end_date," +
                "hotel_id" +
                ") VALUES (?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);

            pr.setDate(1, (season.getStartDate()));
            pr.setDate(2, (season.getEndDate()));
            pr.setInt(3, season.getHotelId());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Season findById(int id){
        Season season = null;
        String query = "SELECT * FROM season WHERE id = ?" ;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                season = this.match(rs);
            }
            return season;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id){
        String query = "DELETE FROM season WHERE id = ?" ;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Season season) {
        String query = "UPDATE hotel SET " +
                "start_date=?," +
                "end_date=?," +
                "hotel_id=?"  +
                "WHERE id = ? " ;
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setDate(1, season.getStartDate());
            pr.setDate(1, season.getEndDate());
            pr.setInt(3, season.getHotelId());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //filter şimdilik düşünmüyorum
    public ArrayList<Season> query(String query) {
        ArrayList<Season> seasons = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                seasons.add(this.match(rs));
            }
        } catch (SQLException e) {
            Helper.showMessage("This could not found for filtering");
        }
        return seasons;
    }

    public ArrayList<Season> findAllSeasonsByHotelId(int hotelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        PreparedStatement pr;
        try {
            pr = this.con.prepareStatement(query);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                seasons.add(this.match(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return seasons;
    }

    private boolean isDateOnTheScope(Date myDateStart, Date myDateEnd, Date compareDateStart, Date compareDateEnd){
        //possibility1
        if(myDateStart.after(compareDateStart) && myDateEnd.before(compareDateEnd)){
            return true;
        } else if(myDateEnd.after(compareDateStart) || myDateStart.before(compareDateEnd)){
            return true;
        } else return myDateStart.before(compareDateStart) && myDateEnd.after(compareDateEnd);  //if those 4 dont occur it is available
    }
}




