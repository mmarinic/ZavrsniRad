package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Contact;
/**
 * GuiWindow je glavni prozor aplikacije.
 * 
 * Kada se kreira objekt ove klase dobijemo prozor koji se sastoji od sljedecih elemenata: 
 * 1. Menu bar sa opcijama file i options. File nudi otvaranje novog xlsl fila, a spremi i spremi kao kreiraju novi xlsl file s obzirom na lokalne podatke.
 * 2. JTable u kojeg se mogu ucitavati podatci iz xlsl fila ili iz baze podataka. Takoder kontakti se mogu u tablicu dodavati i kroz mainframe.
 * 3. TabbedPane koji se sastoji od dva panela, excel i sql. Excel je dostupan svakom korisniku dok sql postaje vidljiv tek kada se ostvari konekcija na bazu.
 *    Paneli sluze da se ovisno o nacinu mogu dodavati kontakti u tablicu i (ili) dodavati u bazu podataka.
 * 4. Panel za izvoz lokalnih podataka u vcf datoteku. Odnosno izvoz lokanih podataka u mobilnu verziju imenika.
 * 5. Podnozje framea koje se sastoji od botuna Spremi kao i ukloni. Spremi kao sprema lokalne podatke u xlsl file, botun ukloni briše odabrani kontakt.
 */
public class GuiWindow {

	private JFrame frame;
	public JTextField textFieldSqlName;
	public JTextField textFieldSqlSurename;
	public JTextField textFieldSqlDepartment;
	public JTextField textFieldSqlShortNumber;
	public JTextField textFieldSqlLongNumber;
	public JTextField textFieldDepartment;
	public JTextField textFieldLongNumber;
	public JTextField textFieldShortNumber;
	public JTextField textFieldSurename;
	public JTextField textFieldName;
	public JTabbedPane tabbedPane;
	public JPanel panelSQL;
	public JTable table_1;
	public JScrollPane scrollPane;
	public JButton btnAddNewContactToJtable;
	public JButton btnIzveziZaMobitel;
	public JButton btnUveziExcel;
	public JButton btnUkloni;
	public JButton btnSpremi;
	public JMenuItem mntmSqlPostavke;
	public JButton btnSqlAddContact;
	public JMenuItem mntmOpen;
	public JMenuItem mntmSave;
	public JMenuItem mntmSaveAs;

	
	public GuiWindow() {
		
		initialize();
		frame.setVisible(true);
	}
	

	/**
	 * Inicializiranje komponenti framea.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 722, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Phone Book Converter");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 190, 327);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelExcel = new JPanel();
		panelExcel.setLayout(null);
		panelExcel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("EXCEL\r\n", null, panelExcel, null);
		
		btnUveziExcel = new JButton("U\u010Ditaj EXCEL datoteku");
		btnUveziExcel.setBounds(10, 34, 167, 23);
		panelExcel.add(btnUveziExcel);
		
		JLabel lblUveziteImenikIz_1 = new JLabel("U\u010Ditaj imenik");
		lblUveziteImenikIz_1.setBounds(10, 11, 167, 14);
		panelExcel.add(lblUveziteImenikIz_1);
		
		JSeparator separator_3_2 = new JSeparator();
		separator_3_2.setBounds(10, 23, 167, 2);
		panelExcel.add(separator_3_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 107, 165, 181);
		panelExcel.add(panel_2);
		panel_2.setLayout(null);
		
		btnAddNewContactToJtable = new JButton("Dodaj kontakt u imenik");
		btnAddNewContactToJtable.setBounds(5, 145, 155, 23);
		panel_2.add(btnAddNewContactToJtable);
		
		textFieldDepartment = new JTextField();
		textFieldDepartment.setColumns(10);
		textFieldDepartment.setBounds(5, 114, 86, 20);
		panel_2.add(textFieldDepartment);
		
		JLabel lblRadnoMjesto_2 = new JLabel("Odjel");
		lblRadnoMjesto_2.setBounds(101, 117, 66, 14);
		panel_2.add(lblRadnoMjesto_2);
		
		JLabel lblDugiBroj_2 = new JLabel("Dugi broj");
		lblDugiBroj_2.setBounds(101, 90, 66, 14);
		panel_2.add(lblDugiBroj_2);
		
		textFieldLongNumber = new JTextField();
		textFieldLongNumber.setColumns(10);
		textFieldLongNumber.setBounds(5, 87, 86, 20);
		panel_2.add(textFieldLongNumber);
		
		textFieldShortNumber = new JTextField();
		textFieldShortNumber.setColumns(10);
		textFieldShortNumber.setBounds(5, 61, 86, 20);
		panel_2.add(textFieldShortNumber);
		
		JLabel lblKratkiBroj_2 = new JLabel("Kratki broj");
		lblKratkiBroj_2.setBounds(101, 64, 64, 14);
		panel_2.add(lblKratkiBroj_2);
		
		JLabel lblPrezime_2 = new JLabel("Prezime");
		lblPrezime_2.setBounds(101, 39, 54, 14);
		panel_2.add(lblPrezime_2);
		
		textFieldSurename = new JTextField();
		textFieldSurename.setColumns(10);
		textFieldSurename.setBounds(5, 36, 86, 20);
		panel_2.add(textFieldSurename);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(5, 11, 86, 20);
		panel_2.add(textFieldName);
		
		JLabel lblIme_2 = new JLabel("Ime");
		lblIme_2.setBounds(101, 14, 52, 14);
		panel_2.add(lblIme_2);
		
		panelSQL = new JPanel();
		panelSQL.setLayout(null);
		panelSQL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
	//	tabbedPane.addTab("SQL", null, panelSQL, null);

		
		textFieldSqlName = new JTextField();
		textFieldSqlName.setColumns(10);
		textFieldSqlName.setBounds(17, 47, 86, 20);
		panelSQL.add(textFieldSqlName);
		
		JLabel lblIme_1 = new JLabel("Ime");
		lblIme_1.setBounds(113, 50, 66, 14);
		panelSQL.add(lblIme_1);
		
		JLabel lblPrezime_1 = new JLabel("Prezime");
		lblPrezime_1.setBounds(113, 75, 66, 14);
		panelSQL.add(lblPrezime_1);
		
		textFieldSqlSurename = new JTextField();
		textFieldSqlSurename.setColumns(10);
		textFieldSqlSurename.setBounds(17, 72, 86, 20);
		panelSQL.add(textFieldSqlSurename);
		
		textFieldSqlDepartment = new JTextField();
		textFieldSqlDepartment.setColumns(10);
		textFieldSqlDepartment.setBounds(17, 150, 86, 20);
		panelSQL.add(textFieldSqlDepartment);
		
		textFieldSqlShortNumber = new JTextField();
		textFieldSqlShortNumber.setColumns(10);
		textFieldSqlShortNumber.setBounds(17, 97, 86, 20);
		panelSQL.add(textFieldSqlShortNumber);
		
		textFieldSqlLongNumber = new JTextField();
		textFieldSqlLongNumber.setColumns(10);
		textFieldSqlLongNumber.setBounds(17, 123, 86, 20);
		panelSQL.add(textFieldSqlLongNumber);
		
		JLabel lblRadnoMjesto_1 = new JLabel("Radno mjesto");
		lblRadnoMjesto_1.setBounds(113, 153, 66, 14);
		panelSQL.add(lblRadnoMjesto_1);
		
		JLabel lblKratkiBroj_1 = new JLabel("Kratki broj");
		lblKratkiBroj_1.setBounds(113, 100, 66, 14);
		panelSQL.add(lblKratkiBroj_1);
		
		JLabel lblDugiBroj_1 = new JLabel("Dugi broj");
		lblDugiBroj_1.setBounds(113, 126, 66, 14);
		panelSQL.add(lblDugiBroj_1);
		
		JSeparator separator_6_1 = new JSeparator();
		separator_6_1.setBounds(17, 299, 164, 2);
		panelSQL.add(separator_6_1);
		
		JButton btnDodajKontaktU_1 = new JButton("Dodaj kontakt u SQL");
		btnDodajKontaktU_1.setBounds(17, 305, 153, 23);
		panelSQL.add(btnDodajKontaktU_1);
		
		JButton btnNewButton_1 = new JButton("Dodaj kontakt u imenik");
		btnNewButton_1.setBounds(17, 370, 153, 23);
		panelSQL.add(btnNewButton_1);
		
		btnSqlAddContact = new JButton("Dodaj kontakt");
		btnSqlAddContact.setBounds(38, 204, 120, 23);
		panelSQL.add(btnSqlAddContact);
		
		btnSpremi = new JButton("Spremi kao");
		btnSpremi.setBounds(560, 501, 105, 23);
		frame.getContentPane().add(btnSpremi);
		
		btnUkloni = new JButton("Ukloni");
		btnUkloni.setBounds(456, 501, 74, 23);
		frame.getContentPane().add(btnUkloni);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(9, 415, 191, 75);
		frame.getContentPane().add(panel);
		
		JLabel lblPretvoriUImenika = new JLabel("Kreiraj imenik za mobitel");
		lblPretvoriUImenika.setBounds(10, 11, 167, 14);
		panel.add(lblPretvoriUImenika);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setBounds(10, 23, 151, 2);
		panel.add(separator_3_1);
		
		btnIzveziZaMobitel = new JButton("Izvezi");
		btnIzveziZaMobitel.setBounds(50, 36, 67, 23);
		panel.add(btnIzveziZaMobitel);
		
		JLabel lblV_1 = new JLabel("v. 3.0");
		lblV_1.setBounds(131, 40, 46, 14);
		panel.add(lblV_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(210, 26, 486, 465);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);

		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Otvori");
		mnFile.add(mntmOpen);
		
		mntmSave = new JMenuItem("Spremi");
		mnFile.add(mntmSave);
		
		mntmSaveAs = new JMenuItem("Spremi kao");
		mnFile.add(mntmSaveAs);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		mntmSqlPostavke = new JMenuItem("SQL postavke");
		mnOptions.add(mntmSqlPostavke);
	}
}
