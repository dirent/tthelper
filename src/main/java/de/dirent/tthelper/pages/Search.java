package de.dirent.tthelper.pages;


import java.util.ArrayList;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import de.dirent.tthelper.entities.Meldung;
import de.dirent.tthelper.entities.Termin;
import de.dirent.tthelper.model.FulltextHit;
import de.dirent.tthelper.model.FulltextSearchResult;
import de.dirent.tthelper.services.FulltextIndexer;


public class Search extends TTHelperPage {

	@Inject
	private Logger logger;

	@Inject 
	private FulltextIndexer indexer;
	
	
	@Property
	private FulltextHit fulltextHit;
	
	@Property @Persist
	private FulltextSearchResult result;
	
	@SetupRender
	public void validate() {
		
		if( this.result == null ) {
			
			this.result = new FulltextSearchResult( null, 0, new ArrayList<FulltextHit>() );
		}
	}
	
	public long getTotalHits() {
		
		return this.result.getTotalHits();
	}
	
	public String getEntity() {
		
		if( this.fulltextHit != null ) {
			
			String id = this.fulltextHit.getId();
			
			if( id.startsWith( Termin.class.getName() ) ) return "Termin";

			if( id.startsWith( Meldung.class.getName() ) ) return "Meldung";
		}
		
		return "unbekannt";
	}
	
	public void startSearch( String phrase ) {
		
		logger.info( "initializing fulltext search for " + phrase );
		
		this.result = indexer.query( phrase, 25 );
		
		logger.info( "Found " + result.size()  + " hits." );
	}
}