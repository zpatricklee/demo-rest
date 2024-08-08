package com.plee.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {
    List<Alien> aliens;

    Connection con = null;

    public AlienRepository() {
        String url = "jdbc:mysql://localhost:3306/restdb";
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if(username == null || password == null) {
            throw new IllegalArgumentException("Database credentials are not set.");
        }

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<>();

        String sql = "select * from alien";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));

                aliens.add(a);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return aliens;
    }

    public Alien getAlien(int id) {
        String sql = "select * from alien where id=" + id;
        Alien a = new Alien();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return a;
    }

    public void createAlien(Alien a1) {
        String sql = "insert into alien values (?,?,?);";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, a1.getId());
            st.setString(2, a1.getName());
            st.setInt(3, a1.getPoints());
            st.executeUpdate();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
