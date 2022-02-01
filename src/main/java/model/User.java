/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pattern
 */
public class User {
    public String email;
    public String username;
    public String pasword;
    public String secteur;

    // parameterize constructor
    public User(String email, String username, String pasword, String secteur) {
        this.email = email;
        this.username = username;
        this.pasword = pasword;
        this.secteur = secteur;
    }
    
    public User(){
        
    }

    // setters and getters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", username=" + username + ", pasword=" + pasword + ", secteur=" + secteur
                + '}';
    }

    public void affiche() {
        System.out.println(this.toString());
    }

}
