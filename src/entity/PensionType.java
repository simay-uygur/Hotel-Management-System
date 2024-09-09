package entity;

public class PensionType {

    private int id;
    private Type pensionType;
    private int hotelId;

    public PensionType() {
    }

    public PensionType(int id, Type pensionType, int hotelId) {
        this.id = id;
        this.pensionType = pensionType;
        this.hotelId = hotelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getPensionType() {
        return pensionType;
    }

    public void setPensionType(Type pensionType) {
        this.pensionType = pensionType;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
