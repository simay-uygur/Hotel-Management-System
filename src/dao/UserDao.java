package dao;

import core.Database;
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

    public User addUser(String username, String password, String usertype){  //check logic
        String query = "INSERT INTO user(username, password, usertype) VALUES (?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username );
            pr.setString(2,password );
            pr.setString(3, usertype );

            ResultSet rs = pr.executeQuery();
            return findByLogin(username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


