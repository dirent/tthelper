package de.dirent.tthelper.pages.ranglistenspieler;


import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;

import de.dirent.tthelper.model.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;


public class CreateRanglistenSpieler {

	@Property @Persist
	private RanglistenSpieler spieler;	
	
	@SetupRender
	public void validate() {
		
		if( this.spieler == null ) {
			
			this.spieler = new RanglistenSpieler();
		}
	}
	
	

	
	public Object initialize( Verein verein ) {
	
		this.spieler = new RanglistenSpieler();
		this.spieler.setVerein(verein);

		return this;
	}
}
