package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;

import model.Contact;
import view.GuiWindow;

/**
 * Table builder kreira JTable sa svim elementima hashMape koja mu se prosljedi.
 */
public class TableBuilder {

	private GuiWindow window;
	private HashMap<String, Contact> hashMap;

	public TableBuilder(GuiWindow window, HashMap<String, Contact> hashMap) {

		this.window = window;
		this.hashMap = hashMap;

		makeContactsVisible();

	}

	/**
	 * metoda koja kreira JTable od svih objekata koji se nalaze u prosljedenoj
	 * hashMapi.
	 * 
	 * @param hashMap
	 * @return
	 */

	private JTable buildTable(HashMap<String, Contact> hashMap) {

		String columnName[] = { "Ime", "Prezime", "Kratki broj", "Dugi broj", "Odjel" };
		Object[][] data = new Object[hashMap.size()][5];

		int i = 0;
		for (Map.Entry<String, Contact> entry : hashMap.entrySet()) {

			data[i][0] = entry.getValue().getName();
			data[i][1] = entry.getValue().getSurename();
			data[i][2] = entry.getValue().getShortNumber();
			data[i][3] = entry.getValue().getLongNumber();
			data[i][4] = entry.getValue().getDepartment();

			i++;

		}

		JTable table = new JTable(data, columnName);

		return table;

	}

	public void makeContactsVisible() {

		window.table_1 = buildTable(hashMap);
		window.scrollPane.setViewportView(window.table_1);

	}

}
