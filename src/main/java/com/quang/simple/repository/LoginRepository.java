package com.quang.simple.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LoginRepository {

    private String url = "mysql://root:hfjFhhABiOBIRgLTGKZbLlegJnXMvaEu@gondola.proxy.rlwy.net:53447/railway";
    private String username = "root";
    private String password = "hfjFhhABiOBIRgLTGKZbLlegJnXMvaEu";

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
    public boolean createUser(String username,String password){
        try(
                Connection conn = DriverManager.getConnection(url, this.username, this.password);
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO users(username,password) VALUES (?, ?)"
                );
        ){

            ps.setString(1, username);
            ps.setString(2, password);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}