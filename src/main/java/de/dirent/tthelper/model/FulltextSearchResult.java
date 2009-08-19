package de.dirent.tthelper.model;


import java.util.List;


public class FulltextSearchResult {

	private String phrase;
	private long totalHits;
	private List<FulltextHit> hits;
	
	
	public FulltextSearchResult( String phrase, 
			long totalHits,
			List<FulltextHit> hits) {

		this.phrase = phrase;
		this.totalHits = totalHits;
		this.hits = hits;
	}


	/**
	 * convenience method
	 */
	public int size() {
		
		return this.hits.size();
	}
	
	
	public String getPhrase() {
	
		return phrase;
	}


	public long getTotalHits() {
	
		return totalHits;
	}


	public List<FulltextHit> getHits() {
	
		return hits;
	}
}
