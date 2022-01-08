/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pattern
 */
public class EmploiJob {
    public String title;
    public String typeContart;
    public String experience;
    public String ville;
    public String requirements;
    public String nbPoste;
    public String link;
    public String date;

    public EmploiJob() {
    }

    public EmploiJob(String title, String typeContart, String experience, String ville, String requirements,
            String nbPoste, String link, String date) {
        this.title = title;
        this.typeContart = typeContart;
        this.experience = experience;
        this.ville = ville;
        this.requirements = requirements;
        this.nbPoste = nbPoste;
        this.link = link;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeContart() {
        return typeContart;
    }

    public void setTypeContart(String typeContart) {
        this.typeContart = typeContart;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNbPoste() {
        return nbPoste;
    }

    public void setNbPoste(String nbPoste) {
        this.nbPoste = nbPoste;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "EmploiJob{" + "title=" + title + ", typeContart=" + typeContart + ", experience=" + experience
                + ", ville=" + ville + ", requirements=" + requirements + ", nbPoste=" + nbPoste + ", link=" + link
                + ", date=" + date + '}';
    }

    public void affiche() {
        System.out.println(this.toString());
    }

}
