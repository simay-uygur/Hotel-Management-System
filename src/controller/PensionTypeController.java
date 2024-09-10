package controller;

import core.Helper;
import dao.PensionTypeDao;
import entity.PensionType;
import entity.Type;

import java.util.ArrayList;

public class PensionTypeController {

    private final PensionTypeDao pensionTypeDao = new PensionTypeDao();

    public ArrayList<PensionType> findAll(){
        return this.pensionTypeDao.findAll();
    }

    public PensionType findById(int id){
        return this.pensionTypeDao.findById(id);
    }

    public boolean deletePensionType(int id){
        if(this.findById(id) == null)
        {
            Helper.showMessage("Customer not found with id " + id);
            return false;
        }
        return this.pensionTypeDao.deletePensionType(id);
    }

    public boolean updatePensionType(PensionType u){
        return this.pensionTypeDao.update(u);
    }

    public boolean deletePensionType(PensionType u){
        return this.pensionTypeDao.deletePensionType(u.getId());
    }

    public boolean savePensionType(PensionType pensionType){
        return this.pensionTypeDao.save(pensionType);
    }

    public  ArrayList<PensionType> filterPensionType(int hotelId, Type type){
        String query = "SELECT * FROM pensionType";

        ArrayList<String> whereList = new ArrayList<>();

        if(hotelId != 0) {
            whereList.add("hotel_id = " + hotelId);
        }

        if( type != null && !type.toString().isEmpty()){
            whereList.add("pension_type LIKE '%"+type.toString()+"%'");
        }

        if(!whereList.isEmpty()){
            query += " WHERE " + String.join(" AND ", whereList);
        }

        return this.pensionTypeDao.query(query);
    }
}
