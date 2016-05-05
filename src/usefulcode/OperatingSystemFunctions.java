/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usefulcode;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *Various reusable methods for checking and manipulating operating system
 *  functions
 * @author SeanTheLawn
 */
public class OperatingSystemFunctions {
    
    
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Windows Functions/////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Launches the specified program.
     * 
     * @param filePath
     *  The path of the program to be launched
     * @throws IOException
     *  If the program fails to be launched
     */
    public static void launchProgram(String filePath)
            throws IOException {
        Runtime.getRuntime().exec(filePath);
    }
    
    /**
     * Opens the specified folder.
     *
     * @param directory
     *  The path of the folder to be opened
     * @throws IOException
     *  If the folder fails to be opened
     */
    public static void openFolder(String directory)
            throws IOException {
        Desktop.getDesktop().open(new File(directory));
    }
    
    /**
     * Opens the specified file.
     *
     * @param directory
     *  The path of the file to be opened, including the file name
     * @throws IOException
     *  If the file fails to be opened
     */
    public static void openFile(String directory)
            throws IOException {
        Desktop.getDesktop().open(new File(directory));
    }
    
    /**
     * Launches the specified application through Valve Corporation's Steam program
     * 
     * @param appID
     *  The ID number of the specified Steam application.
     * @throws URISyntaxException
     *  If an invalid URI path is specified
     * @throws IOException
     *  If the program fails to be launched
     */
    public static void launchSteamApp(String appID)
            throws URISyntaxException, IOException {
        
        URI programURI = new URI("steam://rungameid/" + appID);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(programURI);
        }
    }
    
    /**
     *Generates a list (without header) of processes currently running on the machine.
     * 
     * @return
     *  The list of processes as a single String
     * @throws SecurityException
     *  If the user doesn't have permission to access running processes
     * @throws IOException
     *  If an error occurs with reading the list of processes
     */
    public static String getActiveProcessesString()
            throws SecurityException, IOException {
        String line;
        String processes = "";
        Process p = Runtime.getRuntime().exec
            (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        //Skip header, which is the first 3 lines:
        input.readLine();
        input.readLine();
        input.readLine();
        while ((line = input.readLine()) != null) {
            processes += line + String.format("%n");
        }
        input.close();
        
        return processes;
    }
    
    /**
     *Generates a list of processes currently running on the machine.
     * 
     * @param includeHeader
     *  Allows the user to specify if they want the table header included
     * @return
     *  The list of processes as a single String
     * @throws SecurityException
     *  If the user doesn't have permission to access running processes
     * @throws IOException
     *  If an error occurs with reading the list of processes
     */
    public static String getActiveProcessesString(boolean includeHeader)
            throws SecurityException, IOException{
        
        String line;
        String processes = "";
        Process p = Runtime.getRuntime().exec
            (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        input.readLine(); //First line is always blank so this skips it.
        if(!includeHeader){
            //Skip the header, which is the next 2 lines
            input.readLine();
            input.readLine();
        }
        while ((line = input.readLine()) != null) {
            processes += line + String.format("%n");
        }
        input.close();
        
        return processes;
    }
    
    /**
     *Generates a list of processes currently running on the machine.
     * 
     * @return
     *  The list of processes as an ArrayList of Strings
     * @throws SecurityException
     *  If the user doesn't have permission to access running processes
     * @throws IOException
     *  If an error occurs with reading the list of processes
     */
    public static ArrayList<String> getActiveProcessesStringArray()
            throws SecurityException, IOException{
        String line;
        ArrayList<String> processes = new ArrayList<>();
        Process p = Runtime.getRuntime().exec
            (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        //Skip header, which is the first 3 lines:
        input.readLine();
        input.readLine();
        input.readLine();
        while ((line = input.readLine()) != null) {
            processes.add(line);
        }
        input.close();
        
        return processes;
    }
    
    /**
     *Generates a list of names of processes currently running on the machine.
     * (PLEASE NOTE THAT THIS METHOD IS EXTREMELY FLAWED AND SHOULD BE REVISITED.
     * If a process name has two spaces in a row or the process name is too long,
     * this method will give incorrect output or throw an exception.)
     * 
     * @return
     *  The list of processes as a single String
     * @throws SecurityException
     *  If the user doesn't have permission to access running processes
     * @throws IOException
     *  If an error occurs with reading the list of processes
     */
    public static String getActiveProcessNamesString()
            throws SecurityException, IOException{
        
        String line;
        String processes = "";
        Process p = Runtime.getRuntime().exec
            (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        //First three lines is a header so this skips them.
        input.readLine();
        input.readLine();
        input.readLine();
        while ((line = input.readLine()) != null) {
            processes += line.substring(0, line.indexOf("  ")) + String.format("%n");
        }
        input.close();
        
        return processes;
    }
    
    /**
     *Checks all currently-running processes for specified process
     * 
     * @param targetProcess
     *  The name of the process to be checked for
     * @return
     *  True if target process is currently running; otherwise return false
     * @throws SecurityException
     *  If the user doesn't have permission to access running processes
     * @throws IOException
     *  If an error occurs with reading the list of processes
     */
    public static boolean processIsRunning(String targetProcess)
            throws SecurityException, IOException{
        String line;
        Process p = Runtime.getRuntime().exec
            (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        //Skip header, which is the first 3 lines:
        input.readLine();
        input.readLine();
        input.readLine();
        while ((line = input.readLine()) != null) {
            if(line.startsWith(targetProcess)){
                input.close();
                //Found target process
                return true;
            }
        }
        
        input.close();
        //Checked all processes, target process does not exist
        return false;
    }
    
}
