package de.dirent.tthelper.pages;


import nu.localhost.tapestry.acegi.services.LogoutService;

import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;
import org.apache.tapestry.ioc.annotations.Inject;

import de.dirent.tthelper.pages.pokalmannschaft.AdminPokalMannschaft;
import de.dirent.tthelper.pages.pokalmannschaft.CreatePokalMannschaft;
import de.dirent.tthelper.pages.ranglistenspieler.AdminRanglistenAusrichtung;
import de.dirent.tthelper.pages.ranglistenspieler.AdminRanglistenSpieler;
import de.dirent.tthelper.pages.ranglistenspieler.CreateRanglistenSpieler;



/**
 * Start page of secured services
 */
public class StartSecured extends SecuredPage {
	
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
	
	@SetupRender
	public void validate() {
		
		if( !isUserAuthenticated() ) return;

		if( this.ranglistenAusrichtung == null ) {
			
			this.ranglistenAusrichtung = 
				getPersistenceManager().getRanglistenAusrichtung( getCurrentVerein() );
		}
	}

    @Inject
    private LogoutService logoutService;

    public void onActionFromLogout() {
    	
        logoutService.logout();
    }
    
    @Property
    private String ranglistenAusrichtung;
    
    public boolean isRanglistenMeldungAvailable() {
    	
    	String ra = getPersistenceManager().getRanglistenAusrichtung( getCurrentVerein() );
    	
    	return ra != null  &&  ra.length() > 0;
    }
    
    
    public void onSuccessFromAusrichtungsform() {
    	
    	getPersistenceManager().saveRanglistenAusrichtung( getCurrentVerein(), this.ranglistenAusrichtung );
    }
    
    // Adminstration
    
    @InjectPage
    private AdminPokalMannschaft adminPokalMannschaft;

    @InjectPage
    private AdminRanglistenSpieler adminRanglistenSpieler;
    
    @InjectPage
    private AdminRanglistenAusrichtung adminRanglistenAusrichtung;
    

    Object onActionFromAdminPokalMeldung() {

		return adminPokalMannschaft;
	}

    Object onActionFromAdminRanglistenMeldung() {

		return adminRanglistenSpieler;
	}

    Object onActionFromAdminRanglistenAusrichtung() {

		return adminRanglistenAusrichtung;
	}
}