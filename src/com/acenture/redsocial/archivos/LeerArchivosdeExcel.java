package com.acenture.redsocial.archivos;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.helpers.HSSFRowShifter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.accenture.redsocial.dto.Credenciales;

public class LeerArchivosdeExcel {

	public LeerArchivosdeExcel(File fileName) {
		List cellData = new ArrayList();
				

		try {
			// un FileInputStream obtiene bytes de entrada desde un archivo
			// de sistema de archivo

			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

			// XSSFSheet son las estruturas centrales dentro de un libro y
			// son donde un usuario hace la mayor parte de la hoja de calculo

			// obtiene hoja con el nombre dado mayuculas y minusculas
			XSSFSheet hssfSheet = workbook.getSheetAt(0);

			// Asi obtenemos la cantidad de filas que contenga la hoja de excel

			Iterator rowIterator = hssfSheet.rowIterator();

			
			
			// Creamos un while para movernos dentro de lo registros la cantidad
			// de filas que hay
			// el hasNext develve verdadero si la iteraccion tiene mas elementos

			while (rowIterator.hasNext()) {

				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				// Almacenamos los datos en el iterator

				Iterator iterator = hssfRow.cellIterator();
				List cellTemp = new ArrayList();

				while (iterator.hasNext()) {

					// Almacenamos los datos de cada celda en e hssCell
					XSSFCell hssfCell = (XSSFCell) iterator.next();
					// Los datos en el hssfCell los almacenamos en el cellTemp
					cellTemp.add(hssfCell);

				}
				cellData.add(cellTemp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		obtener(cellData);
	}

	private void obtener(List cellDataList) {

		Credenciales cre = new Credenciales();
		
		for (int i = 1; i < cellDataList.size(); i++) {

			cre.setNumRow(cellDataList.size());
			List cellTempList = (List) cellDataList.get(i);

			for (int j = 0; j < cellTempList.size(); j++) {
				XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);

				String stringCellValue = hssfCell.toString();
				
				cre.setUser(stringCellValue);
				System.out.println(stringCellValue + "");
			}

			System.out.println();
		}

	}

	public static void main(String[] args) {
		File f = new File("C:/Users/Administrator/workspace/TestRedSocial/Credenciales.xlsx");
		
		if(f.exists()){
			
			LeerArchivosdeExcel obj = new LeerArchivosdeExcel(f);
			
		}
	}

}
