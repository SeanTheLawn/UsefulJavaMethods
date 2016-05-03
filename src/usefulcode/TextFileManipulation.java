/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usefulcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SeanTheLawn
 */
public class TextFileManipulation {
    
    /**
    *   Reads a text file and converts the entire file to a String
    * 
     * @param filePath
     *  the path of the file to be read, including the filename
     * @return
     *  the full text file as a String. Returns null if file is empty
     * @throws java.io.FileNotFoundException
     *  if file can't be found
    */
    public static String readTextFile(String filePath) throws FileNotFoundException{
        
        //The file to be read
        File readFile = new File(filePath);
        Scanner fileReader;
        //The String to be returned
        String text = null;
        //Initialize Scanner
        fileReader = new Scanner(readFile);

        while(fileReader.hasNextLine()){

            if(text == null){
                //Initialize return String
                text = "";
            }else{
                //Add new line
                text += String.format("%n");
            }
            
            //Add line to text
            text += fileReader.nextLine();
        }
        
        fileReader.close();
        return text;
    }
    
    /**
    *   Reads a text file and converts a specific line to a String
    * 
     * @param filePath
     *  the path of the file to be read, including the filename
     * @param lineNumber
     *  the number of the line to be read, starting at zero
     * @return
     *  the line as a String. Returns null if file is empty or contains fewer
     *  lines than lineNumber's value
     * @throws java.io.FileNotFoundException
     *  If file can't be found
    */
    public static String readLineTextFile(String filePath, int lineNumber) throws FileNotFoundException{
        
        //The file to be read
        File readFile = new File(filePath);
        Scanner fileReader;
        //The String to be returned
        String line = null;
        //Initialize Scanner
        fileReader = new Scanner(readFile);

        for(int i = 0; fileReader.hasNextLine() && i < lineNumber; i++){
            //Skip line
            fileReader.nextLine();
        }
        
        //Read line
        line = fileReader.nextLine();
        fileReader.close();
        return line;
    }
    
    
}
