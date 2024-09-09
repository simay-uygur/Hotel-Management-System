package entity;

public class Reservation {

    private int id;
    private int roomId;

    public Reservation() {}

    public Reservation(int id, int roomId) {
        this.id = id;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
