/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import static User.SaveJobsDB.jobTitle;
import com.mysql.cj.log.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
    
    public static ArrayList<Offre> getJobs(String jobTitle, int n) throws IOException{
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
        String s = "1234567891";
      for (int l = 0; l <= 2; l++) {
                 StringBuilder url1 = new StringBuilder(base_url);
                char b = s.charAt(l);

                url1.setCharAt(38, b);
                //int foi = 9/(l+1);
                int foi = 10;
                if(l == 2){
                    foi = 2;
                }
                else if (l == 1){
                    foi = 4;
                }
        for (int k = 0; k <=foi ; k++) {
             StringBuilder url = new StringBuilder(url1.toString());

            if(k == foi){
                url.setCharAt(42,'1');
                //url.setCharAt(43,'0');
                url.append('0');
            }else{
                System.out.println(k);
                char c = s.charAt(k);

                url.setCharAt(42, c);
            }
                        
                
                final Document document = Jsoup.connect(url.toString()).get();
                Elements scriptElements = document.select("ul.job-list.job-list2 > li");
         

                int sizeEle = scriptElements.size();
                for (int j = 0; j < sizeEle; j++) {
                   try{
                   
                        Elements division = scriptElements.get(j).select("div.section");
                        String title = division.select("h2 > a").text();
                        String link1 = division.select("h2 > a").first().attr("href");
                        String link = "https://www.rekrute.com"+link1;
                        Elements infos = division.select("div.holder");                  
                        String requirements = infos.get(0).select("span").text();
                        String infoCompany = infos.get(0).select("span").get(1).text();                 
                        String description = infos.get(0).select("span").get(2).text(); 
                        
                        String date = infos.get(0).select("em").text();                               
                        String additionalInfo = infos.get(0).select("ul").text();
                        Offre offre = new Offre(title,requirements,infoCompany,description,date,additionalInfo,link);
                        jobs.add(offre);
                        
                   }catch(Exception e){
                          e.printStackTrace();
                   }
                   
            }
        }
        
        }
        
            
           
            
            


//        
//              FileWriter fw = new FileWriter("recrutesfrontend2.txt");
//         
//
////           
//           
//             for(Offre of : jobs){
//                 System.out.println(of.toString());
//                 fw.write(of.toString());
//                 fw.write(System.getProperty("line.separator"));
//                 
//             }
//             
//             fw.close();
//        

        return jobs;
        
        
        
        
        
    }
    
    
    public static void singleJob() throws IOException{
        
         ArrayList<Offre> jobs= new ArrayList<Offre>();
         ArrayList<Offre> skills= new ArrayList<Offre>();

        
         jobs = getJobs("developpeur",10);
         
         HashMap<Integer,ArrayList<Integer>> preProc = new HashMap<Integer,ArrayList<Integer>>();

         int count = 0;
         for(Offre job : jobs){
             //if(count < 80){
                 try{
            final Document document = Jsoup.connect(job.link).get();
                 System.out.println(count);
            
            String g1 = document.select(".contentbloc > div").get(3).children().text();
            String g2 = document.select(".contentbloc > div").get(4).children().text();

            StringBuilder poste = new StringBuilder(g1);
            StringBuilder requirements = new StringBuilder(g2);
            
             String[] technologies ={"react","angular","vuejs","html","css","javascript","python","sql","java","node","typescript","c#","bash","shell","c++"
            ,"php","flutter","go","kotlin","rust","ruby","dart","assembly","swift","matlab","mysql","postgresql","sqlite","mongodb","redis","firebase","oracle",
            "aws","docker","heroku","kubernetes","linux","flask","django","asp.net","spring","laravel","tensorflow","react native","keras"};
            System.out.println("*****************************");
            System.out.println(technologies.length);
            
            
            
             ArrayList<Integer> techs = new ArrayList<Integer>();
             for (int i = 0; i <= 45; i++) {
                 
                 if(i == 45){
                     //case accepted -> 6
                     int occurences = Collections.frequency(techs, 1);
                     if(occurences > 5){
                         techs.add(6);
                     }
                     //rejected case -> 7
                     else{
                         techs.add(7);
                     }
                 }
                 else if(g2.toLowerCase().contains(technologies[i]) && i != 45){
                     techs.add(1);
                     preProc.put(count, techs);
                }
                 else{
                     techs.add(0);
                     preProc.put(count, techs);
                 }
             }
             
             count++;
             
             }catch(Exception e){
                    e.printStackTrace();
                    continue;
                 }
            
            
         
//         } else{
//                 break;
//             }
         
            }
             
         
         
         File csvFile = new File("bestData.csv");
         PrintWriter out = new PrintWriter(csvFile);
         

         // on a 2 info les plus important le profil cherche et requirements
         
         //mtn on va chercher si existe des mots cle 
         
    for (Map.Entry<Integer, ArrayList<Integer>> entry : preProc.entrySet()) {
        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        ArrayList<Integer> data = new ArrayList<Integer>();
        data = entry.getValue();
        String state = data.get(45) == 6 ? "ACCEPTED" : "REJECTED" ;
            out.printf("%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %s\n", 
             data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15), data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25), data.get(26), data.get(27), data.get(28), data.get(29), data.get(30), data.get(31), data.get(32), data.get(33), data.get(34), data.get(35), data.get(36), data.get(37), data.get(38), data.get(39), data.get(40), data.get(41), data.get(42), data.get(43), data.get(44),state);

    }
    
     out.close();
         
         
       
         
         
         
    }
    
    public static void main(String[] args) throws IOException{
        System.out.println("Bismi Allah");
        
        singleJob();
        //singleJob("https://www.rekrute.com/offre-emploi-responsable-administration-du-personnel-et-affaires-juridiques-recrutement-orh-assessment-casablanca-132525.html");
    }
    
}


