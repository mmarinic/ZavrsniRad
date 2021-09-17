package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Contact;
import view.GuiWindow;
import view.SqlSettingsWindov;
/**
 * GuiBuilder je klasa koja sluzi za kreiranje MainFramea (GuiWindow) i za akzivaciju svih komponenti na MainFrameu.
 * 
 */
public class GuiBuilder {

	private GuiWindow guiWindow;
	public static TableBuilder tableBuilder;
	private PanelBuilder panelBuilder;
	private VCard vCard;
	private ExcelHandler excelHandler = new ExcelHandler();
	public static HashMap<String, Contact> hashMap = new HashMap();
	private static String tempShort = "";
	private static String tempLong = "";

	public GuiBuilder() {

		createGui();
		activateComponents();

	}

	private void buildTable() {
		tableBuilder = new TableBuilder(guiWindow, hashMap);
	}

	private void createGui() {

		guiWindow = new GuiWindow();

	}

	private void activateComponents() {

		guiWindow.mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				excelHandler.exportToXlsx(hashMap, excelHandler.getCurrentFile());
			}
		});

		guiWindow.mntmSaveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				excelHandler.exportToXlsx(hashMap);

			}
		});

		guiWindow.mntmOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file = excelHandler.getFile();
				if (excelHandler.state) {
					hashMap = excelHandler.getMapData(file);
					tableBuilder = new TableBuilder(guiWindow, hashMap);
				} else {

				}

			}
		});

		guiWindow.btnSqlAddContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					if (guiWindow.textFieldSqlName.getText().equals("")
							|| guiWindow.textFieldSqlSurename.getText().equals("")
							|| guiWindow.textFieldSqlShortNumber.getText().equals("")
							|| guiWindow.textFieldSqlLongNumber.getText().equals("")
							|| guiWindow.textFieldSqlDepartment.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Molimo unesite sva polja!");

					} else {

						String name = guiWindow.textFieldSqlName.getText();
						String surename = guiWindow.textFieldSqlSurename.getText();
						String department = guiWindow.textFieldSqlDepartment.getText();
						String shortNumber = guiWindow.textFieldSqlShortNumber.getText();
						String longNumber = guiWindow.textFieldSqlLongNumber.getText();

						Contact contact = new Contact(name, surename, department, shortNumber, longNumber);

						String query = "INSERT INTO phonebooktable(id, name, surename, shortNumber, longNumber, department) values (?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStatement = PanelBuilder.con.prepareStatement(query);

						preparedStatement.setString(1, null);
						preparedStatement.setString(2, name);
						preparedStatement.setString(3, surename);
						preparedStatement.setString(4, shortNumber);
						preparedStatement.setString(5, longNumber);
						preparedStatement.setString(6, department);
						// databaseHandler.conn.

						if (preparedStatement.execute()) {

							JOptionPane.showMessageDialog(null,
									"Pogreška prilikom dodavanja kontakta u bazu podataka....");

						} else {

							JOptionPane.showMessageDialog(null, "Uspješno dodano u bazu podataka...");
							hashMap.put(longNumber, contact);

							tableBuilder.makeContactsVisible();

						}
						guiWindow.textFieldSqlName.setText("");
						guiWindow.textFieldSqlSurename.setText("");
						guiWindow.textFieldSqlShortNumber.setText("");
						guiWindow.textFieldSqlLongNumber.setText("");
						guiWindow.textFieldSqlDepartment.setText("");

					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}

			}
		});

		guiWindow.mntmSqlPostavke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				panelBuilder = new PanelBuilder(new SqlSettingsWindov(), guiWindow);

			}
		});

		guiWindow.btnSpremi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				excelHandler.exportToXlsx(hashMap);

			}
		});

		guiWindow.btnUkloni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int row = guiWindow.table_1.getSelectedRow();
				String name = guiWindow.table_1.getValueAt(row, 0).toString();
				String surename = guiWindow.table_1.getValueAt(row, 1).toString();
				String shortNumber = guiWindow.table_1.getValueAt(row, 2).toString();
				String longNumber = guiWindow.table_1.getValueAt(row, 3).toString();
				String department = guiWindow.table_1.getValueAt(row, 4).toString();

				Contact contact = new Contact(name, surename, department, shortNumber, longNumber);

				hashMap.remove(longNumber);
				System.out.println("Contact deleted from local databse ----> " + name + " Number: " + longNumber);

				buildTable();
			}
		});

		guiWindow.btnUveziExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				File file = excelHandler.getFile();

				if (excelHandler.state) {
					hashMap = excelHandler.getMapData(file);
					tableBuilder = new TableBuilder(guiWindow, hashMap);
				} else {

				}

			}
		});

		guiWindow.textFieldLongNumber.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (tempLong.equals(guiWindow.textFieldLongNumber.getText())) {

					System.out.println("Text not modified");

				} else {

					System.out.println("Text conent modified");
					if (guiWindow.textFieldLongNumber.getText().startsWith("09")
							|| guiWindow.textFieldLongNumber.getText().startsWith("00385")
							|| guiWindow.textFieldLongNumber.getText().startsWith("+")) {

						System.out.println("Number is entered properly...");

					} else {
						System.out.println("Number not entered properly...");
						System.out.println("Wrong input... Deleting...");
						JOptionPane.showMessageDialog(null, "Dugi broj nije ispravno unešen!");
						guiWindow.textFieldLongNumber.setText("");
					}
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				tempLong = guiWindow.textFieldLongNumber.getText();

			}
		});

		guiWindow.textFieldShortNumber.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (tempShort.equals(guiWindow.textFieldShortNumber.getText())) {

					System.out.println("Text not modified");

				} else {

					System.out.println("Text conent modified");

					if (guiWindow.textFieldShortNumber.getText().matches("[0-9]+")) {

						System.out.println("Number entered properly");
					} else {

						System.out.println("Wrong input... Deleting...");
						JOptionPane.showMessageDialog(null, "Kratki broj nije ispravno unešen!");
						guiWindow.textFieldShortNumber.setText("");
					}

				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				tempShort = guiWindow.textFieldShortNumber.getText();

			}
		});

		guiWindow.btnAddNewContactToJtable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (guiWindow.textFieldName.getText().equals("") || guiWindow.textFieldSurename.getText().equals("")
						|| guiWindow.textFieldShortNumber.getText().equals("")
						|| guiWindow.textFieldLongNumber.getText().equals("")
						|| guiWindow.textFieldDepartment.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Molimo unesite sva polja!");

				} else {

					String name = guiWindow.textFieldName.getText();
					String surename = guiWindow.textFieldSurename.getText();
					String department = guiWindow.textFieldDepartment.getText();
					String shortNumber = guiWindow.textFieldShortNumber.getText();
					String longNumber = guiWindow.textFieldLongNumber.getText();

					Contact contact = new Contact(name, surename, department, shortNumber, longNumber);

					hashMap.put(longNumber, contact);

					tableBuilder.makeContactsVisible();

				}

			}
		});

		guiWindow.btnIzveziZaMobitel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				vCard = new VCard(hashMap);

			}
		});

	}

}
