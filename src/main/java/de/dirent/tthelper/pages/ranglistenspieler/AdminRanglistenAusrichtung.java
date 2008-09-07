package de.dirent.tthelper.pages.ranglistenspieler;


import java.util.List;

import org.acegisecurity.annotation.Secured;

import de.dirent.tthelper.entities.RanglistenAusrichtung;
import de.dirent.tthelper.pages.SecuredPage;


@Secured( "ROLE_ADMIN" )
public class AdminRanglistenAusrichtung extends SecuredPage {

	public List<RanglistenAusrichtung> getGemeldeteRanglistenAusrichtung() {
		
		return getPersistenceManager().getAllRanglistenAusrichtung();
	}	
}
