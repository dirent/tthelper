package de.dirent.tthelper.services;

import java.util.List;

import de.dirent.tthelper.model.FulltextHit;


public interface FulltextIndexer {

	String FULLTEXT_ID_NAME = "id";
	String FULLTEXT_HEADLINE_NAME = "body";
	String FULLTEXT_BODY_NAME = "body";
	
	public boolean fulltextIndexExists();
	
	public void initializeFulltextIndex() throws Exception;
	
	public List<FulltextHit> query( String phrase, int maxResults );
}
