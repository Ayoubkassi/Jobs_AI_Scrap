/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author ryota
 */
public class Offre {
    int id; 
    String title;
    String requirements;
    String companyInfo ;
    String description ;
    String date ;
    String  additionalInfo;
    String link;
    String image;

    public Offre() {
    }

    
    
    public Offre(int id, String title, String requirements, String companyInfo, String description, String date, String additionalInfo, String link) {
        this.id = id;
        this.title = title;
        this.requirements = requirements;
        this.companyInfo = companyInfo;
        this.description = description;
        this.date = date;
        this.additionalInfo = additionalInfo;
        this.link = link;
    }

    public Offre(String title, String requirements, String companyInfo, String description, String date, String additionalInfo, String link) {
        this.title = title;
        this.requirements = requirements;
        this.companyInfo = companyInfo;
        this.description = description;
        this.date = date;
        this.additionalInfo = additionalInfo;
        this.link = link;
    }
    
    //without link
    
    public Offre(String title, String requirements, String companyInfo, String description, String date, String additionalInfo) {
        this.title = title;
        this.requirements = requirements;
        this.companyInfo = companyInfo;
        this.description = description;
        this.date = date;
        this.additionalInfo = additionalInfo;
    }
    
    
    //with image and link
    
      public Offre(String title, String requirements, String companyInfo, String description, String date, String additionalInfo, String image , String link) {
        this.title = title;
        this.requirements = requirements;
        this.companyInfo = companyInfo;
        this.description = description;
        this.date = date;
        this.additionalInfo = additionalInfo;
        this.image = image;
        this.link = link;

    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", title=" + title + ", requirements=" + requirements + ", companyInfo=" + companyInfo + ", description=" + description + ", date=" + date + ", additionalInfo=" + additionalInfo + ", link=" + link + '}';
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
    
    
}
