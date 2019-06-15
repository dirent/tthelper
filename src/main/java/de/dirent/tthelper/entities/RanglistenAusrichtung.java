package de.dirent.tthelper.entities;


import javax.persistence.Entity;

import org.apache.tapestry5.beaneditor.Validate;

import de.dirent.tthelper.model.Verein;


@Entity
public class RanglistenAusrichtung extends AbstractEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6147179730960014084L;
	
	
	private Verein verein;
	private String description;
	
	
	public Verein getVerein() {
	
		return verein;
	}
	public void setVerein(Verein verein) {
	
		this.verein = verein;
	}

	@Validate( "required" )
	public String getDescription() {
		
		return description;
	}
	public void setDescription( String description ) {
		
		this.description = description;
	}
}
