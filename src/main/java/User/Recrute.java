/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import static User.SaveJobsDB.jobTitle;
import com.mysql.cj.log.Log;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ryota
 */
public class Recrute {
    
    public static void getLinks(String jobTitle, int n) throws IOException{
        String[] words = jobTitle.split(" ");
        int size = words.length;
        String first_url = "https://www.rekrute.com/offres.html?s=1&p=2&o=1";
        String[] keys = jobTitle.split(" ");
        int sizeKeys = keys.length;
        String secondary = "";
        int i = 0;
        for(String str : keys){
            if(i < sizeKeys -1){
                secondary+=str+"+";
            }
            else{
                secondary+=str;
            }
            i++;
        }     
        
        String base_url = first_url+secondary+"&keyword="+secondary ;
        
         ArrayList<Offre> jobs = new ArrayList<Offre>();
        String s = "123456";
      for (int l = 0; l <= 2; l++) {
                 StringBuilder url1 = new StringBuilder(base_url);
                char b = s.charAt(l);

                url1.setCharAt(38, b);
                int foi = 6/(l+1);
                if(l == 2){
                    foi = 2;
                }
        for (int k = 0; k <foi ; k++) {
                StringBuilder url = new StringBuilder(url1.toString());
                char c = s.charAt(k);

                url.setCharAt(42, c);
                        
                
                final Document document = Jsoup.connect(url.toString()).get();
                Elements scriptElements = document.select("ul.job-list.job-list2 > li");
         

                int sizeEle = scriptElements.size();
                for (int j = 0; j < sizeEle; j++) {
                   try{
                   
                        Elements division = scriptElements.get(j).select("div.section");
                        String title = division.select("h2 > a").text();
                        Elements infos = division.select("div.holder");                  
                        String requirements = infos.get(0).select("span").text();
                        String infoCompany = infos.get(0).select("span").get(1).text();                 
                        String description = infos.get(0).select("span").get(2).text();   
                        String date = infos.get(0).select("em").text();                               
                        String additionalInfo = infos.get(0).select("ul").text();
                        Offre offre = new Offre(title,requirements,infoCompany,description,date,additionalInfo);
                        jobs.add(offre);
                        
                   }catch(Exception e){
                          e.printStackTrace();
                   }
                   
            }
        }
        
        }
        
            
            
            Connection con;
            Statement st;
            
            for(Offre off : jobs){
                System.out.println(off.toString());
                
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "guillaume");
//
//                    String sql = "INSERT INTO `Recrute`(`title`, `requirements`, `companyInfo`,`description`,`date`,`additionalInfo`) "
//                    + "VALUES ('" + off.title + "','" + off.requirements + "','" + off.companyInfo + "','" + off.description + "','" + off.date + "','" + off.additionalInfo + "')";
//                    
//                    st = con.createStatement();
//                    st.execute(sql);
//            
//                } catch (ClassNotFoundException | SQLException ex) {
//                    Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
            
            
            
             
           


//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
//
//        
              FileWriter fw = new FileWriter("recrutesfrontend2.txt");
         

//           
           
             for(Offre of : jobs){
                 System.out.println(of.toString());
                 fw.write(of.toString());
                 fw.write(System.getProperty("line.separator"));
                 
             }
             
             fw.close();
//        
        
        
        
        
        
    }
    
    public static void main(String[] args) throws IOException{
        System.out.println("Bismi Allah");
        
        getLinks("frontend engineer",10);
    }
    
}


