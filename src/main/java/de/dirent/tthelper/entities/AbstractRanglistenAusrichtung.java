package de.dirent.tthelper.entities;


import javax.persistence.MappedSuperclass;

import org.apache.tapestry5.beaneditor.Validate;

import de.dirent.tthelper.model.Verein;


@MappedSuperclass
public class AbstractRanglistenAusrichtung extends AbstractEntity {

	private static final long serialVersionUID = 4738803292977826781L;

	
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
