package entity;

public class Room {

    private int id;
    private int hotelId;
    private int pensionType;
    private int seasonId;
    private int adultPrice;
    private int childPrice;
    private int bedAmount;
    private int roomArea;
    private boolean hasTv;
    private boolean hasMinibar;
    private boolean hasPlaystation;
    private boolean hasSafe;
    private boolean hasProjection;

    public Room() {}

    public Room(int id, int hotelId, int pensionType, int seasonId, int adultPrice, int childPrice, int bedAmount, int roomArea, boolean hasTv, boolean hasMinibar, boolean hasPlaystation, boolean hasSafe, boolean hasProjection) {
        this.id = id;
        this.hotelId = hotelId;
        this.pensionType = pensionType;
        this.seasonId = seasonId;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.bedAmount = bedAmount;
        this.roomArea = roomArea;
        this.hasTv = hasTv;
        this.hasMinibar = hasMinibar;
        this.hasPlaystation = hasPlaystation;
        this.hasSafe = hasSafe;
        this.hasProjection = hasProjection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getPensionType() {
        return pensionType;
    }

    public void setPensionType(int pensionType) {
        this.pensionType = pensionType;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getBedAmount() {
        return bedAmount;
    }

    public void setBedAmount(int bedAmount) {
        this.bedAmount = bedAmount;
    }

    public int getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(int roomArea) {
        this.roomArea = roomArea;
    }

    public boolean isHasTv() {
        return hasTv;
    }

    public void setHasTv(boolean hasTv) {
        this.hasTv = hasTv;
    }

    public boolean isHasMinibar() {
        return hasMinibar;
    }

    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }

    public boolean isHasPlaystation() {
        return hasPlaystation;
    }

    public void setHasPlaystation(boolean hasPlaystation) {
        this.hasPlaystation = hasPlaystation;
    }

    public boolean isHasSafe() {
        return hasSafe;
    }

    public void setHasSafe(boolean hasSafe) {
        this.hasSafe = hasSafe;
    }

    public boolean isHasProjection() {
        return hasProjection;
    }

    public void setHasProjection(boolean hasProjection) {
        this.hasProjection = hasProjection;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", pensionType=" + pensionType +
                ", seasonId=" + seasonId +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice +
                ", bedAmount=" + bedAmount +
                ", roomArea=" + roomArea +
                ", hasTv=" + hasTv +
                ", hasMinibar=" + hasMinibar +
                ", hasPlaystation=" + hasPlaystation +
                ", hasSafe=" + hasSafe +
                ", hasProjection=" + hasProjection +
                '}';
    }
}
