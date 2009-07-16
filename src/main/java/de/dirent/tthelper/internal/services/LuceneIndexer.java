package de.dirent.tthelper.internal.services;


import java.io.File;

import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.tapestry5.services.ApplicationGlobals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dirent.tthelper.services.FulltextIndexer;
import de.dirent.tthelper.services.PersistenceManager;


/**
 * Service implementation for indexing and searching fulltext contents
 * 
 * @author dirk
 */
public class LuceneIndexer implements FulltextIndexer {

	protected static final Logger logger = LoggerFactory.getLogger( LuceneIndexer.class );
	
	private File indexRoot;

	
	public LuceneIndexer( ApplicationGlobals globals, PersistenceManager persistenceManager ) {
		
		File tempDir = 
			(File) globals.getServletContext().getAttribute( "javax.servlet.context.tempdir" );
		
		indexRoot = new File( tempDir, "index" );
	}
	
	public boolean fulltextIndexExists() {
		
		return this.indexRoot.exists();
	}
	
	public void initializeFulltextIndex() throws Exception {
		
		long millis = System.currentTimeMillis();

		try {
        
			IndexWriter writer = null;

			if( !indexRoot.exists() ) {
				
				indexRoot.mkdir();
	
				writer = new IndexWriter( indexRoot, 
						new GermanAnalyzer(), 
						true, 
						MaxFieldLength.UNLIMITED );
				
				// TODO: initially index db contents
				
			} else {
			
				writer = new IndexWriter( indexRoot, 
						new GermanAnalyzer(), 
						false, 
						MaxFieldLength.UNLIMITED );
			}
			
			writer.optimize();
			writer.close();

		} finally {
			logger.info( "Initializing fulltext index needed " + (System.currentTimeMillis()-millis) + "ms." );
		}
	}
}
