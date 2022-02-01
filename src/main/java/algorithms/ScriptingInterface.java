/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package algorithms;

import java.sql.SQLException;
import java.util.ArrayList;
import model.EmploiJob;

/**
 *
 * @author ryota
 */
public interface ScriptingInterface {
    
    void jobsApplies(String login, String pass, String message)
            throws ClassNotFoundException, SQLException, InterruptedException;
    
}
