package de.dirent.tthelper.model;


import java.util.Date;

import org.apache.tapestry.beaneditor.NonVisual;
import org.apache.tapestry.beaneditor.Validate;


public class PokalMannschaft {

	private Long id;
	private Date date = new Date();
	
	private Verein verein;
	private String name;
	private PokalWettbewerb wettbewerb;
	private Integer pokalAnzahl = 1;
	private String bemerkung;
	
	
	
	@NonVisual
	public Long getId() {
	
		return id;
	}
	public void setId(Long id) {
	
		this.id = id;
	}

	
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
}
