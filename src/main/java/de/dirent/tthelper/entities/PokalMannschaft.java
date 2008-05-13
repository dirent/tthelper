package de.dirent.tthelper.entities;


import java.util.Date;

import javax.persistence.Entity;

import org.apache.tapestry.beaneditor.NonVisual;
import org.apache.tapestry.beaneditor.Validate;

import de.dirent.tthelper.model.PokalWettbewerb;
import de.dirent.tthelper.model.Verein;


@Entity
public class PokalMannschaft

		extends AbstractEntity implements Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6252188767976858677L;
	
	
	private Date date = new Date();
	
	private Verein verein;
	private String name;
	private PokalWettbewerb wettbewerb;
	private Integer pokalAnzahl = 1;
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
	

	@Validate( "required,maxlength=32")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Validate( "required" )
	public PokalWettbewerb getWettbewerb() {
	
		return wettbewerb;
	}
	public void setWettbewerb(PokalWettbewerb wettbewerb) {
	
		this.wettbewerb = wettbewerb;
	}
	

	@Validate( "min=0,max=3" )
	public Integer getPokalAnzahl() {
		return pokalAnzahl;
	}
	public void setPokalAnzahl(Integer pokalAnzahl) {
		this.pokalAnzahl = pokalAnzahl;
	}
	
	
	@Validate( "maxlength=512" )
	public String getBemerkung() {
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}
	
	public PokalMannschaft clone() {
		
		PokalMannschaft clone = new PokalMannschaft();
		clone.setId( getId() );
		clone.date = date;
		clone.name = name;
		clone.verein = verein;
		clone.pokalAnzahl = pokalAnzahl;
		clone.wettbewerb = wettbewerb;
		clone.bemerkung = bemerkung;
		
		return clone;
	}
}
