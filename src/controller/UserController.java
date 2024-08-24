package controller;

import core.Helper;
import dao.UserDao;
import entity.User;
import entity.UserType;

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

    public boolean updateUser(User u){
        return this.userDao.update(u);
    }

    public boolean deleteUser(User u){
        return this.userDao.deleteUser(u.getId());
    }

    public boolean saveUser(User user){
        return this.userDao.save(user);
    }

    public  ArrayList<User> filterUser(String username, UserType type){
        String query = "SELECT * FROM user";

        ArrayList<String> whereList = new ArrayList<>();

        if(username.length() > 0){
            whereList.add("username LIKE '%"+username+"%'");
        }

        if( type != null && type.toString().length() > 0 ){
            whereList.add("usertype LIKE '%"+type.toString()+"%'");
        }

        if(!whereList.isEmpty()){
            query += " WHERE " + String.join(" AND ", whereList);
        }

        return this.userDao.query(query);
    }
}
