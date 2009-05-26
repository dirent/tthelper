package de.dirent.tthelper.pages;


import nu.localhost.tapestry.acegi.services.LogoutService;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

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

		if( this.jugendRanglistenAusrichtung == null ) {
			
			this.jugendRanglistenAusrichtung = 
				getPersistenceManager().getJugendRanglistenAusrichtung( getCurrentVerein() );
		}
	}

    @Inject
    private LogoutService logoutService;

    public void onActionFromLogout() {
    	
        logoutService.logout();
    }
    
    @Property
    private String ranglistenAusrichtung;
    
    @Property
    private String jugendRanglistenAusrichtung;
    
    public boolean isRanglistenMeldungAvailable() {
    	
    	String ra = getPersistenceManager().getRanglistenAusrichtung( getCurrentVerein() );
    	String jra = getPersistenceManager().getJugendRanglistenAusrichtung( getCurrentVerein() );
    	return (ra != null  &&  ra.length() > 0) ||  (jra != null  &&  jra.length() > 0);
    }
    
    
    @CommitAfter
    public void onSuccessFromAusrichtungsform() {
    	
    	if( this.ranglistenAusrichtung != null  &&  this.ranglistenAusrichtung.length() > 0 ) {
    		getPersistenceManager().saveRanglistenAusrichtung( getCurrentVerein(), this.ranglistenAusrichtung );
    	}
    	
    	if( this.jugendRanglistenAusrichtung != null  &&  this.jugendRanglistenAusrichtung.length() > 0 ) {
    		getPersistenceManager().saveJugendRanglistenAusrichtung( getCurrentVerein(), this.jugendRanglistenAusrichtung );
    	}
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