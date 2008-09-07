package de.dirent.tthelper.pages;


import org.apache.tapestry.annotations.InjectPage;

import de.dirent.tthelper.pages.helfer.CreateHelfer;



/**
 * Start page of unsecured (anonymous) services
 */
public class Start extends TTHelperPage {
	
	@InjectPage
	private CreateHelfer createHelfer;

	
	Object onActionFromHelferMeldung() {

		return createHelfer;
	}
}