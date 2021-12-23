/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import com.mysql.cj.log.Log;
import java.io.IOException;
import java.util.ArrayList;
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
        
        //System.out.println(base_url);
        //for loop
        
        final Document document = Jsoup.connect(base_url).get();
        //System.out.println(document);
        //Elements scriptElements = document.select("div.slide-block");
         Elements scriptElements = document.select("ul.job-list.job-list2 > li");
         
        ArrayList<Offre> jobs = new ArrayList<Offre>();

         //System.out.println(scriptElements);
            int sizeEle = scriptElements.size();
            for (int j = 0; j < sizeEle; j++) {
                   //System.out.println(scriptElements.get(0).select("div.section").text());
                   try{
                   
                   Elements division = scriptElements.get(j).select("div.section");
                   String title = division.select("h2 > a").text();
//                   System.out.println(title);
                   Elements infos = division.select("div.holder");
                   
                   String requirements = infos.get(0).select("span").text();
                   //System.out.println(requirements);
                   String infoCompany = infos.get(0).select("span").get(1).text();
                   
                   String description = infos.get(0).select("span").get(2).text();
                   
                   //System.out.println(description);
                   
                   String date = infos.get(0).select("em").text();
                   //System.out.println(date);
                   
                   //additional infos 
                   
                   String additionalInfo = infos.get(0).select("ul").text();
                   
                   //System.out.println(additionalInfo);
                   
                   Offre offre = new Offre(title,requirements,infoCompany,description,date,additionalInfo);
                   jobs.add(offre);
                   
                   
                   
                   }catch(Exception e){
                       e.printStackTrace();
                   }
                   
                   //System.out.println(infoCompany);
            }
            
            
            //print Jobs 
            
            for(Offre off : jobs){
                System.out.println(off.toString());
            }

        
        
        
        
        
        
        
    }
    
    public static void main(String[] args) throws IOException{
        System.out.println("Bismi Allah");
        
        getLinks("frontend engineer",10);
    }
    
}


