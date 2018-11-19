package com.learnx.demo.dao;

import com.learnx.demo.model.Login;
import com.learnx.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component("userDAO")
public class UserDAO {

    @Autowired
    DataSource dataSource;

    public User save(User user) {
        if(user == null) return null;
        String sql = "INSERT INTO AppUser (User_name, User_password, User_type, Email) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getType());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return user;
    }

    public User validate(Login login) {
        if(login == null || login.getUsername() == null || login.getPassword() == null) return null;
        String sql = "select * from AppUser where User_name = ? and User_password = ?";
        Connection conn = null;
        User user = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setUsername(rs.getString("User_name"));
                user.setPassword(rs.getString("User_password"));
                user.setEmail(rs.getString("Email"));
                user.setType(rs.getString("User_type"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return user;
    }

}
