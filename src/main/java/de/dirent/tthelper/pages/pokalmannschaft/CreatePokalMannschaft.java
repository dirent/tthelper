package de.dirent.tthelper.pages.pokalmannschaft;


import java.util.List;

import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;

import de.dirent.tthelper.model.PokalMannschaft;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.pages.TTHelperPage;


public class CreatePokalMannschaft extends TTHelperPage {

	public List<PokalMannschaft> getGemeldetePokalMannschaften() {
		
		return getPersistenceManager().getPokalMannschaften( Verein.SVG );
	}	
	@Property
	private PokalMannschaft pm;
	
	@Property @Persist
	private PokalMannschaft mannschaft;	
	
	@SetupRender
	public void validate() {
		
		if( this.mannschaft == null ) {
			
			this.mannschaft = new PokalMannschaft();
			this.mannschaft.setVerein( Verein.SVG );
		}
	}
	
	public Object initialize( Verein verein ) {
	
		this.mannschaft = new PokalMannschaft();
		this.mannschaft.setVerein(verein);

		return this;
	}
	
	
	public void onSuccess() {

		getPersistenceManager().add( this.mannschaft );
	}
}
