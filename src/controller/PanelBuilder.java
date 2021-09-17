package controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Contact;
import view.GuiWindow;
import view.SqlSettingsWindov;
/**
 * PanelBuilder je klasa koji za danu klasu iz viewa paketa kreira njen objekt i na zadane botune dodaje actionListenere. U projektu sam imao potrebu za samo jednim takvim objektom, odnosno
 * jednim novim prozorom. Ali na ovaj nacin kreiranje novih prozora bi bilo lakše za realizirati i preglednije za daljnji razvoj.
 */
public class PanelBuilder {

	private SqlSettingsWindov sqlSettingsWindov;
	private GuiWindow guiWindow;
	public static Connection con;

	public PanelBuilder(SqlSettingsWindov sqlSettingsWindov, GuiWindow guiWindow) {

		this.sqlSettingsWindov = sqlSettingsWindov;
		this.guiWindow = guiWindow;

		showSqlPanel();
		activateSqlComponents();

	}

	private void showSqlPanel() {

		sqlSettingsWindov.setVisible(true);

	}

	/**
	 * Metoda koja na btnConnect dodaje actionListener koji kada se aktivira izvlaci
	 * podatke sa textFildova i pridodaje ih zadanim stringovima. Metodom .isEmpty()
	 * provjerava sve svaki string i ukoliko je iti jedan false dobija se poruka da
	 * nisu uneseni svi podaci. nakon cega se pokušava ostvariti konekciju na bazu i
	 * poziva se buildTable().
	 */

	private void activateSqlComponents() {

		sqlSettingsWindov.btnConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {

					String databaseServer = sqlSettingsWindov.textFieldDatabaseServer.getText();
					String databaseName = sqlSettingsWindov.textFieldDatabaseName.getText();
					String username = sqlSettingsWindov.textFieldUsername.getText();
					String password = sqlSettingsWindov.passwordField.getText();

					if (databaseServer.isEmpty() || databaseName.isEmpty() || username.isEmpty()
							|| password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nisu uneseni svi podaci...");

					} else {

						con = DatabaseHandler.getConnection(databaseServer, databaseName, username, password);

						if (con != null) {
							sqlSettingsWindov.dispose();
							buildTable();
							// tabbedPane.addTab("SQL", null, panelSQL, null);
							guiWindow.tabbedPane.addTab("SQL", null, guiWindow.panelSQL, null);
						}

					}

				} catch (Exception e2) {
					// TODO: handle exception

					System.out.println("Unjeli ste krive podatke....");
				}

			}
		});

	}

	/**
	 * Metoda buildTable iz baze podataka povlaci podatke i sprema ih u hashMapu s
	 * kojom se onda kreira tableBuilder();
	 */

	private void buildTable() {

		String query = "SELECT * FROM phonebooktable";
		GuiBuilder.hashMap = new HashMap<String, Contact>();
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {

				String name = resultSet.getString("name");
				String surename = resultSet.getString("surename");
				String shortNumber = resultSet.getString("shortNumber");
				String longNumber = resultSet.getString("longNumber");
				String department = resultSet.getString("department");

				Contact contact = new Contact(name, surename, department, shortNumber, longNumber);
				GuiBuilder.hashMap.put(longNumber, contact);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error getting sql data....");
		}

		GuiBuilder.tableBuilder = new TableBuilder(guiWindow, GuiBuilder.hashMap);
	}

}
