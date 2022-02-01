/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author othmane
 */
public class GFG {
     public long difference_In_Seconds, difference_In_Minutes, difference_In_Hours, difference_In_Years, difference_In_Days;
    // Function to print difference in
    // time start_date and end_date
    public void findDifference(String start_date, String end_date) {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            this.difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            this.difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            this.difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            this.difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            this.difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
