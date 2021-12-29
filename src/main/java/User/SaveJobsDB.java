/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryota
 */
public class SaveJobsDB {
    
    static ArrayList<Job> jobs = new ArrayList<Job>();
    
    static String jobTitle = "Software Enginner";
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
          Connection con;
            Statement st;
            

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            int i =0;
            
            while ((line = br.readLine()) != null) {
                    try{
                  String title1 = line.split(",")[0];
                  String title = line.substring(10,title1.length());
                  
                  int len = line.split(",").length;
                  String company1 = line.split(",")[1];
                  int companyindex = line.indexOf(", company=srcname:");
                  String company = line.substring(companyindex+19,line.indexOf(line.split(",")[len-1])-2);
                  
                  String link1 = line.split(",")[len-1];
                  String link = link1.substring(6,link1.length()-1);
                  System.out.println(link);
                  
                  
                  try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "guillaume");
            
            String sql = "INSERT INTO `Jobs`(`title`, `company`, `link`,`Field`) "
                    + "VALUES ('" + title + "','" + company + "','" + link + "','" + jobTitle + "')";
            st = con.createStatement();
            st.execute(sql);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SaveJobsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
           


                    }catch(Exception e){
                        e.printStackTrace();
                    }
            }
        }
    }
    
}
