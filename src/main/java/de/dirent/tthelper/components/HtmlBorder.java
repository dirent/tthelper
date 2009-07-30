package de.dirent.tthelper.components;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationGlobals;

import de.dirent.tthelper.pages.Search;


public class HtmlBorder {

	@Property @SessionState( create=false )
	private String phrase;
		
	
	@InjectPage
	private Search searchPage;
	
    public Object onSubmitFromSearchForm() {
    	
    	System.out.println( "received search request with phrase " + this.phrase );
    	
    	this.searchPage.startSearch( this.phrase.toString() );
    	
    	return this.searchPage;
    }
    
    @Inject
    private ApplicationGlobals globals;
    
    public String getLastPublishedDate() {
    	
		DateFormat df = new SimpleDateFormat( "dd. MMMM yyyy HH:mm 'Uhr'",  Locale.GERMAN );
		
		File tempDir = 
			(File) globals.getServletContext().getAttribute( "javax.servlet.context.tempdir" );

		// first approximation
		Date lastPublished = new Date( tempDir.lastModified() );
		
		// TODO: check the db for some newer changes
		
		return df.format( lastPublished );
    }
}
