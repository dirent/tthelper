package de.dirent.tthelper.pages;


import org.acegisecurity.annotation.Secured;
import org.apache.tapestry.annotations.InjectPage;

import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.pages.pokalmannschaft.CreatePokalMannschaft;
import de.dirent.tthelper.pages.ranglistenspieler.CreateRanglistenSpieler;



/**
 * Start page of application expense-manager.
 */
@Secured( "ROLE_USER" )
public class Start {
	
	@InjectPage
	private CreatePokalMannschaft createPokalMannschaft;

	@InjectPage
	private CreateRanglistenSpieler createRanglistenSpieler;

	
	Object onActionFromPokalMeldung() {

		return createPokalMannschaft.initialize( Verein.SVG );
	}

	
	Object onActionFromRanglistenMeldung() {

		return createRanglistenSpieler.initialize( Verein.SVG );
	}
}