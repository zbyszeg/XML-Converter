package com.zbyszeg.sopoltrad.analyseconverter;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.*;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.put("OptionPane.cancelButtonText", "Anuluj");
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

		String filebody = "";
		String all = "";
		String location = args[0];
		String c = "\\";
		String name, filename, newName = null;

		int condition = 0;

		Parser parser = new Parser();
		Progress window = new Progress();

		while (condition != 1) {
			window.setVisible(false);
			name = (String) JOptionPane.showInputDialog(null, "WprowadŸ nazwê pliku XML z analiz¹:",
					"Sopoltrad Analyse Converter", JOptionPane.QUESTION_MESSAGE, null, null, "analyse.xml");

			if (name == null)
				System.exit(1);

			if (!name.contains(".xml") && !name.contains(".XML"))
				name += ".xml";

			filename = location + c + name;

			parser.setFileName(filename);

			int dot = name.indexOf('.');
			newName = name.substring(0, dot);

			window.setVisible(true);

			parser.parse();

			filebody = parser.getFileBody();
			all += "Words:\n" + parser.getAll();
			condition = parser.getCondition();
		}
		StringBuilder _filebody = new StringBuilder(filebody);
		_filebody.insert(0, all + "\n\n");

		int condition2 = 0;

		while (condition2 != 1) {
			try {
				PrintWriter save = new PrintWriter("Analyse");
				save.print(_filebody);
				save.close();

				String vb = "wscript C:\\SopoltradStudio\\macro.vbs " + "\"" + location + "\" " + newName;

				try {
					Runtime.getRuntime().exec(vb);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "System napotka³ problem przy uruchamianiu skrytpu VBS.",
							"B³¹d wykonania skryptu", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}

				window.setVisible(false);
				condition2 = 1;

				JOptionPane.showMessageDialog(null, "Zapisano plik\n'" + newName + ".xlsx'", "Zakoñczono pomyœlnie",
						JOptionPane.INFORMATION_MESSAGE);

				File file = new File("Analyse");
				file.delete();

				try {
					FileInputStream file2 = new FileInputStream(new File(newName + ".xlsx"));

					XSSFWorkbook workbook = new XSSFWorkbook(file2);
					XSSFSheet analyse = workbook.getSheetAt(0);
					analyse.autoSizeColumn(1);
					analyse.autoSizeColumn(2);
					analyse.autoSizeColumn(3);
					analyse.autoSizeColumn(4);
					analyse.autoSizeColumn(5);
					analyse.autoSizeColumn(6);

					XSSFFormulaEvaluator.evaluateAllFormulaCells((XSSFWorkbook) workbook);

					file2.close();

					FileOutputStream outFile = new FileOutputStream(new File(newName + ".xlsx"));
					workbook.write(outFile);
					outFile.close();
					workbook.close();

				} catch (Exception e) {

				}

				System.exit(0);
			} catch (FileNotFoundException e) {
				int response = JOptionPane.showConfirmDialog(null,
						"Prawdopodobnie plik jest otwarty. Spróbuj zamkn¹æ plik i kliknij OK.",
						"Problem z dostêpem do pliku Analyse", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (response == JOptionPane.CANCEL_OPTION)
					System.exit(1);

			}
		}
	}
}
