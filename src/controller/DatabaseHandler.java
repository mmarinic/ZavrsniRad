package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Singleton
 * DatabaseHandler je klasa koja se koristi za spajanje na bazu podataka.
 * 
 * @author Marin
 *
 */

public class DatabaseHandler {

	public static Connection conn;

	public DatabaseHandler() {

	}
	/**
	 * Metoda koja vraca connection a za ulaz traži podatke za pristup bazi podataka.
	 * @param databaseServer
	 * @param databaseName
	 * @param username
	 * @param password
	 * @return
	 */

	public static Connection getConnection(String databaseServer, String databaseName, String username,
			String password) {
		Connection con = null;
		try {

			if (conn == null || conn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://" + databaseServer + "/" + databaseName, username,
						password);
				System.out.println("Connected!!!!");
				JOptionPane.showMessageDialog(null, "Database connected....");
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,
					"Pogreska prilikom spajanja na bazu, Provjerite da li ste tocno unjeli sve podatke....");
		}

		return con;
	}

}
