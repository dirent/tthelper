package de.dirent.tthelper.model;


import java.util.Date;

import org.apache.tapestry.beaneditor.NonVisual;
import org.apache.tapestry.beaneditor.Validate;


public class RanglistenSpieler {

	private Long id;
	private Date date = new Date();
	
	private Verein verein;
	private String name;
	private Date birthDate;
	private RanglistenKonkurrenz konkurrenz;
	private boolean teilnahmeBezirksrangliste = false;
	private boolean teilnahmeBezirksmeisterschaften = false;
	
	
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
	
	
	@Validate( "required" )
	public RanglistenKonkurrenz getKonkurrenz() {
		return konkurrenz;
	}
	public void setKonkurrenz(RanglistenKonkurrenz konkurrenz) {
		this.konkurrenz = konkurrenz;
	}
	
	
	
	public boolean isTeilnahmeBezirksrangliste() {
		return teilnahmeBezirksrangliste;
	}
	public void setTeilnahmeBezirksrangliste(boolean teilnahmeBezirksrangliste) {
		this.teilnahmeBezirksrangliste = teilnahmeBezirksrangliste;
	}

	
	public boolean isTeilnahmeBezirksmeisterschaften() {
		return teilnahmeBezirksmeisterschaften;
	}
	public void setTeilnahmeBezirksmeisterschaften(
			boolean teilnahmeBezirksmeisterschaften) {
		this.teilnahmeBezirksmeisterschaften = teilnahmeBezirksmeisterschaften;
	}
}
