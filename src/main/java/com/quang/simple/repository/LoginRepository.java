package com.quang.simple.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LoginRepository {
    @Value("${MYSQL_URL}") private String url;
    @Value("${MYSQL_USERNAME}") private String username;
    @Value("${MYSQL_PASSWORD}") private String password;
    public boolean checkUser(String username, String password){

        try(
                Connection conn = DriverManager.getConnection(url, this.username, this.password);
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT 1 FROM users WHERE username = ? AND password = ?"
                );
        ){

            ps.setString(1, username);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}