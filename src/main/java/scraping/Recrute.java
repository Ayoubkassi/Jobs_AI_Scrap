/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scraping;

import model.EmploiJob;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author pattern
 */
public class Recrute {

    // general format

    public static ArrayList<EmploiJob> getJobsGen(String jobTitle, int n) throws IOException {
        String first_url = "https://www.rekrute.com/offres.html?s=1&p=2&o=1";
        String[] keys = jobTitle.split(" ");
        int sizeKeys = keys.length;
        String secondary = "";
        int i = 0;
        for (String str : keys) {
            if (i < sizeKeys - 1) {
                secondary += str + "+";
            } else {
                secondary += str;
            }
            i++;
        }

        String base_url = first_url + secondary + "&keyword=" + secondary;

        ArrayList<EmploiJob> offres = new ArrayList<EmploiJob>();

        String s = "1234567891";
        for (int l = 0; l <= 2; l++) {
            StringBuilder url1 = new StringBuilder(base_url);
            char b = s.charAt(l);

            url1.setCharAt(38, b);
            // int foi = 9/(l+1);
            int foi = 10;
            if (l == 2) {
                foi = 2;
            } else if (l == 1) {
                foi = 4;
            }
            for (int k = 0; k <= foi; k++) {
                StringBuilder url = new StringBuilder(url1.toString());

                if (k == foi) {
                    url.setCharAt(42, '1');
                    // url.setCharAt(43,'0');
                    url.append('0');
                } else {
                    char c = s.charAt(k);

                    url.setCharAt(42, c);
                }

                final Document document = Jsoup.connect(url.toString()).get();
                Elements scriptElements = document.select("ul.job-list.job-list2 > li");

                int sizeEle = scriptElements.size();
                for (int j = 0; j < sizeEle; j++) {
                    try {

                        Elements division = scriptElements.get(j).select("div.section");
                        String title = division.select("h2 > a").text().trim();
                        String link1 = division.select("h2 > a").first().attr("href");
                        String link = "https://www.rekrute.com" + link1;
                        Elements infos = division.select("div.holder");
                        String requirements = infos.get(0).select("span").text();
                        // String infoCompany = infos.get(0).select("span").get(1).text();
                        String description = infos.get(0).select("span").get(2).text();
                        // ajouter ville
                        int villeIndex = title.indexOf(" | ");
                        String ville = title.substring(villeIndex + 3);
                        // ajouter contrat
                        Elements info = infos.get(0).select("ul");
                        String contrat = info.select("li").get(4).text().substring(26);
                        // ajouter experience
                        String experience = info.select("li").get(2).text().substring(21);
                        System.out.println(experience);

                        
                        String[] technologies = { "react", "angular", "vuejs", "html", "css", "javascript", "python",
                                "sql", "java", "node", "typescript", "c#", "bash", "shell", "c++", "php", "flutter",
                                "go", "kotlin", "rust", "ruby", "dart", "assembly", "swift", "matlab", "mysql",
                                "postgresql", "sqlite", "mongodb", "redis", "firebase", "oracle",
                                "aws", "docker", "heroku", "kubernetes", "linux", "flask", "django", "asp.net",
                                "spring", "laravel", "tensorflow", "react native", "keras" };
                        System.out.println("*****************************");

                        ArrayList<Integer> requirementes = new ArrayList<Integer>();

                        for (int f = 0; f < technologies.length; f++) {
                            if (description.toLowerCase().contains(technologies[f])
                                    || requirements.toLowerCase().contains(technologies[f])) {
                                requirementes.add(1);
                            } else {
                                requirementes.add(0);
                            }
                        }

                        String req = "";
                        for (int id : requirementes) {
                            req += id + " ";
                        }

                        String date = infos.get(0).select("em").text();
                        // String additionalInfo = infos.get(0).select("ul").text();

                        // store in general EmploiJob
                        EmploiJob emploi = new EmploiJob(title, contrat, experience, ville, req, "1", link, date);
                        offres.add(emploi);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        }
     
        return offres;

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Bismi Allah");

    }
}
