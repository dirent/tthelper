package de.dirent.tthelper.services;


import de.dirent.tthelper.model.FulltextSearchResult;


public interface FulltextIndexer {

	String FULLTEXT_ID_NAME = "id";
	String FULLTEXT_HEADLINE_NAME = "body";
	String FULLTEXT_BODY_NAME = "body";
	
	public boolean fulltextIndexExists();
	
	public void initializeFulltextIndex() throws Exception;
	
	public FulltextSearchResult query( String phrase, int maxResults );
}
