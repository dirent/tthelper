package de.dirent.tthelper.entities;


import java.util.Date;

import javax.persistence.Entity;

import org.apache.tapestry.beaneditor.NonVisual;
import org.apache.tapestry.beaneditor.Validate;

import de.dirent.tthelper.model.SweatShirtSize;
import de.dirent.tthelper.model.Verein;


@Entity
public class Helfer 

		extends AbstractEntity implements Cloneable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -471822420963322708L;

	
	private Date date = new Date();
	
	private Verein verein;
	private String name;
	private String anschrift;
	private String telefonnummer;
	private Date birthDate;
	private String email;
	private SweatShirtSize size = SweatShirtSize.XL;
	private boolean kannDo = true;
	private boolean kannFr = true;
	private boolean kannSa = true;
	private boolean kannSo = true;
	
	private String bemerkung;
	
	
	@NonVisual
	public Date getDate() {
	
		return date;
	}
	public void setDate(Date date) {
	
		this.date = date;
	}

	
	@Validate( "required" )
	public Verein getVerein() {
	
		return verein;
	}
	public void setVerein(Verein verein) {
	
		this.verein = verein;
	}
	
	
	@Validate( "required" )
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Validate( "required" )
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	@Validate( "required,maxlength=256" )	
	public String getAnschrift() {
	
		return anschrift;
	}
	public void setAnschrift(String anschrift) {
	
		this.anschrift = anschrift;
	}
	

	@Validate( "required" )
	public String getTelefonnummer() {
	
		return telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
	
		this.telefonnummer = telefonnummer;
	}

	
	@Validate( "maxlength=32" )
	public String getEmail() {
	
		return email;
	}
	public void setEmail(String email) {
	
		this.email = email;
	}

	
	@Validate( "required" )
	public SweatShirtSize getSize() {
	
		return size;
	}	
	public void setSize(SweatShirtSize size) {
	
		this.size = size;
	}

	
	public boolean isKannDo() {
	
		return kannDo;
	}
	
	public void setKannDo(boolean kannDo) {
	
		this.kannDo = kannDo;
	}
	
	public boolean isKannFr() {
	
		return kannFr;
	}
	
	public void setKannFr(boolean kannFr) {
	
		this.kannFr = kannFr;
	}
	
	public boolean isKannSa() {
	
		return kannSa;
	}
	
	public void setKannSa(boolean kannSa) {
	
		this.kannSa = kannSa;
	}
	
	public boolean isKannSo() {
	
		return kannSo;
	}
	
	public void setKannSo(boolean kannSo) {
	
		this.kannSo = kannSo;
	}
	
	
	@Validate( "maxlength=512" )
	public String getBemerkung() {
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	
	public Helfer clone() {
		
		Helfer clone = new Helfer();
		clone.setId( getId() );
		clone.date = date;
		clone.name = name;
		clone.birthDate = birthDate;
		clone.verein = verein;
		clone.anschrift = anschrift;
		clone.telefonnummer = telefonnummer;
		clone.email = email;
		clone.size = size;
		clone.kannDo = kannDo;
		clone.kannFr = kannFr;
		clone.kannSa = kannSa;
		clone.kannSo = kannSo;
		clone.bemerkung = bemerkung;
		
		return clone;
	}
}
