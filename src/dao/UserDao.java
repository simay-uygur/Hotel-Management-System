package dao;

import core.Database;
import core.Helper;
import entity.User;
import entity.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private Connection con;

    public UserDao() {
        this.con = Database.getInstance();
    }

    public User findByLogin(String username, String password)
    {
        User user = null;
        String query =  "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);

            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                user = this.match(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public ArrayList<User> findAll()
    {

        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next())
            {
                User user = this.match(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public User match(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setPassword(rs.getString("password"));
        user.setUsername(rs.getString("username"));
        user.setUsertype(UserType.valueOf(rs.getString("usertype")));
        return user;
    }

    public boolean save(User user){  //check logic
        String query = "INSERT INTO user(username, password, usertype) VALUES (?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getUsertype().toString());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(int id){
        User user = null;
        String query = "SELECT * FROM user WHERE id = ?" ;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                user = this.match(rs);
            }
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(int id){
        String query = "DELETE FROM user WHERE id = " + id;
        User user = null;

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(User user) {
        String query = "UPDATE user SET " +
                "username = ? ," +
                "password = ? ," +
                "usertype = ? " +
                "WHERE id = ? " ;
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getUsertype().toString());
            pr.setInt(4, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //for filter
    public ArrayList<User> query(String query) {
        ArrayList<User> customers = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                customers.add(this.match(rs));
            }
        } catch (SQLException e) {
            Helper.showMessage("This could not found for filtering");
        }
        return customers;
    }

}


