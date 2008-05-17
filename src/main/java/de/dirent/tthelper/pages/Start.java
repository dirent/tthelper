package de.dirent.tthelper.pages;


import nu.localhost.tapestry.acegi.services.LogoutService;

import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.ioc.annotations.Inject;

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
	
	
    @Inject
    private LogoutService logoutService;

    public void onActionFromLogout() {
    	
        logoutService.logout();
    }
}