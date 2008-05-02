package de.dirent.tthelper.pages.pokalmannschaft;


import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;

import de.dirent.tthelper.model.PokalMannschaft;
import de.dirent.tthelper.model.Verein;


public class CreatePokalMannschaft {

	@Property @Persist
	private PokalMannschaft mannschaft;	
	
	@SetupRender
	public void validate() {
		
		if( this.mannschaft == null ) {
			
			this.mannschaft = new PokalMannschaft();
		}
	}
	
	

	
	public Object initialize( int target, Verein verein ) {
	
		this.mannschaft = new PokalMannschaft();
		this.mannschaft.setVerein(verein);

		return this;
	}
}
