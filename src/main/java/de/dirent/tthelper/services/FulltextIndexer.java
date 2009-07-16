package de.dirent.tthelper.services;


public interface FulltextIndexer {

	public boolean fulltextIndexExists();
	
	public void initializeFulltextIndex() throws Exception;
}
