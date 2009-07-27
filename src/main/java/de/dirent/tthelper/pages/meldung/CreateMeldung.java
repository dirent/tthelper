package de.dirent.tthelper.pages.meldung;


import org.acegisecurity.annotation.Secured;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import de.dirent.tthelper.entities.Meldung;
import de.dirent.tthelper.pages.TTHelperPage;


@Secured( "ROLE_AUTHOR" )
public class CreateMeldung extends TTHelperPage {

	@Property
	private Meldung meldung;
	

	@SetupRender
	public void validate() {
		
		if( this.meldung == null ) {
			
			this.meldung= new Meldung();
		}
	}

	@CommitAfter
	public Object onSuccessFromMeldung() {

		getPersistenceManager().add( this.meldung );
		return "Start";
	}
}