package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Contact;

/**
 * Klasa excellHandler dizajnirana je da služi za importanje i exportanje xlsl
 * datoteka. Prilikom importa podaci iz excel tablica se upisuju u zasebne
 * kontakte i dodaju hashmapu na temelju koje onda TableBuilder radi JTable. Dok
 * kod eksporta uzimaju se lokalni podaci i koristenjem XSSFWorkbook-a exportaju
 * u xlsl datoteku.
 */
public class ExcelHandler {

	public static boolean state;
	private static File file;

	/**
	 * Metoda koja cita podatke iz xlsl datoteke i kreira nove kontakte i dodaje ih
	 * u hashMapu.
	 * 
	 * @param file
	 * @return
	 */

	public static HashMap<String, Contact> getMapData(File file) {

		HashMap<String, Contact> contacts = new HashMap<String, Contact>();
		FileInputStream fileInputStream = null;
		try {

			try {

				fileInputStream = new FileInputStream(file);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Cancell is pressed or file doesn't exist..");
			}

			if (fileInputStream != null) {

				Workbook workBook = new XSSFWorkbook(fileInputStream);
				Sheet sheet = workBook.getSheetAt(0);

				int lastRowNumber = sheet.getLastRowNum();

				for (int i = 0; i <= lastRowNumber; i++) {

					Row row = sheet.getRow(i);

					Cell keyCell = row.getCell(0);
					keyCell.setCellType(CellType.STRING);
					String keyLongNumber = keyCell.getStringCellValue().trim();

					Cell nameCell = row.getCell(1);
					String name = nameCell.getStringCellValue().trim();

					Cell surenameCell = row.getCell(2);
					String surename = surenameCell.getStringCellValue().trim();

					Cell shortNumberCell = row.getCell(3);
					shortNumberCell.setCellType(CellType.STRING);
					String shortNumber = shortNumberCell.getStringCellValue().trim();

					Cell longNumberCell = row.getCell(4);
					longNumberCell.setCellType(CellType.STRING);
					String longNumber = longNumberCell.getStringCellValue().trim();

					Cell departmentCell = row.getCell(5);
					String department = departmentCell.getStringCellValue().trim();

					Contact currentContact = new Contact(name, surename, department, shortNumber, longNumber);

					contacts.put(keyLongNumber, currentContact);

				}

			}

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return contacts;

	}

	public static File getCurrentFile() {

		return file;
	}

	/**
	 * Metoda koja pokrece JFileChooser i vraca file koji se otvara.
	 * 
	 * @return
	 */

	public static File getFile() {

		String filename = null;
		String dir = null;
		String data = null;
		File myObj = null;
		JFileChooser c = new JFileChooser();
		// Demonstrate "Open" dialog:
		int rVal = c.showOpenDialog(new JPanel());
		if (rVal == JFileChooser.APPROVE_OPTION) {
			filename = c.getSelectedFile().getName();
			dir = c.getCurrentDirectory().toString();

			System.out.println(dir + " " + filename);

			myObj = new File(dir + "\\" + filename);
			file = myObj;

			state = true;

		}

		if (rVal == JFileChooser.CANCEL_OPTION) {
			filename = "You pressed cancel";

			state = false;

		}

		return myObj;

	}

	/**
	 * Metoda koja pokrece JFileChoser i vraca file u koji cemo zapisivati podatke,
	 * ukoliko file ne postoji kreira se novi file sa zeljenim imenom.
	 * 
	 * @return
	 */

	public static File getFileForSaving() {

		String filename = null;
		String dir = null;
		String data = null;
		File myObj = null;
		JFileChooser c = new JFileChooser();
		// Demonstrate "Open" dialog:
		int rVal = c.showSaveDialog(new JPanel());
		if (rVal == JFileChooser.APPROVE_OPTION) {
			filename = c.getSelectedFile().getName();
			dir = c.getCurrentDirectory().toString();

			myObj = new File(dir + "\\" + filename + ".xlsx");

			try {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (rVal == JFileChooser.CANCEL_OPTION) {
			filename = "You pressed cancel";

		}

		return myObj;
	}

	/**
	 * Metoda koja iz date hashMape i fila, kreira XSSFWorkbook i exporta podatke iz
	 * hashmape u zadani file.
	 * 
	 * @param hashMap
	 * @param file
	 */

	public static void exportToXlsx(HashMap<String, Contact> hashMap, File file) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Imenik");
		XSSFRow row;
		int i = 0;

		for (Map.Entry<String, Contact> entry : hashMap.entrySet()) {

			row = sheet.createRow(i);

			Cell cellKeyLongNumber = row.createCell(0);
			Cell cellName = row.createCell(1);
			Cell cellSurename = row.createCell(2);
			Cell cellShortNumber = row.createCell(3);
			Cell cellLongNumber = row.createCell(4);
			Cell cellDepartment = row.createCell(5);

			cellKeyLongNumber.setCellValue(entry.getValue().getLongNumber().toString());
			cellName.setCellValue(entry.getValue().getName().toString());
			cellSurename.setCellValue(entry.getValue().getSurename().toString());
			cellShortNumber.setCellValue(entry.getValue().getShortNumber().toString());
			cellLongNumber.setCellValue(entry.getValue().getLongNumber().toString());
			cellDepartment.setCellValue(entry.getValue().getDepartment().toString());

			i++;
		}

		try {

			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			System.out.println("Excel file is created sucessfully...");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void exportToXlsx(HashMap<String, Contact> hashMap) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Imenik");
		XSSFRow row;
		int i = 0;

		for (Map.Entry<String, Contact> entry : hashMap.entrySet()) {

			row = sheet.createRow(i);

			Cell cellKeyLongNumber = row.createCell(0);
			Cell cellName = row.createCell(1);
			Cell cellSurename = row.createCell(2);
			Cell cellShortNumber = row.createCell(3);
			Cell cellLongNumber = row.createCell(4);
			Cell cellDepartment = row.createCell(5);

			cellKeyLongNumber.setCellValue(entry.getValue().getLongNumber().toString());
			cellName.setCellValue(entry.getValue().getName().toString());
			cellSurename.setCellValue(entry.getValue().getSurename().toString());
			cellShortNumber.setCellValue(entry.getValue().getShortNumber().toString());
			cellLongNumber.setCellValue(entry.getValue().getLongNumber().toString());
			cellDepartment.setCellValue(entry.getValue().getDepartment().toString());

			i++;
		}

		try {

			FileOutputStream out = new FileOutputStream(getFileForSaving());
			workbook.write(out);
			out.close();
			System.out.println("Excel file is created sucessfully...");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
