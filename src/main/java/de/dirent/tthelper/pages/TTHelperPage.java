package de.dirent.tthelper.pages;


import org.apache.tapestry.ioc.annotations.Inject;

import de.dirent.tthelper.services.PersistenceManager;


public class TTHelperPage {

	@Inject
	private PersistenceManager persistenceManager;

	protected PersistenceManager getPersistenceManager() {
		
		return persistenceManager;
	}
}
