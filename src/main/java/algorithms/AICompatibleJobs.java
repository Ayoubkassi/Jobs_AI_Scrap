/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import model.EmploiJob;
import static database.HandleDB.fetchJobs;
import java.util.ArrayList;

/**
 *
 * @author pattern
 */
public class AICompatibleJobs {

    public static ArrayList<EmploiJob> getCompatibleJobs(String skills) {
        // initialize return data
        ArrayList<EmploiJob> jobs = fetchJobs();
        ArrayList<EmploiJob> bestJobs = new ArrayList<EmploiJob>();

        // check frequence : value to be added is 60%
        int skillsFrequence = skills.split(" ").length;
        int acceptableFrequence = (int) Math.round(skillsFrequence * 0.6);

        // total technologies that wa have
        String[] technologies = { "react", "angular", "vuejs", "html", "css", "javascript", "python", "sql", "java",
                "node", "typescript", "c#", "bash", "shell", "c++", "php", "flutter", "go", "kotlin", "rust", "ruby",
                "dart", "assembly", "swift", "matlab", "mysql", "postgresql", "sqlite", "mongodb", "redis", "firebase",
                "oracle",
                "aws", "docker", "heroku", "kubernetes", "linux", "flask", "django", "asp.net", "spring", "laravel",
                "tensorflow", "react native", "keras" };

        // loop and add into BestJob if the value is accepted
        for (EmploiJob job : jobs) {
            int frequence = 0;
            char[] requirements = job.requirements.toCharArray();
            for (int i = 0; i < requirements.length; i += 2) {
                if (requirements[i] == '1' && skills.contains(technologies[i / 2])) {
                    frequence++;
                }

            }

            if (frequence >= acceptableFrequence) {
                bestJobs.add(job);
            }

        }

        return bestJobs;
    }

    
}
