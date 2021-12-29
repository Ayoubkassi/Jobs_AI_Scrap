/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ryota
 */
public class AdminsDB {
     public static ArrayList<Admins> getAdmins(){
        
        Connection connect = null;
        Statement statement = null;
       // PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://localhost:3306/JobsScraper";
        String user = "root";
        String password = "guillaume";
        
        ArrayList<Admins> admins = new ArrayList<Admins>();
        
        try{
            //Define Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Initialize connection
            connect = DriverManager.getConnection(url,user,password);
            //statements
            
            statement = connect.createStatement();
            
            //read from db
            
            resultSet = statement.executeQuery("select * from admins");
            
            //to print content
             System.out.println("");
             System.out.println("");
             System.out.println("");
            while(resultSet.next()){
                
                String username = resultSet.getString("username");
                String pass = resultSet.getString("password");
                int id = resultSet.getInt("id");
                
                //save here
                Admins adm = new Admins(id,username,pass);
                admins.add(adm);
               
               // System.out.println("id : "+id + "username : "+username +"   ,password : "+pass);

            }
            
            
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
          return admins;

        
    }
}


//CREATE TABLE admins(
//    id INT(11) PRIMARY KEY AUTO_INCREMENT,
//    username VARCHAR(100),
//    password VARCHAR(100)
//);


//CREATE TABLE user(
//    id INT(11) PRIMARY KEY AUTO_INCREMENT,
//    username VARCHAR(100),
//    password VARCHAR(100),
//    email VARCHAR(100),
//    secteur VARCHAR(100)
//);