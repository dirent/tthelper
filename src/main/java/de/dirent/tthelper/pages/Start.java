package de.dirent.tthelper.pages;


import java.util.Random;

import org.apache.tapestry.annotations.InjectPage;

import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.pages.pokalmannschaft.CreatePokalMannschaft;



/**
 * Start page of application expense-manager.
 */
public class Start {
	
	private final Random random = new Random();

	@InjectPage
	private CreatePokalMannschaft createPokalMannschaft;

	
	protected int getInitialTarget() {
		
		return random.nextInt(10) + 1;
	}


	Object onActionFromPokalMeldung() {

		return createPokalMannschaft.initialize( getInitialTarget(), Verein.SVG );
	}
}