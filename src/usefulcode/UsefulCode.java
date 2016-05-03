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
        
        String write = "okay testing\nline 2" + String.format("%n") + "line 3 lol";
        
        try {
            TextFileManipulation.insertTextLineInFile("S:\\Users\\SeanTheLawn\\Desktop\\Programming Projects\\test files\\test.txt",
                    "S:\\Users\\SeanTheLawn\\Desktop\\Programming Projects\\test files\\test4.txt",
                    "---", 0);
            //TextFileManipulation.writeTextFile("S:\\Users\\SeanTheLawn\\Desktop\\test3.txt", "testing", true);
        } catch (Exception ex) {
            Logger.getLogger(UsefulCode.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception");
        }
        
    }
    
}
