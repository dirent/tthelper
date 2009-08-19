package de.dirent.tthelper.internal.services;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.tapestry5.services.ApplicationGlobals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dirent.tthelper.entities.AbstractEntity;
import de.dirent.tthelper.entities.Meldung;
import de.dirent.tthelper.entities.Termin;
import de.dirent.tthelper.model.FulltextHit;
import de.dirent.tthelper.model.FulltextSearchResult;
import de.dirent.tthelper.services.FulltextIndexer;
import de.dirent.tthelper.services.PersistenceManager;


/**
 * Service implementation for indexing and searching fulltext contents
 * 
 * @author dirk
 */
public class LuceneIndexer implements FulltextIndexer {

	protected static final Logger logger = LoggerFactory.getLogger( LuceneIndexer.class );
	
	final File indexRoot;
	final PersistenceManager persistenceManager;
	
	public LuceneIndexer( ApplicationGlobals globals, PersistenceManager persistenceManager ) {
		
		File tempDir = 
			(File) globals.getServletContext().getAttribute( "javax.servlet.context.tempdir" );
		
		indexRoot = new File( tempDir, "index" );
		
		this.persistenceManager = persistenceManager;
	}
	
	public boolean fulltextIndexExists() {
		
		return this.indexRoot.exists();
	}
	
	
	public void initializeFulltextIndex() throws Exception {
		
		long millis = System.currentTimeMillis();

		IndexWriter writer = null;
		int documentCount = 0;

		if( !indexRoot.exists() ) {
			
			indexRoot.mkdir();

			writer = new IndexWriter( indexRoot, 
					new GermanAnalyzer(), 
					true, 
					MaxFieldLength.UNLIMITED );
			
			int firstResult = 0;
			int maxResults = 100;

			
			// index Termin instances in blocks a 100				
			List<Termin> termine = new ArrayList<Termin>();
			
			do {
				
				termine = persistenceManager.getAllTermine( firstResult, maxResults );
				firstResult += termine.size();
				
				for( Termin termin : termine ) {
				
			        Document doc = new Document();

			        doc.add( new Field( FULLTEXT_ID_NAME, createFulltextId(termin), Field.Store.YES, Field.Index.ANALYZED ) );
			        doc.add( new Field( FULLTEXT_BODY_NAME, termin.getBody(), Field.Store.YES, Field.Index.ANALYZED ) );

			        writer.addDocument( doc );
			        documentCount++;
				}
				
			} while( termine == null  ||  termine.isEmpty() );
			

		
			// index Meldung instances in blocks a 100				
			List<Meldung> meldungen = new ArrayList<Meldung>();
			firstResult = 0;
			
			do {
				
				meldungen = persistenceManager.getAllMeldungen( firstResult, maxResults );
				firstResult += meldungen.size();
				
				for( Meldung meldung : meldungen ) {
				
			        Document doc = new Document();

			        doc.add( new Field( FULLTEXT_ID_NAME, createFulltextId(meldung), Field.Store.YES, Field.Index.ANALYZED ) );
			        doc.add( new Field( FULLTEXT_HEADLINE_NAME, meldung.getHeadline(), Field.Store.YES, Field.Index.ANALYZED ) );
			        doc.add( new Field( FULLTEXT_BODY_NAME, meldung.getBody(), Field.Store.YES, Field.Index.ANALYZED ) );

			        writer.addDocument( doc );
			        documentCount++;
				}
				
			} while( termine == null  ||  termine.isEmpty() );
			
		} else {
		
			writer = new IndexWriter( indexRoot, 
					new GermanAnalyzer(), 
					false, 
					MaxFieldLength.UNLIMITED );
		}
		
		writer.optimize();
		writer.close();

		logger.info( "Initializing fulltext index with " + documentCount + " documents needed " + (System.currentTimeMillis()-millis) + "ms." );
	}
	
	
	public FulltextSearchResult query( String phrase, int maxResults ) {
		
        long millis = System.currentTimeMillis();
		List<FulltextHit> fulltextHits = new ArrayList<FulltextHit>();
        long totalHits = 0;
        
        try {
        	
            Directory fsDir = FSDirectory.getDirectory( this.indexRoot );
            IndexSearcher is = new IndexSearcher(fsDir);
            String queryString = phrase;
            Query query = new MultiFieldQueryParser( 
            		new String[] { FULLTEXT_HEADLINE_NAME, FULLTEXT_BODY_NAME }, new GermanAnalyzer() ).parse( queryString );
                        
            TopDocs topDocs = is.search( query, maxResults );
            
            for( int i=0; i<topDocs.scoreDocs.length; i++ ) {
            
            	fulltextHits.add( new FulltextHit( is.doc( topDocs.scoreDocs[i].doc ) ) );
            }
            
            totalHits = topDocs.totalHits;
            
        } catch( Exception e ) {
            
            logger.error( "Could not query: " + e.getMessage() );
            
        } finally {
        	
        	logger.info( "Fulltext query with phrase '" + phrase + "' needed " + (System.currentTimeMillis()-millis)  + "ms." );
        }
        
        return new FulltextSearchResult( phrase, totalHits, fulltextHits );
	}
	
	
	protected String createFulltextId( AbstractEntity entity ) {
		
		return entity.getClass().getName() + entity.getId();
	}
}
