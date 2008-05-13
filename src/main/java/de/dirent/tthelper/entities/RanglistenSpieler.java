package de.dirent.tthelper.entities;


import java.util.Date;

import javax.persistence.Entity;

import org.apache.tapestry.beaneditor.NonVisual;
import org.apache.tapestry.beaneditor.Validate;

import de.dirent.tthelper.model.RanglistenKonkurrenz;
import de.dirent.tthelper.model.Verein;


@Entity
public class RanglistenSpieler 

		extends AbstractEntity implements Cloneable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -471822420963322708L;

	
	private Date date = new Date();
	
	private Verein verein;
	private String name;
	private Date birthDate;
	private RanglistenKonkurrenz konkurrenz;
	private boolean teilnahmeBezirksrangliste = false;
	private boolean teilnahmeBezirksmeisterschaften = false;
	
	
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
	
	
	public RanglistenSpieler clone() {
		
		RanglistenSpieler clone = new RanglistenSpieler();
		clone.setId( getId() );
		clone.date = date;
		clone.name = name;
		clone.birthDate = birthDate;
		clone.verein = verein;
		clone.konkurrenz = konkurrenz;
		clone.teilnahmeBezirksmeisterschaften = teilnahmeBezirksmeisterschaften;
		clone.teilnahmeBezirksrangliste = teilnahmeBezirksrangliste;
		
		return clone;
	}
}
