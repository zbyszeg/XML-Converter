package com.zbyszeg.sopoltrad.analyseconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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
			
			analyse.autoSizeColumn(1);
			analyse.autoSizeColumn(2);
			analyse.autoSizeColumn(3);
			analyse.autoSizeColumn(4);
			analyse.autoSizeColumn(5);
			analyse.autoSizeColumn(6);

			XSSFFormulaEvaluator.evaluateAllFormulaCells((XSSFWorkbook) workbook);

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(newFile));
			workbook.write(outFile);
			outFile.close();
			workbook.close();

		} catch (Exception e) {

		}
	}
}
