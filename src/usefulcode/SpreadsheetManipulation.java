/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usefulcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




/**
 *
 * @author SeanTheLawn
 */
public class SpreadsheetManipulation {
    
    
    /**
     *Reads a sheet from an Excel 2007 (or later) spreadsheet, formatted as:
     *  array[row][column]  
     *
     * @param filePath
     *  The path of the file to be read, including the filename
     * @param sheetIndex
     *  The index of the sheet in the workbook (starting at zero)
     * @return
     *  The sheet as a two-dimensional String array
     * @throws IOException
     *  If the file can't be read
     */
    public static String[][] readExcel07SheetAsArray(String filePath, int sheetIndex)
                    throws IOException{
        
        XSSFWorkbook workbook; //XSSFWorkbooks are for Excel2007 or later files (.xlsx)
        XSSFSheet sheet;
        
        workbook = new XSSFWorkbook(new FileInputStream(new File(filePath))); 
        sheet = workbook.getSheetAt(sheetIndex);
        
        int maxRowLength = 0;
        
        for(int i = 0; i <= sheet.getLastRowNum(); i++){
            if(sheet.getRow(i) != null && sheet.getRow(i).getLastCellNum() > maxRowLength){
                maxRowLength = sheet.getRow(i).getLastCellNum();
                System.out.println("Row " + i + " is length: " + sheet.getRow(i).getLastCellNum());
            }
        }
        
        String spreadsheet[][] = new String[sheet.getLastRowNum() + 1][maxRowLength];
        
        for(int r = 0; r <= sheet.getLastRowNum(); r++){
            if(sheet.getRow(r) != null){
                for(int c = 0; c < maxRowLength; c++){
                    if(sheet.getRow(r).getCell(c) != null){
                        spreadsheet[r][c] = "" + sheet.getRow(r).getCell(c);
                        System.out.println("Row " + r + ", cell " + c + ": " + sheet.getRow(r).getCell(c));
                    }else{
                        spreadsheet[r][c] = null;
                        System.out.println("Row " + r + ", cell " + c + ": null");
                    }
                }
            }else{
                for(int c = 0; c < maxRowLength; c++){
                    spreadsheet[r][c] = null;
                    System.out.println("Row " + r + ", cell " + c + ": null");
                }
            }
        }
        
        return spreadsheet;
    }
    
    /**
     *Reads a sheet from an Excel 2003 (or earlier) spreadsheet, formatted as:
     *  array[row][column]
     *
     * @param filePath
     *  The path of the file to be read, including the filename
     * @param sheetIndex
     *  The index of the sheet in the workbook (starting at zero)
     * @return
     *  The sheet as a two-dimensional String array
     * @throws IOException
     *  If the file can't be read
     */
    public static String[][] readExcel03SheetAsArray(String filePath, int sheetIndex)
                    throws IOException{
        
        HSSFWorkbook workbook; //HSSFWorkbooks are for Excel2003 or earlier files (.xls)
        HSSFSheet sheet;
        
        workbook = new HSSFWorkbook(new FileInputStream(new File(filePath))); 
        sheet = workbook.getSheetAt(sheetIndex);
        
        int maxRowLength = 0;
        
        for(int i = 0; i <= sheet.getLastRowNum(); i++){
            if(sheet.getRow(i) != null && sheet.getRow(i).getLastCellNum() > maxRowLength){
                maxRowLength = sheet.getRow(i).getLastCellNum();
                System.out.println("Row " + i + " is length: " + sheet.getRow(i).getLastCellNum());
            }
        }
        
        String spreadsheet[][] = new String[sheet.getLastRowNum() + 1][maxRowLength];
        
        for(int r = 0; r <= sheet.getLastRowNum(); r++){
            if(sheet.getRow(r) != null){
                for(int c = 0; c < maxRowLength; c++){
                    if(sheet.getRow(r).getCell(c) != null){
                        spreadsheet[r][c] = "" + sheet.getRow(r).getCell(c);
                        System.out.println("Row " + r + ", cell " + c + ": " + sheet.getRow(r).getCell(c));
                    }else{
                        spreadsheet[r][c] = null;
                        System.out.println("Row " + r + ", cell " + c + ": null");
                    }
                }
            }else{
                for(int c = 0; c < maxRowLength; c++){
                    spreadsheet[r][c] = null;
                    System.out.println("Row " + r + ", cell " + c + ": null");
                }
            }
        }
        
        return spreadsheet;
    }
    
    /**
     *Reads an entire Excel 2007 (or later) workbook, formatted as:
     *  array[sheet][row][column]  
     *
     * @param filePath
     *  The path of the file to be read, including the filename
     * @return
     *  The sheet as a three-dimensional String array
     * @throws IOException
     *  If the file can't be read
     */
    public static String[][][] readExcel07WorkbookAsArray(String filePath)
                    throws IOException{
        
        XSSFWorkbook workbook; //XSSFWorkbooks are for Excel2007 or later files (.xlsx)
        XSSFSheet sheet;
        
        workbook = new XSSFWorkbook(new FileInputStream(new File(filePath))); 
        
        int maxRowCount = 0;
        int maxRowLength = 0;
        
        for(int j = 0; j < workbook.getNumberOfSheets(); j++){
            sheet = workbook.getSheetAt(j);
            for(int i = 0; i <= sheet.getLastRowNum(); i++){
                if(sheet.getLastRowNum() > maxRowCount){
                    maxRowCount = sheet.getLastRowNum();
                }
                if(sheet.getRow(i) != null && sheet.getRow(i).getLastCellNum() > maxRowLength){
                    maxRowLength = sheet.getRow(i).getLastCellNum();
                    System.out.println("Sheet " + j + ":   Row " + i + " is length: " + sheet.getRow(i).getLastCellNum());
                }
            }
        }
        
        String spreadsheet[][][] = new String[workbook.getNumberOfSheets()][maxRowCount + 1][maxRowLength];
        
        for(int s = 0; s < workbook.getNumberOfSheets(); s++){
            for(int r = 0; r <= maxRowCount; r++){
                if(workbook.getSheetAt(s).getRow(r) != null){
                    for(int c = 0; c < maxRowLength; c++){
                        if(workbook.getSheetAt(s).getRow(r).getCell(c) != null){
                            spreadsheet[s][r][c] = "" + workbook.getSheetAt(s).getRow(r).getCell(c);
                            System.out.println("Row " + r + ", cell " + c + ": " + workbook.getSheetAt(s).getRow(r).getCell(c));
                        }else{
                            spreadsheet[s][r][c] = null;
                            System.out.println("Row " + r + ", cell " + c + ": null");
                        }
                    }
                }else{
                    for(int c = 0; c < maxRowLength; c++){
                        spreadsheet[s][r][c] = null;
                        System.out.println("Row " + r + ", cell " + c + ": null");
                    }
                }
            }
        }
        
        return spreadsheet;
    }
    
    /**
     *Reads an entire Excel 2003 (or earlier) workbook, formatted as:
     *  array[sheet][row][column]  
     *
     * @param filePath
     *  The path of the file to be read, including the filename
     * @return
     *  The sheet as a three-dimensional String array
     * @throws IOException
     *  If the file can't be read
     */
    public static String[][][] readExcel03WorkbookAsArray(String filePath)
                    throws IOException{
        
        HSSFWorkbook workbook; //XSSFWorkbooks are for Excel2007 or later files (.xls)
        HSSFSheet sheet;
        
        workbook = new HSSFWorkbook(new FileInputStream(new File(filePath))); 
        
        int maxRowCount = 0;
        int maxRowLength = 0;
        
        for(int j = 0; j < workbook.getNumberOfSheets(); j++){
            sheet = workbook.getSheetAt(j);
            for(int i = 0; i <= sheet.getLastRowNum(); i++){
                if(sheet.getLastRowNum() > maxRowCount){
                    maxRowCount = sheet.getLastRowNum();
                }
                if(sheet.getRow(i) != null && sheet.getRow(i).getLastCellNum() > maxRowLength){
                    maxRowLength = sheet.getRow(i).getLastCellNum();
                    System.out.println("Sheet " + j + ":   Row " + i + " is length: " + sheet.getRow(i).getLastCellNum());
                }
            }
        }
        
        String spreadsheet[][][] = new String[workbook.getNumberOfSheets()][maxRowCount + 1][maxRowLength];
        
        for(int s = 0; s < workbook.getNumberOfSheets(); s++){
            for(int r = 0; r <= maxRowCount; r++){
                if(workbook.getSheetAt(s).getRow(r) != null){
                    for(int c = 0; c < maxRowLength; c++){
                        if(workbook.getSheetAt(s).getRow(r).getCell(c) != null){
                            spreadsheet[s][r][c] = "" + workbook.getSheetAt(s).getRow(r).getCell(c);
                            System.out.println("Row " + r + ", cell " + c + ": " + workbook.getSheetAt(s).getRow(r).getCell(c));
                        }else{
                            spreadsheet[s][r][c] = null;
                            System.out.println("Row " + r + ", cell " + c + ": null");
                        }
                    }
                }else{
                    for(int c = 0; c < maxRowLength; c++){
                        spreadsheet[s][r][c] = null;
                        System.out.println("Row " + r + ", cell " + c + ": null");
                    }
                }
            }
        }
        
        return spreadsheet;
    }
}
