/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author ryota
 */
public class User {
     String fname,lname,id,filiere,annee;
    public User(String fname,String lname,String id,String annee, String filiere){
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.annee = annee;
        this.filiere = filiere;
        
    }

    @Override
    public String toString() {
        return "User{" + "fname=" + fname + ", lname=" + lname + ", id=" + id + ", filiere=" + filiere + ", annee=" + annee + '}';
    }
    
    
    
    //getters
    public String getLname(){
        return this.lname;
    }
    public String getFname(){
        return this.fname;
    }
    public String getId(){
        return this.id;
    }
    public String getFiliere(){
        return this.filiere;
    }
    public String getAnnee(){
        return this.annee;
    }
}
