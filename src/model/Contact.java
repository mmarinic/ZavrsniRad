package model;
/**
 * Contact je klasa koja predstavlja kontakte iz imenika.
 * Svaki kontakt ima ime, prezime, kratki broj(Sastoji se od 4 znamenke i predstavlja poslovni broj svakog kontakta), dugi broj(broj telefona) i odjel u kojem radi.
 * @author Marin
 *
 */
public class Contact {
	
	private String name;
	private String surename;
	private String department;
	private String shortNumber;
	private String longNumber;
	private int id;
	

	public Contact(String name, String surename, String department, String shortNumber, String longNumber, int id) {
		
		this.name = name;
		this.surename = surename;
		this.department = department;
		this.shortNumber = shortNumber;
		this.longNumber = longNumber;
		this.id = id;
	}
	/**
	 * Konstruktor koji postavlja ime, prezime, kratki broj, dugi broj i odjel sukladno unosu.
	 * @param name
	 * @param surename
	 * @param department
	 * @param shortNumber
	 * @param longNumber
	 */
	public Contact(String name, String surename, String department, String shortNumber, String longNumber) {
		
		this.name = name;
		this.surename = surename;
		this.department = department;
		this.shortNumber = shortNumber;
		this.longNumber = longNumber;
		
	}


	/**
	 * Getteri i setteri
	 */
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurename() {
		return surename;
	}


	public void setSurename(String surename) {
		this.surename = surename;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getShortNumber() {
		return shortNumber;
	}


	public void setShortNumber(String shortNumber) {
		this.shortNumber = shortNumber;
	}


	public String getLongNumber() {
		return longNumber;
	}


	public void setLongNumber(String longNumber) {
		this.longNumber = longNumber;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
