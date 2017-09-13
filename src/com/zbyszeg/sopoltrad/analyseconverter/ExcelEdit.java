package com.zbyszeg.sopoltrad.analyseconverter;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelEdit {
	private String newFile;

	public void setNewFile(String newFile) {
		this.newFile = newFile;
	}

	public void editor() {
		try {
			FileInputStream file = new FileInputStream(new File(newFile));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet analyse = workbook.getSheetAt(0);
			XSSFFont font = workbook.createFont();
			
			font.setBold(true);
			font.setColor(new XSSFColor(new Color(51,51,102)));
			font.setFontHeight(12);
			

			CellStyle style = workbook.createCellStyle();
			CellStyle bold = workbook.createCellStyle();
			
			style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
			bold.setFont(font);			

			Cell cell = null;
			
			for (Row row : analyse) {
				cell = row.getCell(5);
				if(cell!=null)
					cell.setCellStyle(style);
			}
			
			for (Row row : analyse) {
				cell = row.getCell(1);
				if(cell!=null)
					cell.setCellStyle(bold);
			}
			
			for (int i=2; i<=6; i++) {
				cell = analyse.getRow(3).getCell(i);
				cell.setCellStyle(bold);
			}
			
			for (int i = 1; i <= 6; i++)
				analyse.autoSizeColumn(i);
			
			XSSFFormulaEvaluator.evaluateAllFormulaCells((XSSFWorkbook) workbook);

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(newFile));
			workbook.write(outFile);
			outFile.close();
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
