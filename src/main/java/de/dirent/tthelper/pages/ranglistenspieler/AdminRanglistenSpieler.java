package de.dirent.tthelper.pages.ranglistenspieler;


import java.text.Format;
import java.util.List;

import org.acegisecurity.annotation.Secured;
import org.apache.tapestry.annotations.Property;

import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.pages.SecuredPage;
import de.dirent.tthelper.utils.BooleanFormat;


@Secured( "ROLE_ADMIN" )
public class AdminRanglistenSpieler extends SecuredPage {

	@Property
	private RanglistenSpieler rs;

	public List<RanglistenSpieler> getGemeldeteRanglistenSpieler() {
		
		return getPersistenceManager().getAllRanglistenSpieler();
	}	

	
	private Format booleanFormat = new BooleanFormat( "Ja", "Nein" );
	
    public Format getBooleanFormat() {
    	
    	return booleanFormat;
    }
}
