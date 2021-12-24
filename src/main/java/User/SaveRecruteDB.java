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
import weka.experiment.Compute;

/**
 *
 * @author ryota
 */
public class SaveRecruteDB {
     static ArrayList<Job> jobs = new ArrayList<Job>();
    
    static String jobTitle = "Front End";
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
          Connection con;
            Statement st;
            

        try (BufferedReader br = new BufferedReader(new FileReader("recrutesfrontend2.txt"))) {
            String line;
            int i =0;
            
            while ((line = br.readLine()) != null) {
                    try{
                  String title1 = line.split(",")[1];
                  String title = line.substring(18,title1.length()+11);

                  //System.out.println(title);
                  
                  String requirements = line.split(",")[2].substring(14);
                  
                  int companyindex = line.indexOf(", companyInfo");
                  int descriptionindex = line.indexOf(", description");
                  int addinfoindex = line.indexOf(", additionalInfo");
                  int dateIndex = line.indexOf(", date=");
                  String company = line.substring(companyindex+14,descriptionindex);

                  String description = line.substring(descriptionindex+14,dateIndex);
                  String date =  line.substring(dateIndex+20,addinfoindex);
                  
                  String addInfos1 = line.substring(addinfoindex+17);
                  String addInfo = addInfos1.substring(0, addInfos1.length()-1);
                  
                  try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "guillaume");
            
          String sql = "INSERT INTO `Recrute`(`title`, `requirements`, `companyInfo`) "
                    + "VALUES ('" + title + "','" + requirements + "','" + company + "')";
            st = con.createStatement();
            st.execute(sql);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
//           


                    }catch(Exception e){
                        e.printStackTrace();
                    }
            }
        }
    }
}

