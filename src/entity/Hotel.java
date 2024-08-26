package entity;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String area;
    private String fullAddress;
    private String emailAddress;
    private int phoneNumber;
    private int stars;
    private boolean hasFreeParking;
    private boolean hasFreeWifi;
    private boolean hasSwimmingPool;
    private boolean hasFitnessCenter;
    private boolean hasHotelConcierge;
    private boolean hasSpa;
    private boolean hasRoomService;

    public Hotel() {}

    public Hotel(int id, String name, String city, String area, String fullAddress, String emailAddress, int phoneNumber, int stars, boolean hasFreeParking, boolean hasFreeWifi, boolean hasSwimmingPool, boolean hasFitnessCenter, boolean hasHotelConcierge, boolean hasSpa, boolean hasRoomService) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.area = area;
        this.fullAddress = fullAddress;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.stars = stars;
        this.hasFreeParking = hasFreeParking;
        this.hasFreeWifi = hasFreeWifi;
        this.hasSwimmingPool = hasSwimmingPool;
        this.hasFitnessCenter = hasFitnessCenter;
        this.hasHotelConcierge = hasHotelConcierge;
        this.hasSpa = hasSpa;
        this.hasRoomService = hasRoomService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isHasFreeParking() {
        return hasFreeParking;
    }

    public void setHasFreeParking(boolean hasFreeParking) {
        this.hasFreeParking = hasFreeParking;
    }

    public boolean isHasFreeWifi() {
        return hasFreeWifi;
    }

    public void setHasFreeWifi(boolean hasFreeWifi) {
        this.hasFreeWifi = hasFreeWifi;
    }

    public boolean isHasSwimmingPool() {
        return hasSwimmingPool;
    }

    public void setHasSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public boolean isHasFitnessCenter() {
        return hasFitnessCenter;
    }

    public void setHasFitnessCenter(boolean hasFitnessCenter) {
        this.hasFitnessCenter = hasFitnessCenter;
    }

    public boolean isHasHotelConcierge() {
        return hasHotelConcierge;
    }

    public void setHasHotelConcierge(boolean hasHotelConcierge) {
        this.hasHotelConcierge = hasHotelConcierge;
    }

    public boolean isHasSpa() {
        return hasSpa;
    }

    public void setHasSpa(boolean hasSpa) {
        this.hasSpa = hasSpa;
    }

    public boolean isHasRoomService() {
        return hasRoomService;
    }

    public void setHasRoomService(boolean hasRoomService) {
        this.hasRoomService = hasRoomService;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", stars=" + stars +
                ", hasFreeParking=" + hasFreeParking +
                ", hasFreeWifi=" + hasFreeWifi +
                ", hasSwimmingPool=" + hasSwimmingPool +
                ", hasFitnessCenter=" + hasFitnessCenter +
                ", hasHotelConcierge=" + hasHotelConcierge +
                ", hasSpa=" + hasSpa +
                ", hasRoomService=" + hasRoomService +
                '}';
    }
}
