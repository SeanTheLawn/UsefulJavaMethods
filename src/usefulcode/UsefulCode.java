/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usefulcode;

import java.io.FileNotFoundException;
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
            System.out.print(TextFileManipulation.readLineTextFile("S:\\Users\\SeanTheLawn\\Desktop\\test.txt", 2));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsefulCode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
