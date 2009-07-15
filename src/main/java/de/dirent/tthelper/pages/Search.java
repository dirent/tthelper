package de.dirent.tthelper.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;


public class Search extends TTHelperPage {

	@Inject
	private Logger logger;

	public void startSearch( String phrase ) {
		
		logger.info( "initializing fulltext search for " + phrase );
	}
}