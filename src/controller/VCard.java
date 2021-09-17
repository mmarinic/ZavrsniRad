package controller;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import model.Contact;
/**
 * Klasa VCard služi za kreiranje mobilne verzije imenika.
 * Za svaki Kontakt koji se nalazi u hashMap u vcf datoteku se zapisuje Ime, prezime, kratki broj, dugi broj i odjel kontakta.
 * Nakon toga se kreira vcf datoteka.
 */
public class VCard {

	private HashMap<String, Contact> hashMap = new HashMap();
	private File file;
	private FileOutputStream fop;
	private BufferedReader br;

	public VCard(HashMap<String, Contact> hashMap) {

		this.hashMap = hashMap;

		try {
			createVCard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Metoda koja kreira i zapisuje u vcf datoteku.
	 * @throws IOException
	 */

	private void createVCard() throws IOException {

		file = new File("contacts.vcf");
		fop = new FileOutputStream(file);

		if (file.exists()) {

			for (Map.Entry<String, Contact> entry : hashMap.entrySet()) {

				String str = "BEGIN:VCARD\n" + "VERSION:3.0\n" + "FN:" + entry.getValue().getName() + " "
						+ entry.getValue().getSurename() + "\n" + "TEL;TYPE=FAX,WORK;VALUE=text:"
						+ entry.getValue().getShortNumber() + "\n" + "TEL;TYPE=VOICE,CELL;VALUE=text:"
						+ entry.getValue().getLongNumber() + "\n" + "ORG:"+ entry.getValue().getDepartment() +"\n" + "END:VCARD\n";
				fop.write(str.getBytes());

			}

			br = null;
			String sCurrentLine;
			br = new BufferedReader(new FileReader("contacts.vcf"));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
			// close the output stream and buffer reader
			fop.flush();
			fop.close();
			System.out.println("The data has been written");
			JOptionPane.showMessageDialog(null, "Imenik je kreiran.");

		} else
			System.out.println("This file does not exist");
	}

}
