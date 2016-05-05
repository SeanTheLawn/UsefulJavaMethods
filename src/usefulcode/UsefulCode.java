/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usefulcode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SeanTheLawn
 */
public class UsefulCode {
    
    /**
     * @param args the command line arguments
    */
    public static void main(String[] args) {
        
        try {
            
            System.out.println(OperatingSystemFunctions.getActiveProcessNamesString()); 
            
        } catch (SecurityException ex) {
            Logger.getLogger(UsefulCode.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println();
            System.out.println("Security Exception");
            System.out.println();
        } catch (IOException ex) {
            Logger.getLogger(UsefulCode.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println();
            System.out.println("IOException");
            System.out.println();
        }
        
    }
    
}
