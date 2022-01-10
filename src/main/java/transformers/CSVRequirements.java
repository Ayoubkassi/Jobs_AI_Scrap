/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transformers;

import model.EmploiJob;
import static database.HandleDB.fetchJobsDomaine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author pattern
 */
public class CSVRequirements {
    public static void saveCSVRequirements() throws FileNotFoundException {
        File csvFile = new File("frontend.csv");
        PrintWriter out = new PrintWriter(csvFile);
        // ArrayList<EmploiJob> jobs = fetchJobs();
        ArrayList<EmploiJob> jobs = fetchJobsDomaine("FrontEnd");

        for (EmploiJob job : jobs) {
            char[] requirement = job.requirements.toCharArray();
            int[] requirements = new int[100];
            for (int i = 0; i < 45; i++) {
                System.out.println(i);
                requirements[i * 2] = Character.getNumericValue(requirement[i * 2]);
                System.out.println(requirements[i]);
            }

            out.printf(
                    "%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%s\n",
                    requirements[0], requirements[2], requirements[4], requirements[6], requirements[8],
                    requirements[10], requirements[12], requirements[14], requirements[16], requirements[18],
                    requirements[20], requirements[22], requirements[24], requirements[26], requirements[28],
                    requirements[30], requirements[32], requirements[34], requirements[36], requirements[38],
                    requirements[40], requirements[42], requirements[44], requirements[46], requirements[48],
                    requirements[50], requirements[52], requirements[54], requirements[56], requirements[58],
                    requirements[60], requirements[62], requirements[64], requirements[66], requirements[68],
                    requirements[70], requirements[72], requirements[74], requirements[76], requirements[78],
                    requirements[80], requirements[82], requirements[84], requirements[86], requirements[88],
                    "FrontEnd");

        }

        out.close();

    }

    public static void main(String[] args) throws FileNotFoundException {
        saveCSVRequirements();
    }
}
