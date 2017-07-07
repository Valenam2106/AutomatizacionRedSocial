package com.acenture.redsocial.archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.accenture.redsocial.dto.Credenciales;

public class DataDrivenUsers {

	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	// public XSSFCol
	public XSSFCell cell = null;

	public DataDrivenUsers(String exelfilepath) throws Exception {

		fis = new FileInputStream(exelfilepath);
		workbook = new XSSFWorkbook(fis);
		fis.close();

	}

	public Credenciales getCellData(int rowNum) {
		int col_Num = -1;
		try {
			Credenciales credencial = new Credenciales();

			String sheetName = "Sheet1";

			sheet = workbook.getSheet(sheetName);

			String colName_user = "";
			String colName_pass = "";

			row = sheet.getRow(rowNum + 1);
			colName_user = row.getCell(0).getStringCellValue();
			colName_pass = row.getCell(1).getStringCellValue();

			credencial.setUser(colName_user);
			credencial.setPass(colName_pass);
			return credencial;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setStatus(int rowNum, String xlFilePath, String status) throws Exception{ 
		 		 
		 		String sheetName = "Sheet1"; 
		 		 
		         sheet = workbook.getSheet(sheetName); 
		         row = sheet.getRow(rowNum+1); 
		         cell = row.createCell(2); 
		         cell.setCellValue(status); 
		           
		         FileOutputStream fos = new FileOutputStream(xlFilePath); 
		         workbook.write(fos); 
		         fos.close(); 
		}
	
	public void setPassword(){
		
		String sheetName = "Sheet1"; 
		 
        sheet = workbook.getSheet(sheetName); 
        row = sheet.getRow(1); 
        cell = row.createCell(1); 
        cell.setCellValue("123456789abc"); 
          
        FileOutputStream fos;
		try {
			fos = new FileOutputStream("C://Users//Administrator//workspace//TestRedSocial//Credenciales.xlsx");
			workbook.write(fos); 
	        fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         
	}

	

}
