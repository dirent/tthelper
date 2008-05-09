package de.dirent.tthelper.pages;


import org.apache.tapestry.annotations.InjectPage;

import de.dirent.tthelper.pages.pokalmannschaft.CreatePokalMannschaft;
import de.dirent.tthelper.pages.ranglistenspieler.CreateRanglistenSpieler;



/**
 * Start page of application expense-manager.
 */
public class Start extends TTHelperPage {
	
	@InjectPage
	private CreatePokalMannschaft createPokalMannschaft;

	@InjectPage
	private CreateRanglistenSpieler createRanglistenSpieler;

	
	Object onActionFromPokalMeldung() {

		return createPokalMannschaft.initialize( getCurrentVerein() );
	}

	
	Object onActionFromRanglistenMeldung() {

		return createRanglistenSpieler.initialize( getCurrentVerein() );
	}
}