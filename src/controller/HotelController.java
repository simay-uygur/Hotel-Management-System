package controller;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelController {

    private final HotelDao hotelDao = new HotelDao();

    public HotelController() {}

    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }

    public Hotel findById(int id){
        return this.hotelDao.findById(id);
    }

    public boolean deleteHotel(int id){
        if(this.findById(id) == null)
        {
            Helper.showMessage("Customer not found with id " + id);
            return false;
        }
        return this.hotelDao.deleteHotel(id);
    }

    public boolean updateHotel(Hotel hotel){
        return this.hotelDao.update(hotel);
    }

    public boolean deleteHotel(Hotel hotel){
        return this.hotelDao.deleteHotel(hotel.getId());
    }

    public boolean saveHotel(Hotel hotel){
        return this.hotelDao.save(hotel);
    }

    public  ArrayList<Hotel> filterHotel(
            String name,
            String city,
            String area,
            int stars,
            boolean hasFreeParking,
            boolean hasFreeWifi,
            boolean hasSwimmingPool,
            boolean hasFitnessCenter,
            boolean hasHotelConcierge,
            boolean hasSpa,
            boolean hasRoomService){
        String query = "SELECT * FROM hotel";

        ArrayList<String> whereList = new ArrayList<>();

        if(!name.isEmpty()){
            whereList.add("name LIKE '%"+name+"%'");
        }

        if(!city.isEmpty()){
            whereList.add("city LIKE '%"+city+"%'");
        }

        if(!area.isEmpty()){
            whereList.add("area LIKE '%"+area+"%'");
        }

        if(stars > 0){
            whereList.add("stars >= " +stars);
        }

        if(hasFreeParking){
            whereList.add("hasFreeParking = 1");
        }

        if(hasFreeWifi){
            whereList.add("hasFreeWifi = 1");
        }

        if(hasSwimmingPool){
            whereList.add("hasSwimmingPool = 1");
        }

        if(hasFitnessCenter){
            whereList.add("hasFitnessCenter = 1");
        }

        if(hasHotelConcierge){
            whereList.add("hasHotelConcierge = 1");
        }

        if(hasSpa){
            whereList.add("hasSpa = 1");
        }

        if(hasRoomService){
            whereList.add("hasRoomService = 1");
        }

        if(!whereList.isEmpty()){
            query += " WHERE " + String.join(" AND ", whereList);
        }
        System.out.println("Executing query: " + query);

        return this.hotelDao.query(query);
    }
}

