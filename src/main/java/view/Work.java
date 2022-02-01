/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.EmploiJob;
import static scraping.Emploi.getJobs;
import static database.HandleDB.addJob;
import static database.HandleDB.fetchJobsParams;
import static database.HandleDB.fetchJobsParamsDate;
import static database.HandleDB.getScrapeConfigs;
import static database.HandleDB.lastDateScrap;
import static scraping.Indeed.ScrapJobs;
import static scraping.Recrute.getJobsGen;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import utils.GFG;

/**
 *
 * @author pattern
 */
public class Work extends javax.swing.JFrame {

    /**
     * Creates new form Work
     */
    ArrayList<EmploiJob> offres = new ArrayList<EmploiJob>();
    ArrayList<EmploiJob> offres1 = new ArrayList<EmploiJob>();
    ArrayList<EmploiJob> offres2 = new ArrayList<EmploiJob>();

    public Work() {
        initComponents();
    }

    // this is the constructor that we gonna be working with
    public Work(String startdate, String enddate, String domaine, String type, String country, String indeed,
            String rekrute, String emploi) throws IOException, SQLException, ClassNotFoundException {
        initComponents();
        String datee = enddate.split("-")[0];
        // System.out.println(datee);
        // Get Scrape Rate Configs
        HashMap scrapeRateConfigs = getScrapeConfigs();
        // here we gonna choose between scrap or load database
        // check day of month
        // Current Date
        String currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        // Last Scrape Date
        String dateScrap = lastDateScrap();

        // Get DateTime Diff
        GFG dateDiff = new GFG();
        dateDiff.findDifference(dateScrap, currentDateTime);

        System.out.println("Diff in days : " + dateDiff.difference_In_Days);
        System.out.println("Diff in hours : " + dateDiff.difference_In_Hours);

        if (scrapeRateConfigs.get("scrape_rate_context").toString().toLowerCase().equals("hours")) {
            if (dateDiff.difference_In_Hours < Integer
                    .parseInt(scrapeRateConfigs.get("scrape_rate_count").toString())) {
                offres = fetchJobsParamsDate(datee, domaine, type, country, indeed, rekrute, emploi);
            } else {
                // scrap data
                if (indeed.equals("Indeed")) {
                    // scrap indeed
                    offres = ScrapJobs("Software Engineer", 2, "", type, "3");
                    for (EmploiJob job : offres) {
                        // and add into database
                        addJob(job, "FullStack", "Indeed");
                    }
                }
                if (rekrute.equals("Rekrute")) {
                    offres1 = getJobsGen("developpeur", 10);
                }
                if (emploi.equals("Emploi")) {
                    offres2 = getJobs("", 20);

                }

                // add rekrute to all

                for (EmploiJob job : offres1) {
                    offres.add(job);
                    // and add into database
                    addJob(job, "FullStack", "Rekrute");
                }

                // add Emploi jobs

                for (EmploiJob job : offres2) {
                    offres.add(job);
                    // and add into database
                    addJob(job, "FullStack", "Emploi");
                }

            }
        } else if (scrapeRateConfigs.get("scrape_rate_context").toString().toLowerCase().equals("days")) {
            if (dateDiff.difference_In_Days < Integer.parseInt(scrapeRateConfigs.get("scrape_rate_count").toString())) {
                offres = fetchJobsParamsDate(datee, domaine, type, country, indeed, rekrute, emploi);
            } else {
                // scrap data
                if (indeed.equals("Indeed")) {
                    // scrap indeed
                    offres = ScrapJobs("Software Engineer", 10, "", type, "3");
                    for (EmploiJob job : offres) {
                        // and add into database
                        addJob(job, "FullStack", "Indeed");
                    }
                }
                if (rekrute.equals("Rekrute")) {
                    offres1 = getJobsGen("developpeur", 10);
                }
                if (emploi.equals("Emploi")) {
                    offres2 = getJobs("", 20);

                }

                // add rekrute to all

                for (EmploiJob job : offres1) {
                    offres.add(job);
                    // and add into database
                    addJob(job, "FullStack", "Rekrute");
                }

                // add Emploi jobs

                for (EmploiJob job : offres2) {
                    offres.add(job);
                    // and add into database
                    addJob(job, "FullStack", "Emploi");
                }

            }
        }

        updateTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJobs = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 120, 219));
        jLabel1.setText("JOBS SCRAPER");

        tblJobs.setBackground(new java.awt.Color(255, 255, 255));
        tblJobs.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        tblJobs.setForeground(new java.awt.Color(0, 0, 0));
        tblJobs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Company", "Ville"
            }
        ));
        tblJobs.setGridColor(new java.awt.Color(33, 120, 219));
        tblJobs.setSelectionBackground(new java.awt.Color(33, 120, 219));
        tblJobs.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblJobs.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(tblJobs);

        jButton1.setBackground(new java.awt.Color(33, 120, 219));
        jButton1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("FUN START HERE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 120, 219));
        jButton2.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 334, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(81, 81, 81)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // start

        PreTraining cp = null;
        cp = new PreTraining();
        cp.setVisible(true);
        this.setVisible(false);

    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        Intermed cp = null;
        cp = new Intermed();
        cp.setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    Connection con;
    Statement st;

    private void updateTable() {
        try {
            // get rows that i need with HandleDB
            int id = 0;
            DefaultTableModel model = (DefaultTableModel) tblJobs.getModel();

            for (EmploiJob job : offres) {

                Object[] row = new Object[4];
                row[0] = ++id;
                row[1] = job.getTitle();
                row[2] = job.getTypeContart();
                row[3] = job.getVille();

                model.addRow(row);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Work.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Work.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Work.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Work.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Work().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblJobs;
    // End of variables declaration//GEN-END:variables
}
