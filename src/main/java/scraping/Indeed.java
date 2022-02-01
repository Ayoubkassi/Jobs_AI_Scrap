/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scraping;

/**
 *
 * @author pattern
 */

import model.EmploiJob;
import static database.HandleDB.addJob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Indeed {

    public static ArrayList<EmploiJob> ScrapJobs(String jobTitle, int n, String country, String type, String date)
            throws IOException {
        
        String cnt = "";
        String initialDate = date;

        if (country.equals("germany")) {
            cnt = "de.";
        } else if (country.equals("morocco")) {
            cnt = "ma.";
        } else if (country.equals("france")) {
            cnt = "fr.";
        }
        String[] words = jobTitle.split(" ");
        int size = words.length;
        String first_url = "https://" + cnt + "indeed.com/jobs?q=";
        String med_url = "";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                med_url += words[i].toLowerCase() + "";
            } else {
                med_url += words[i].toLowerCase() + "+";
            }
        }

        String base_url = first_url + med_url;
        ArrayList<EmploiJob> offres = new ArrayList<EmploiJob>();

        for (int i = 20; i <= n * 10; i += 10) {
            final String url = base_url + "&start=" + i + "&jt=" + type + "&fromage=" + date;
            System.out.println(i);
            final Document document = Jsoup.connect(url).get();
            //System.out.println(document);
            Elements scriptElements = document.getElementsByTag("script");
            int scr_num = 0;
            String data = "";

            try {
                for (Element element : scriptElements) {
                    scr_num++;
                    if (scr_num == 26) {
                        for (DataNode node : element.dataNodes()) {
                            data = node.getWholeData();
                            System.out.println("data *************");
                            System.out.println(data);
                        }

                    }
                }

                int ext_begin = data.indexOf("zrprclk");
                String ext_data = data.substring(ext_begin + 105, ext_begin + 121);

                int begin = data.indexOf("jobmap[0]");
                String jobs_data = data.substring(begin);
                String[] list_jobs = jobs_data.split(";");
                // have title , type , date , ville , link
                int max_jobs = list_jobs.length - 1;
                int start = 0;
                for (String job : list_jobs) {
                    if (start < max_jobs) {
                        try {
                            int end_title = job.indexOf("locid");
                            int start_title = job.indexOf("title");
                            String title = job.substring(start_title + 7, end_title - 2);
                            int start_jk = job.indexOf("{") + 5;
                            int end_jk = job.indexOf("efccid") - 2;
                            String jk = job.substring(start_jk, end_jk);
                            String job_link = "https://indeed.com/viewjob?jk=" + jk + "&tk=" + ext_data
                                    + "&from=serp&vjs=3";
                            int indexVille = job.indexOf(",city:'");
                            int endVille = job.indexOf("',title:");
                            String ville = job.substring(indexVille + 7, endVille);
                            date = initialDate;

                            try {
                                Document single = Jsoup.connect(job_link).get();
                                Element body = single.select("body").get(0);
                                Elements div = body.select("div.jobsearch-JobComponent-description");
                                String job_description = div.text().toLowerCase();
                                int experienceIndex = job_description.indexOf("d’expérience");
                                String experience = "";
                                boolean expexist = job_description.contains("d’expérience");
                                if (expexist) {
                                    experience = job_description.substring(experienceIndex - 13, experienceIndex);
                                }

                                ArrayList<Integer> requirements = new ArrayList<Integer>();
                                String[] technologies = { "react", "angular", "vuejs", "html", "css", "javascript",
                                        "python", "sql", " java ", "node", "typescript", "c#", "bash", "shell", "c++",
                                        "php", "flutter", "go", "kotlin", "rust", "ruby", "dart", "assembly", "swift",
                                        "matlab", "mysql", "postgresql", "sqlite", "mongodb", "redis", "firebase",
                                        "oracle",
                                        "aws", "docker", "heroku", "kubernetes", "linux", "flask", "django", "asp.net",
                                        "spring", "laravel", "tensorflow", "react native", "keras" };

                                for (int k = 0; k < technologies.length; k++) {
                                    if (job_description.contains(technologies[k])) {
                                        requirements.add(1);
                                    } else {
                                        requirements.add(0);
                                    }
                                }

                                String req = "";
                                for (int id : requirements) {
                                    req += id + " ";
                                }

                                date = "last" + initialDate + "days";
                                // save Emploi and add Array
                                EmploiJob offre = new EmploiJob(title, type, experience, ville, req, "1", job_link,
                                        date);
                                //System.out.println(offre.toString());
                                offres.add(offre);
                                offre.affiche();

                            } catch (Exception e) {
                                e.printStackTrace();
                                continue;
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                    start++;
                }

            } catch (Exception ex) {

            }

        }

        

        return offres;
    }


    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Bismi Allah");
        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();

        try {
            
            jobs = ScrapJobs("back end",2, "", "fulltime", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (EmploiJob job : jobs) {
            addJob(job, "BackEnd", "Indeed");
        }

    }
}
