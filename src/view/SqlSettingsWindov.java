package view;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
/**
 * SqlSettingsWindow je klasa iz koje se kreira objekt kada se pristisne na botun za sql postavke.
 * 
 * Na zadana polja se unose kljucni atributi za ostvarivanje konekcije na bazu podataka.
 */

public class SqlSettingsWindov extends JFrame {

	private JPanel contentPane;
	public JTextField textFieldDatabaseName;
	public JTextField textFieldUsername;
	public JPasswordField passwordField;
	public JTextField textFieldDatabaseServer;
	public JButton btnConnect;

	public SqlSettingsWindov() {

		initialize();

	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		getContentPane().setLayout(null);
		setSize(575, 340);
		contentPane.setLayout(null);

		JLabel lblDatabaseName = new JLabel("Database Name:");
		lblDatabaseName.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblDatabaseName.setBounds(10, 81, 165, 29);
		getContentPane().add(lblDatabaseName);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblUsername.setBounds(10, 138, 165, 29);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblPassword.setBounds(10, 193, 165, 29);
		getContentPane().add(lblPassword);

		JLabel lblDatabaseServer = new JLabel("Database Server:");
		lblDatabaseServer.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblDatabaseServer.setBounds(10, 26, 176, 29);
		getContentPane().add(lblDatabaseServer);

		textFieldDatabaseServer = new JTextField();
		textFieldDatabaseServer.setFont(new Font("Unispace", Font.PLAIN, 18));
		textFieldDatabaseServer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDatabaseServer.setBounds(196, 29, 354, 29);
		getContentPane().add(textFieldDatabaseServer);
		textFieldDatabaseServer.setColumns(10);

		textFieldDatabaseName = new JTextField();
		textFieldDatabaseName.setFont(new Font("Unispace", Font.PLAIN, 18));
		textFieldDatabaseName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDatabaseName.setColumns(10);
		textFieldDatabaseName.setBounds(196, 84, 354, 29);
		getContentPane().add(textFieldDatabaseName);

		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Unispace", Font.PLAIN, 18));
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(196, 141, 354, 29);
		getContentPane().add(textFieldUsername);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Unispace", Font.PLAIN, 18));
		passwordField.setBounds(196, 193, 354, 29);
		getContentPane().add(passwordField);
		
		btnConnect = new JButton("Spoji se");
		btnConnect.setFont(new Font("Unispace", Font.BOLD, 22));
		btnConnect.setBounds(196, 233, 354, 57);
		contentPane.add(btnConnect);

	}
}
