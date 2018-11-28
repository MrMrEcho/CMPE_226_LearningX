package com.learnx.demo.dao;

import com.learnx.demo.model.Login;
import com.learnx.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
//@Component("userDAO")
public class UserDAO {

    @Autowired
    DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public User save(User user) {
        if(user == null) return null;

        String sql = "INSERT INTO AppUser (User_name, User_password, User_type, Email) " +
                //"VALUES (?, ?, ?, ?)";
                "VALUES (:User_name, :User_password, :User_type, :Email)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("User_name", user.getUsername()).
                setParameter("User_password", user.getPassword()).
                setParameter("User_type", user.getType()).
                setParameter("Email", user.getEmail());
//        query.setParameter(1, user.getUsername()).
//                setParameter(2, user.getPassword()).
//                setParameter(3, user.getType()).
//                setParameter(4, user.getEmail());
        int nUpdate = query.executeUpdate();

        if (nUpdate == 1) {
            return user;
        } else {
            return null;
        }

//        String sql = "INSERT INTO AppUserDto (User_name, User_password, User_type, Email) VALUES (?, ?, ?, ?)";
//        Connection conn = null;
//        try {
//            conn = dataSource.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getType());
//            ps.setString(4, user.getEmail());
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//        return user;
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
