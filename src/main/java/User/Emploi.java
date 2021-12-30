/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

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
public class Emploi {
    public static ArrayList<EmploiJob> getJobs(String secteur, int nbPages) throws IOException{
        
        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();
        String first_url = "https://www.emploi.ma/recherche-jobs-maroc?f%5B0%5D=im_field_offre_metiers%3A31&page=";
        
        for (int i = 1; i <= nbPages; i++) {
            String url = first_url+i;
            try{
                final Document document = Jsoup.connect(url.toString()).get();
                Elements scriptElements = document.select("div.job-description-wrapper");
                
                for(Element element : scriptElements){
                    Element row = element.select("div > div > div > div").get(1);
                    
                    String title = row.select("h5 > a").text();
                    String link ="https://www.emploi.ma" + row.select("h5 > a").attr("href");
                    System.out.println(title);
                    //scrap into each link alone
                    try{
                    Document offre = Jsoup.connect(link).get();
                    Elements header = offre.select("div.block-header");
                    
                    String date = header.select("div.job-ad-publication-date").text().substring(11);
                    
                    Elements infos = offre.select("div.container-page-content");
                    Elements info = infos.select("table.job-ad-criteria > tbody");
                    
                        for(Element ele : info){
                            Elements single = ele.select("tr > td");
                            String contrat = single.get(5).text();
                            String experience = single.get(11).text();
                            String ville = single.get(9).text();
                            String nbPoste = single.get(17).text();
                            
                        }
                        
                    Elements Descriptions =offre.select("div.content.clearfix ");
                    Element Description = Descriptions.select("ul").first();
                        System.out.println(Description);
                        System.out.println("*************************************");
                    
//                    
                    }catch(Exception e){
                        e.printStackTrace();
                        continue;
                    }
//                    
                }
//                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
        

    
            
        }
        return jobs;
    }
    
    
    public static void main(String[] args) throws IOException{
        getJobs("a",1);
    }
}
