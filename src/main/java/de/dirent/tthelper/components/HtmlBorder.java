package de.dirent.tthelper.components;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

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
}
