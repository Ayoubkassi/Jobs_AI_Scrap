/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryota
 */
public class HandleDB {

    public HandleDB() {
    }
    
    
    
     //connection avec la base de donne
    public static Statement connectToDB() throws ClassNotFoundException, SQLException{
        
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://localhost:3306/JobsScraper";
        String user = "root";
        String password = "guillaume";
        
         try{
            //Definer notre driver
            Class.forName("com.mysql.jdbc.Driver");  
            //Initializer la connection
            connect = DriverManager.getConnection(url,user,password);
            //statements
            statement = connect.createStatement();
         }
        catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            return statement;
    }
    
    
    
    public static ArrayList<User> getUsers(){
          ArrayList<User> users = new ArrayList<User>();
          
           try{
        Statement st = connectToDB();
        String sql = "select * from user";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String secteur = rs.getString("secteur");
            
            User user = new User(email,username,password,secteur);
            users.add(user);
        }
        
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          return users;
    }
    
    public static boolean isUserInDB( String email){
        boolean exist = false;
        ArrayList<User> users = getUsers();
        for(User user : users){
            if(user.email.equals(email)){
                exist = true;
            }
        }
        
        return exist;
        
    }
    
    
    public static void addUser(User user){
          try{
            Statement st = connectToDB();
           
                String requette = "INSERT INTO `user`(`username`, `password`, `email`,`secteur`) "
                        + "VALUES ('" + user.username + "','" + user.pasword + "','" +user.email + "','" + user.secteur + "')";
                st.execute(requette);
                System.out.println("User added");
        }
        catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
    } 
 
 }

    //trouver etudiant avce Id
    public static Offre selectJobById(int id) throws ClassNotFoundException, SQLException{
        
        
        
        Offre job = null;
        Statement st = connectToDB();
        String sql = "select * from Jobs where id='" + id + "'";
        ResultSet rs = st.executeQuery(sql);
         if (!rs.first()) {
             System.out.println("Offre avec id : "+id+" n'existe pas");
         }else{
                String title = rs.getString("title");
//                String nom = rs.getString("nom");
//                String annee = rs.getString("anneeScolaire");
//                String filiere = rs.getString("filiere");
                
                 job = new Offre();
                                
         }
  
        
        return job;
    }
    
    
    //trouver etudiant avec nom complet nom et prenom
    public static Offre selectJobByTitleAndCompany(String company , String title) throws ClassNotFoundException, SQLException{
        Offre job = null;
        
        try{
        Statement st = connectToDB();
        String sql = "select * from Jobs where title='" + title + "'and company ='" +company + "'";
        ResultSet rs = st.executeQuery(sql);
         if (!rs.first()) {
             System.out.println("Offre avec company et title : "+company+" "+title + "n'existe pas");
         }else{
                String titl = rs.getString("title");
//                String no = rs.getString("nom");
//                String annee = rs.getString("anneeScolaire");
//                String filiere = rs.getString("filiere");
                
                job = new Offre();
                
                
         }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
        return job;
    }
    
    public static void addJob(EmploiJob job , String domaine , String site) throws SQLException, ClassNotFoundException{
        
        try{
            Statement st = connectToDB();
            String sql = "select * from Jobs where title='" + job.title + "'and site ='" +site + " limit = 1'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.first()){
                //verifier si l'eleve existe deja dans la base de donnes;
                System.out.println("Sorry deja cette Offre existe avec le meme title et company");
            }else{

                String requette = "INSERT INTO `Jobs`(`title`, `typeContrat`, `experience`,`ville`,`requirements`,`nbPoste`,`link`,`date`,`site`,`domaine`) "
                        + "VALUES ('" + job.title + "','" + job.typeContart + "','" +job.experience + "','" +job.ville + "','" +job.requirements + "','" +job.nbPoste + "','" +job.link + "','" + job.date + "','"+ site +  "','"  + domaine + "')";
                st.execute(requette);
                System.out.println("added");
        }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //modifier par id
    public static void updateJob(int id,Offre newJob) throws SQLException, ClassNotFoundException{
        Offre job = null;
        try{
        job = selectJobById(id);
        //verifier l'existence d'abord
        if(job != null){
              Statement st = connectToDB();
              String sql = "UPDATE `Jobs`SET title='" + newJob.title + "',company='" + newJob.companyInfo + "',date='" + newJob.date  + "',description='" + newJob.description + "'WHERE id='" + id + "'";
              st.execute(sql);
              System.out.println("Offre mis a jour avec succes");
           }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    //methode additionel pour afficher toutes les etudiant
    
    public static void fetchJobs(){
        
        ArrayList<Offre> jobs = new ArrayList<Offre>();
        
        try{
            Statement st = connectToDB();
            String sql = "select * from Jobs ";
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                Offre job = new Offre(rs.getString("title"), rs.getString("requirements"), rs.getString("company"),rs.getString("description"),rs.getString("link"),rs.getString("date"));
                jobs.add(job);
                job.affiche();
            }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //methode additionel pour afficher toutes les etudiant
    
    public static ArrayList<Offre> fetchJob(){
        
        ArrayList<Offre> jobs = new ArrayList<Offre>();
        
        try{
            Statement st = connectToDB();
            String sql = "select * from Offre ";
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                Offre job = new Offre(rs.getString("title"), rs.getString("company"), rs.getString("description"),rs.getString("requirements"),rs.getString("date"),rs.getString("link"));
                jobs.add(job);
                job.affiche();
            }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jobs;
        
    }
    
    //methode additionel pour supprimer un etudiant avec id
    
    public static void deleteJobWithID(int id){
         Offre job = null;
        try{
        job = selectJobById(id);
        //verifier l'existence d'abord
        if(job != null){
              Statement st = connectToDB();
              String sql = "DELETE FROM `Jobs` WHERE id='" + id + "'";
              st.execute(sql);
              System.out.println("Offre supprime de la base de donne");
           }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   //methode additionel pour supprimer un etudiant avec nom et prenom
    
    public static void deleteJobWithTitleandCompany(String company , String title){
        Offre job = null;
        try{
        job = selectJobByTitleAndCompany(company,title);
        //verifier l'existence d'abord
        if(job != null){
              Statement st = connectToDB();
              String sql = "DELETE FROM `Jobs` WHERE company='" + company + "' and title='"+ title +"'";
              st.execute(sql);
              System.out.println("Offre supprime de la base de donne");
           }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //selct id by nom et prenom
    
    public static int selectId(String company , String title) throws ClassNotFoundException, SQLException{
        int id = 1;
        Statement st = connectToDB();
        String sql = "select * from Jobs where company='" + company+ "'and title ='" +title + "'";
        ResultSet rs = st.executeQuery(sql);
         if (!rs.first()) {
             System.out.println("Offre avec id : "+id+" n'existe pas");
         }else{
                
                id = rs.getInt("id");
                
                                
         }
        return id;
    }
}
