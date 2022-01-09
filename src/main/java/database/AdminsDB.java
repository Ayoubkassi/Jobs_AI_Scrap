/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import model.Admins;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pattern
 */
public class AdminsDB {
    
    private static Connection link;
    private static String url = "jdbc:mysql://localhost:3306/JobsScraper?allowMultiQueries=true";
    private static String user = "root";
    private static String password = "guillaume";
    
    private static void initConnection() throws ClassNotFoundException, SQLException {
        // Define Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Initialize connection
        link = DriverManager.getConnection(url, user, password);
    }

    // retourner toutes les admines de la base
    public static ArrayList<Admins> getAdmins() {

        Connection connect = null;
        Statement statement = null;

        // PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;

        ArrayList<Admins> admins = new ArrayList<Admins>();

        try {
            // Define Driver
            Class.forName("com.mysql.jdbc.Driver");

            // Initialize connection
            connect = DriverManager.getConnection(url, user, password);
            
            link = connect;
            // statements

            statement = connect.createStatement();

            // read from db

            resultSet = statement.executeQuery("select * from admins");

            // to print content
            System.out.println("");
            System.out.println("");
            System.out.println("");
            while (resultSet.next()) {

                String username = resultSet.getString("username");
                String pass = resultSet.getString("password");
                int id = resultSet.getInt("id");

                // save here
                Admins adm = new Admins(id, username, pass);
                admins.add(adm);

                // System.out.println("id : "+id + "username : "+username +" ,password :
                // "+pass);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return admins;

    }
    
    public static boolean updateScrapeRate(String rateCount, String rateContext ) throws Exception {
        initConnection();

        String query = "update `configs` set `config_value`=? where `config_name`='scrape_rate_count';"+
                "update `configs` set `config_value`=? where `config_name`='scrape_rate_context';";
        try (PreparedStatement pstmt = link.prepareStatement(query)) {
            pstmt.setString(1, rateCount);
            pstmt.setString(2, rateContext);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (Exception e) {
            throw e;
        } 
    }
}
