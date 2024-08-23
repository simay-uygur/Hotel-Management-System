package controller;

import core.Helper;
import dao.UserDao;
import entity.User;
import java.util.ArrayList;

public class UserController {



    private final UserDao userDao = new UserDao();


    public User findByLogIn(String username, String password)
    {
//        if(!Helper.isEmailValid(mail)) {
//            return null;
//        }   not necessary because i wont check whether it is a mail
        return this.userDao.findByLogin(username,password);
    }

    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }

    public User addUser(String username, String password, String  type){  //not sure whether type should be string
        return userDao.addUser(username, password, type);
    }

    public User findById(int id){
        return this.userDao.findById(id);
    }

    public boolean deleteUser(int id){
        if(this.findById(id) == null)
        {
            Helper.showMessage("Customer not found with id " + id);
            return false;
        }
        return this.userDao.deleteUser(id);
    }
}
