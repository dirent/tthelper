package de.dirent.tthelper.pages.termin;


import org.acegisecurity.annotation.Secured;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import de.dirent.tthelper.entities.Termin;
import de.dirent.tthelper.pages.TTHelperPage;


@Secured( "ROLE_AUTHOR" )
public class CreateTermin extends TTHelperPage {

	@Property
	private Termin termin;
	

	@SetupRender
	public void validate() {
		
		if( this.termin == null ) {
			
			this.termin = new Termin();
		}
	}

	@CommitAfter
	public Object onSuccessFromTermin() {

		getPersistenceManager().add( this.termin );
		return "Termine";
	}
}