package de.dirent.tthelper.model;

import org.apache.lucene.document.Document;

import de.dirent.tthelper.services.FulltextIndexer;


public class FulltextHit {

	private String id;
	private String fulltextAbstract;
	
	public FulltextHit( Document doc ) {
		
		this.id = doc.get( FulltextIndexer.FULLTEXT_ID_NAME );
		this.fulltextAbstract = doc.get( FulltextIndexer.FULLTEXT_BODY_NAME );
	}
	
	public String getId() {
		
		return this.id;
	}
	
	public String getAbstract() {
		
		return this.fulltextAbstract;
	}
}
