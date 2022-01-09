/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import model.EmploiJob;
import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pattern
 */
public class HandleDB {

    public HandleDB() {
    }

    // connection avec la base de donne
    public static Statement connectToDB() throws ClassNotFoundException, SQLException {

        Connection connect = null;
        Statement statement = null;
        String url = "jdbc:mysql://localhost:3306/JobsScraper";
        String user = "root";
        String password = "guillaume";

        try {
            // Definer notre driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Initializer la connection
            connect = DriverManager.getConnection(url, user, password);
            // statements
            statement = connect.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return statement;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            Statement st = connectToDB();
            String sql = "select * from user";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String secteur = rs.getString("secteur");

                User user = new User(email, username, password, secteur);
                users.add(user);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public static boolean isUserInDB(String email) {
        boolean exist = false;
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if (user.email.equals(email)) {
                exist = true;
            }
        }

        return exist;

    }

    public static void addUser(User user) {
        try {
            Statement st = connectToDB();

            String requette = "INSERT INTO `user`(`username`, `password`, `email`,`secteur`) "
                    + "VALUES ('" + user.username + "','" + user.pasword + "','" + user.email + "','" + user.secteur
                    + "')";
            st.execute(requette);
            System.out.println("User added");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void addJob(EmploiJob job, String domaine, String site) throws SQLException, ClassNotFoundException {

        try {
            Statement st = connectToDB();
            String sql = "select * from Jobs where title='" + job.title + "'and site ='" + site + " limit = 1'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.first()) {
                // verifier si l'offre existe deja dans la base de donnes;
                System.out.println("Sorry deja cette Offre existe avec le meme title et company");
            } else {

                String requette = "INSERT INTO `Jobs`(`title`, `typeContrat`, `experience`,`ville`,`requirements`,`nbPoste`,`link`,`date`,`site`,`domaine`) "
                        + "VALUES ('" + job.title + "','" + job.typeContart + "','" + job.experience + "','" + job.ville
                        + "','" + job.requirements + "','" + job.nbPoste + "','" + job.link + "','" + job.date + "','"
                        + site + "','" + domaine + "')";
                st.execute(requette);
                System.out.println("added");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // afficher toutes les offres de avec parametre

    public static ArrayList<EmploiJob> fetchJobsParams(String domaine, String type, String country, String first,
            String second, String third) {
        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();

        // i will code it as binary 000 -> means no one , or 111->7 means all of them
        System.out.println("begin");
        try {
            Statement st = connectToDB();
            String sql = "SELECT * FROM `Jobs` WHERE domaine = '" + domaine + "' and typeContrat= '" + type
                    + "' and country = '" + country + "' and site in ('" + first + "','" + second + "','" + third
                    + "')";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                EmploiJob job = new EmploiJob(rs.getString("title"), rs.getString("typeContrat"),
                        rs.getString("experience"), rs.getString("ville"), rs.getString("requirements"),
                        rs.getString("nbPoste"), rs.getString("link"), rs.getString("date"));
                jobs.add(job);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("probleem");
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);

        }

        return jobs;
    }

    public static ArrayList<EmploiJob> fetchJobsParamsDate(String date, String domaine, String type, String country,
            String first, String second, String third) {
        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();
        // i will code it as binary 000 -> means no one , or 111->7 means all of them
        if (date.equals("2021") || date.equals("2022")) {
            System.out.println("begin");
            try {
                Statement st = connectToDB();
                String sql = "SELECT * FROM `Jobs` WHERE domaine = '" + domaine + "' and typeContrat= '" + type
                        + "' and country = '" + country + "' and site in ('" + first + "','" + second + "','" + third
                        + "')";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    EmploiJob job = new EmploiJob(rs.getString("title"), rs.getString("typeContrat"),
                            rs.getString("experience"), rs.getString("ville"), rs.getString("requirements"),
                            rs.getString("nbPoste"), rs.getString("link"), rs.getString("date"));
                    jobs.add(job);
                }

                return jobs;
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("probleem");
                Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        return jobs;
    }

    // methode additionel pour afficher toutes les Offres

    public static ArrayList<EmploiJob> fetchJobs() {

        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();

        try {
            Statement st = connectToDB();
            String sql = "select * from Jobs ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                EmploiJob job = new EmploiJob(rs.getString("title"), rs.getString("typeContrat"),
                        rs.getString("experience"), rs.getString("ville"), rs.getString("requirements"),
                        rs.getString("nbPoste"), rs.getString("link"), rs.getString("date"));
                jobs.add(job);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jobs;
    }

    public static ArrayList<EmploiJob> fetchJobsDomaine(String name) {

        ArrayList<EmploiJob> jobs = new ArrayList<EmploiJob>();

        try {
            Statement st = connectToDB();
            String sql = "select * from Jobs where domaine='" + name + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                EmploiJob job = new EmploiJob(rs.getString("title"), rs.getString("typeContrat"),
                        rs.getString("experience"), rs.getString("ville"), rs.getString("requirements"),
                        rs.getString("nbPoste"), rs.getString("link"), rs.getString("date"));
                jobs.add(job);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jobs;
    }
    
    
    public static ArrayList<String> fetchRequirements(){
        ArrayList<String> wantedTechs = new ArrayList<String>();
        try{
             Statement st = connectToDB();
             String sql = "select requirements from Jobs";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               String requi = rs.getString("requirements");
               wantedTechs.add(requi);
            }
        }catch(ClassNotFoundException | SQLException ex){
             Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return wantedTechs;
    }

    // methode additionel pour supprimer un etudiant avec nom et prenom

    // public static void deleteJobWithTitleandCompany(String company , String
    // title){
    // Offre job = null;
    // try{
    // job = selectJobByTitleAndCompany(company,title);
    // //verifier l'existence d'abord
    // if(job != null){
    // Statement st = connectToDB();
    // String sql = "DELETE FROM `Jobs` WHERE company='" + company + "' and
    // title='"+ title +"'";
    // st.execute(sql);
    // System.out.println("Offre supprime de la base de donne");
    // }
    // }catch(ClassNotFoundException | SQLException ex){
    // Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
    // }
    // }
    //
    // selct id by nom et prenom

    // select one job by company and title
    public static int selectId(String company, String title) throws ClassNotFoundException, SQLException {
        int id = 1;
        Statement st = connectToDB();
        String sql = "select * from Jobs where company='" + company + "'and title ='" + title + "'";
        ResultSet rs = st.executeQuery(sql);
        if (!rs.first()) {
            System.out.println("Offre avec id : " + id + " n'existe pas");
        } else {

            id = rs.getInt("id");

        }
        return id;
    }

    // retourner les lien de toutes les offres
    public static ArrayList<String> getEmploiLinks() throws ClassNotFoundException, SQLException {
        ArrayList<String> links = new ArrayList<String>();
        try {
            Statement st = connectToDB();
            String site = "Emploi";
            String sql = "select link from Jobs where site='" + site + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                links.add(rs.getString("link"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return links;
    }

    // select dateScrap from Jobs ORDER BY id DESC LIMIT 1;

    public static String lastDateScrap() {
        String lastDate = "";
        try {
            Statement st = connectToDB();
            String sql = "select * from Jobs ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lastDate = rs.getString("dateScrap");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lastDate;
    }
    

    public static HashMap<String, String> getScrapeConfigs() {
        HashMap<String, String> configs = new HashMap<>();
        try {
            Statement st = connectToDB();
            String sql = "select * from configs where config_name='scrape_rate_context' or `config_name`='scrape_rate_count'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                configs.put(rs.getString("config_name"), rs.getString("config_value"));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HandleDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return configs;
    }
}
