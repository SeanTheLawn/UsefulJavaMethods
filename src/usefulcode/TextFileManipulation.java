/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usefulcode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;

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
     *  if the read file can't be found
    */
    public static String readTextFile(String filePath) throws FileNotFoundException{
        
        //The file to be read
        File readFile = new File(filePath);
        //Initialize Scanner for reading file
        Scanner fileReader = new Scanner(readFile);
        //The String to be returned
        String text = null;

        while(fileReader.hasNextLine()){

            if(text == null){
                //Initialize return String
                text = "";
            }else{
                //Add new linebreak
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
     *  If the read file can't be found
    */
    public static String readLineTextFile(String filePath, int lineNumber) throws FileNotFoundException{
        
        //The file to be read
        File readFile = new File(filePath);
        //Initialize Scanner for reading file
        Scanner fileReader = new Scanner(readFile);
        //The String to be returned
        String line = null;

        for(int i = 0; fileReader.hasNextLine() && i <= lineNumber; i++){
            if(i == lineNumber){
                //Read line
                line = fileReader.nextLine();
            }else{
                //Skip line
                fileReader.nextLine();
            }
        }
        
        fileReader.close();
        return line;
    }
    
    /**
     *Writes a string to a text file
     * 
     * @param filePath
     *  the path of the file to be read, including the filename
     * @param text
     *  the String to be written to the file; can be more than one line.
     * @param overwrite
     *  determines whether the user wants to overwrite the write file if it
     *  already exists. If true, pre-existing file will be overwritten
     * @throws IIOException
     *  if the write file already exists and the user allowed overwriting, but
     *  the file could not be overwritten
     * @throws AccessDeniedException
     *  if the write file already exists but the user didn't allow overwriting
     * @throws IOException
     *  if an error occurs initializing the BufferedWriter
     */
    public static void writeTextFile(String filePath, String text, boolean overwrite)
        throws IIOException, IOException, AccessDeniedException{
        
        //The file to be written
        File writeFile = new File(filePath);
        if(writeFile.exists() && overwrite){
            //If file exists, try to delete it
            if(!writeFile.delete()){
                //If file cannot be deleted, throw OIOException
                throw new IIOException("Could not delete pre-existing file: " +
                    filePath);
            }
        }else if(writeFile.exists() && !overwrite){
            //If file exists but is not allowed to be overwritten, throw AccessDeniedException
            throw new AccessDeniedException(writeFile.getPath());
        }
        
        //Format each linebreak to be displayed correctly in a text file
        String formattedText = text.replaceAll("\n", String.format("%n"));
        //Initialize BufferedWriter to write string to file
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(writeFile));
        //Write the file
        fileWriter.write(formattedText);
        
        fileWriter.close();
    }
    
    /**
     *Adds new text to the end of a text file, starting on a new line.
     * 
     * @param initFilePath
     *  the path of the initial file, including the filename
     * @param newFilePath
     *  the path of the new appended file, including the filename (Can be the
     *  same file)
     * @param newText
     *  the String of text to be added to the original file
     * @throws FileNotFoundException
     *  if the original file can't be found
     * @throws IIOException
     *  if the user chooses to write the original file but the file can't be
     *  overwritten
     * @throws IOException
     *  if an error occurs initializing the BufferedWriter while writing the file
     */
    public static void appendTextFile(String initFilePath, String newFilePath,
            String newText) throws FileNotFoundException,
            IIOException, IOException{
        
        //Read the initial file and append the new text
        String text = readTextFile(initFilePath) + String.format("%n") + newText;
        //Write the new text file with overwrite permissions
        writeTextFile(newFilePath, text, true);
    }
    
    /**
     *Adds new text to the end of a text file, starting on the final line of the
     * original file.
     * 
     * @param initFilePath
     *  the path of the initial file, including the filename
     * @param newFilePath
     *  the path of the new appended file, including the filename (Can be the
     *  same file)
     * @param newText
     *  the String of text to be added to the original file
     * @throws FileNotFoundException
     *  if the original file can't be found
     * @throws IIOException
     *  if the user chooses to write the original file but the file can't be
     *  overwritten
     * @throws IOException
     *  if an error occurs initializing the BufferedWriter while writing the file
     */
    public static void appendTextFileSameLine(String initFilePath, String newFilePath,
            String newText) throws FileNotFoundException,
            IIOException, IOException{
        
        //Read the initial file and append the new text
        String text = readTextFile(initFilePath) + newText;
        //Write the new text file with overwrite permissions
        writeTextFile(newFilePath, text, true);
    }
    
    /**
     *Inserts new text into the middle of a text file at the specified line and
     * character index.
     * 
     * @param initFilePath
     *  the path of the original text file, including the filename
     * @param newFilePath
     *  the path of the edited output text file, including the filename
     * @param newText
     *  the String of text to be added
     * @param lineNumber
     *  the line number where the new text will be inserted (starts at zero)
     * @param charIndex
     *  the character index where the text will be inserted on the specified line
     * @throws FileNotFoundException
     *  if the original file can't be found
     * @throws IOException
     *  if an error occurs initializing the BufferedWriter while writing the file
     */
    public static void insertTextInFile(String initFilePath, String newFilePath,
            String newText, int lineNumber, int charIndex)
            throws FileNotFoundException, IOException{
        
        //The file to be read
        File readFile = new File(initFilePath);
        //Initialize Scanner for reading file
        Scanner fileReader = new Scanner(readFile);
        //The full String of text to be built upon
        String fullTextBuilder = null;
        
        //Read in all text before the injected text
        for(int i = 0; fileReader.hasNextLine() && i < lineNumber; i++){

            if(fullTextBuilder == null){
                //Initialize the builder String
                fullTextBuilder = "";
            }else{
                //Add new linebreak
                fullTextBuilder += String.format("%n");
            }
            
            //Add line to builder String
            fullTextBuilder += fileReader.nextLine();
            
            //If true, the text file is too short
            if(i == lineNumber - 1 && !fileReader.hasNextLine()){
                throw new IndexOutOfBoundsException(
                              "Line index is too large/Text file is too short");
            }
        }
        
        //If user is not inserting text into first line, add a linebreak
        if(fullTextBuilder != null){
            fullTextBuilder += String.format("%n");
        }else{
            fullTextBuilder = "";
        }
        
        //The line where the text insertion occurs
        String line = fileReader.nextLine();
        //Insert the new text into the line
        line = line.substring(0, charIndex) + newText + line.substring(charIndex);
        //Add the edited line to the builder String
        fullTextBuilder += line;
        
        //Read in all text after the addition
        while(fileReader.hasNextLine()){

            if(fullTextBuilder == null){
                //Initialize the builder String if it hasn't been started
                //      (this should only occur if inserting text on the first line)
                fullTextBuilder = "";
            }else{
                //Add new linebreak
                fullTextBuilder += String.format("%n");
            }
            
            //Add line to builder String
            fullTextBuilder += fileReader.nextLine();
        }
        fileReader.close();
        
        //Write the file
        writeTextFile(newFilePath, fullTextBuilder, true);
    }
    
    /**
     *Inserts a new line of text into the middle of a text file at the specified
     * line. Original text at the specified line is pushed down.
     * 
     * @param initFilePath
     *  the path of the original text file, including the filename
     * @param newFilePath
     *  the path of the edited output text file, including the filename
     * @param newText
     *  the String of text to be added
     * @param lineNumber
     *  the line number where the new text will be inserted (starts at zero)
     * @throws FileNotFoundException
     *  if the original file can't be found
     * @throws IOException
     *  if an error occurs initializing the BufferedWriter while writing the file
     */
    public static void insertTextLineInFile(String initFilePath, String newFilePath,
            String newText, int lineNumber)
            throws FileNotFoundException, IOException{
        insertTextInFile(initFilePath, newFilePath,
                (newText + String.format("%n")),
                lineNumber, 0);
    }
}
