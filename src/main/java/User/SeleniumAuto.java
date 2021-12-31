/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author ryota
 */


public class SeleniumAuto {
    
    
    public static void jobsApplies(String login , String pass){
        WebDriver webDriver;
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://www.emploi.ma/login");
        webDriver.findElement(By.xpath("//*[@id=\"edit-name\"]")).sendKeys(login);
        webDriver.findElement(By.xpath("//*[@id=\"edit-pass\"]")).sendKeys(pass);
        webDriver.findElement(By.xpath("//*[@id=\"edit-submit\"]")).click();
        

    }
    
    public static void main(String[] args){
        jobsApplies("ayoub.kassi@uit.ac.ma","3117G0F597");
    }
}
