/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scraping;

import model.EmploiJob;
import static database.HandleDB.addJob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author pattern
 */
public class Emploi {
    public static ArrayList<EmploiJob> getJobs(String secteur, int nbPages) throws IOException {

        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();
        String first_url = "https://www.emploi.ma/recherche-jobs-maroc?f%5B0%5D=im_field_offre_metiers%3A31&page=";

        for (int i = 1; i <= nbPages; i++) {
            String url = first_url + i;
            try {
                final Document document = Jsoup.connect(url.toString()).get();
                Elements scriptElements = document.select("div.job-description-wrapper");

                for (Element element : scriptElements) {
                    Element row = element.select("div > div > div > div").get(1);

                    String title = row.select("h5 > a").text();
                    String link = "https://www.emploi.ma" + row.select("h5 > a").attr("href");

                    // scrap into each link alone

                    try {
                        Document offre = Jsoup.connect(link).get();
                        Elements header = offre.select("div.block-header");

                        String date = header.select("div.job-ad-publication-date").text().substring(11);

                        Elements infos = offre.select("div.container-page-content");
                        Elements info = infos.select("table.job-ad-criteria > tbody");
                        String contrat = "";
                        String experience = "";
                        String ville = "";
                        String nbPoste = "";
                        for (Element ele : info) {
                            Elements single = ele.select("tr > td");
                            contrat = single.get(5).text();
                            experience = single.get(11).text();
                            ville = single.get(9).text();
                            nbPoste = single.get(17).text();

                        }

                        ArrayList<Integer> requirements = new ArrayList<Integer>();

                        String Descriptions = offre.select("div.content.clearfix").get(1).text();
                        String[] technologies = { "react", "angular", "vuejs", "html", "css", "javascript", "python",
                                "sql", "java", "node", "typescript", "c#", "bash", "shell", "c++", "php", "flutter",
                                "go", "kotlin", "rust", "ruby", "dart", "assembly", "swift", "matlab", "mysql",
                                "postgresql", "sqlite", "mongodb", "redis", "firebase", "oracle",
                                "aws", "docker", "heroku", "kubernetes", "linux", "flask", "django", "asp.net",
                                "spring", "laravel", "tensorflow", "react native", "keras" };
                        System.out.println("*****************************");

                        for (int j = 0; j < technologies.length; j++) {
                            if (Descriptions.toLowerCase().contains(technologies[j])) {
                                requirements.add(1);
                            } else {
                                requirements.add(0);
                            }
                        }

                        String req = "";
                        for (int id : requirements) {
                            req += id + " ";
                        }

                        EmploiJob job = new EmploiJob(title, contrat, experience, ville, req, nbPoste, link, date);
                        jobs.add(job);

                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return jobs;
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();

        jobs = getJobs("", 20);


        for (EmploiJob job : jobs) {
            addJob(job, "Informatique", "Rekrute");
        }

       
    }
}
