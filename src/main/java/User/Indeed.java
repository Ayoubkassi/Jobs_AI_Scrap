/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author ryota
 */

import static User.HandleDB.addJob;
import static User.Recrute.getJobsGen;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Indeed {
    
   public static void singleJob(String job_url) throws IOException{
 
        try{
            
            Document document = Jsoup.connect(job_url).get();
            Element body= document.select("body").get(0);
            Elements  div = body.select("div.jobsearch-JobComponent-description");           
            String job_description = div.text().toLowerCase();

        
            String[] requirements ={"react","angular","vuejs","html","css"};
            ArrayList<String> match_requirements = new ArrayList<String>();
        
            for(String req: requirements){
                if(job_description.contains(req)){
                    match_requirements.add(req);
                }
            }
        
            for(String req: match_requirements){
                System.out.println(req);
            }
        
        

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
    
    
    
    public static ArrayList<EmploiJob> getLinks(String jobTitle, int n, String country , String type , String date) throws IOException{
        String cnt = "";
        String initialDate = date;

        if(country.equals("germany")){
            cnt = "de.";
        }else if(country.equals("morocco")){
            cnt = "ma.";
        }else if (country.equals("france")){
            cnt = "fr.";
        }
        String[] words = jobTitle.split(" ");
        int size = words.length;
        String first_url = "https://"+cnt+"indeed.com/jobs?q=";
        String med_url= "";
        for (int i = 0; i < size; i++) {
            if(i == size-1){
                med_url+=words[i].toLowerCase()+"";
            }
            else{
                med_url+=words[i].toLowerCase()+"+";
            }
        }
        
        String base_url = first_url+med_url ;
        ArrayList<Job> jobs = new ArrayList<Job>();
        ArrayList<EmploiJob> offres = new ArrayList<EmploiJob>();
        
        for (int i = 310; i <= n*10; i+=10){
            final String url = base_url+"&start="+i+"&jt="+type+"&fromage="+date;

            System.out.println(url);
            final Document document = Jsoup.connect(url).get();
            Elements scriptElements = document.getElementsByTag("script");
            System.out.println(document);
            int scr_num=0;
            String data= "";
            
            try{
             for (Element element :scriptElements ){
                 scr_num++;
                 if(scr_num == 25){
            for (DataNode node : element.dataNodes()) {
                data = node.getWholeData(); 
                    }
                 
                 }
      }
             
             
             
             int ext_begin = data.indexOf("zrprclk");
             String ext_data = data.substring(ext_begin+105, ext_begin+121);
                                      
             int begin = data.indexOf("jobmap[0]");
             String jobs_data = data.substring(begin);                          
             String[] list_jobs = jobs_data.split(";");
             // have title , type , date , ville , link 
             int max_jobs = list_jobs.length -1;
             int start = 0;
             for(String job : list_jobs){
                 if(start<max_jobs){
                     try{
                    String company = job.split(",")[5];
                    int end_title = job.indexOf("locid");
                    int start_title = job.indexOf("title");
                    String title = job.substring(start_title+7, end_title-2);
                    String link = "";
                    int start_jk = job.indexOf("{")+5;
                    int end_jk = job.indexOf("efccid")-2;                
                    String jk = job.substring(start_jk,end_jk);
                    String  job_link = "https://indeed.com/viewjob?jk="+jk+"&tk="+ext_data+"&from=serp&vjs=3";
                    int indexVille = job.indexOf(",city:'");
                    int endVille = job.indexOf("',title:");
                    String ville = job.substring(indexVille+7,endVille);
                    System.out.println(job_link);
                         System.out.println(job_link);
                    
                    //Job current_job = new Job(title,company,job_link);                 
                    //jobs.add(current_job);
                    
                    //each single job
                    date = initialDate;
                    try{
                    Document single = Jsoup.connect(job_link).get();
                    Element body= single.select("body").get(0);
                    Elements  div = body.select("div.jobsearch-JobComponent-description");           
                    String job_description = div.text().toLowerCase();
                    int experienceIndex = job_description.indexOf("d’expérience");
                    String experience = "";
                    boolean expexist = job_description.contains("d’expérience");
                    if(expexist){
                    experience = job_description.substring(experienceIndex -13,experienceIndex);
                    }
                    //requirements 
                    
                    ArrayList<Integer> requirements = new ArrayList<Integer>();
                        
                        String[] technologies ={"react","angular","vuejs","html","css","javascript","python","sql","java","node","typescript","c#","bash","shell","c++"
                ,"php","flutter","go","kotlin","rust","ruby","dart","assembly","swift","matlab","mysql","postgresql","sqlite","mongodb","redis","firebase","oracle",
                "aws","docker","heroku","kubernetes","linux","flask","django","asp.net","spring","laravel","tensorflow","react native","keras"};
            
                        for (int k = 0; k < technologies.length; k++) {
                            if(job_description.contains(technologies[k])){
                                requirements.add(1);
                            }
                            else{
                                requirements.add(0);
                            }
                        }
                        
                        String req ="";
                        for(int id : requirements){
                            req+=id+" ";
                        }
                        
                        date = "last" + initialDate + "days";
                        //save Emploi and add Array
                        EmploiJob offre = new EmploiJob(title,type,experience,ville,req,"1",job_link,date);
                        System.out.println(offre.toString());
                        offres.add(offre);
                        offre.affiche();
                        
                    }catch(Exception e){
                        e.printStackTrace();
                        continue;
                    }
                    
                    
                     }catch(Exception ex){
                         ex.printStackTrace();                
                     }

                 }
                 start++;
             }

   
            }catch(Exception ex){
                
            }
            
            
        }
        
            
//save in file        
//            FileWriter fw = new FileWriter("data.txt");
//                    
//           
//            for(Job job : jobs){
//                 System.out.println(job.toString());
//                 fw.write(job.toString());
//                 fw.write(System.getProperty("line.separator"));
//             }
//             
//             fw.close();

            return offres;
    }
    
    
  
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException{
        System.out.println("Bismi Allah");
        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();
        
        //chercher les offres dans tous le monde avec 10 pages , type CDD et date derniers 7 jours
        try{
        jobs = getLinks("software engineer",50,"","fulltime","14");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        for(EmploiJob job : jobs){
            addJob(job,"Informatique","Indeed");
        }
        //after saving into our db we must execute : update Jobs set typeContrat="CDI" where typeContrat="fulltime";
        
        //singleJob("https://fr.indeed.com/voir-emploi?jk=1f985058e4f47e10&tk=1fm2prn1ir8bl800&from=serp&vjs=3");
    }   
}


