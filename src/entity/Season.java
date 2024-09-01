package entity;

import java.sql.Date;

public class Season {
    private int id;
    private Date startDate;
    private Date endDate;
    private int hotelId;

    public Season(int id, Date startDate, Date endDate, int hotelId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hotelId = hotelId;
    }

    public Season() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
