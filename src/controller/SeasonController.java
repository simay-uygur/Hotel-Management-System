package controller;

import core.Helper;
import dao.SeasonDao;
import entity.Season;
import exception.SeasonDateProblemException;

import java.util.ArrayList;

public class SeasonController {

    private final SeasonDao seasonDao = new SeasonDao();


    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public Season findById(int id) {
        return this.seasonDao.findById(id);
    }

    public boolean deleteSeason(int id) {
        if (this.findById(id) == null) {
            Helper.showMessage("Season not found with id " + id);
            return false;
        }
        return this.seasonDao.delete(id);
    }

    public boolean updateSeason(Season u) {
        return this.seasonDao.update(u);
    }

    public boolean saveSeason(Season season) throws SeasonDateProblemException {
        return this.seasonDao.save(season);
    }

}
