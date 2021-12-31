/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import static User.HandleDB.getEmploiLinks;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author ryota
 */


public class SeleniumAuto {
    
    
    public static void jobsApplies(String login , String pass, String message) throws ClassNotFoundException, SQLException, InterruptedException{
        ArrayList<String> links = getEmploiLinks();
        
        WebDriver webDriver;
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://www.emploi.ma/login");
        webDriver.findElement(By.xpath("//*[@id=\"edit-name\"]")).sendKeys(login);
        webDriver.findElement(By.xpath("//*[@id=\"edit-pass\"]")).sendKeys(pass);
        webDriver.findElement(By.xpath("//*[@id=\"edit-submit\"]")).click();
        for(String link : links){
            webDriver.navigate().to(link);
            webDriver.findElement(By.xpath("//*[@id=\"main-content\"]/div[1]/div[2]/a")).click();
            Thread.sleep(1000);
            webDriver.findElement(By.xpath("//*[@id=\"edit-body-und-0-value\"]")).sendKeys(message);
            webDriver.findElement(By.xpath("//*[@id=\"edit-submit\"]")).click();
                        
            Thread.sleep(1000);
        }

    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException{
        jobsApplies("ayoub.kassi@uit.ac.ma","3117G0F597","Hey Hey and welocme again");
    }
}
