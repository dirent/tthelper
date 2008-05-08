package de.dirent.tthelper.pages;


import org.acegisecurity.annotation.Secured;
import org.apache.tapestry.ioc.annotations.Inject;

import de.dirent.tthelper.services.PersistenceManager;


@Secured( "ROLE_USER" )
public class TTHelperPage {

	@Inject
	private PersistenceManager persistenceManager;

	protected PersistenceManager getPersistenceManager() {
		
		return persistenceManager;
	}
}
