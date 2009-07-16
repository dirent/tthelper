package de.dirent.tthelper.internal.services;


import org.apache.tapestry5.services.ApplicationInitializer;
import org.apache.tapestry5.services.ApplicationInitializerFilter;
import org.apache.tapestry5.services.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dirent.tthelper.services.FulltextIndexer;


/**
 * Initializes the fulltext index on startup
 *  
 * @author dirk
 */
public class LuceneIndexInitializer implements ApplicationInitializerFilter {

	protected static final Logger logger = LoggerFactory.getLogger( LuceneIndexInitializer.class );

	
	FulltextIndexer indexer;
	
	public LuceneIndexInitializer( FulltextIndexer indexer ) {
	
		this.indexer = indexer;
	}
	
	
	public void initializeApplication( Context context, 
			ApplicationInitializer applicationInitializer ) {

		try {

			if( !this.indexer.fulltextIndexExists() ) {
				
				this.indexer.initializeFulltextIndex();
			}

		} catch( Exception e ) {
	
			logger.error( "Could not initialize database for fulltext index: " + e.getMessage() );
		}

        applicationInitializer.initializeApplication(context);
	}
}
