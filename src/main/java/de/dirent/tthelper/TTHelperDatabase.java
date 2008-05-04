package de.dirent.tthelper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.dirent.tthelper.model.PokalMannschaft;
import de.dirent.tthelper.model.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.services.PersistenceManager;


/**
 *  Acts like an in-memory database of survey information.
 *
 */ 
public class TTHelperDatabase implements PersistenceManager {

	
	private Map<Verein, List<PokalMannschaft>> pokalMannschaften = 
		new HashMap<Verein, List<PokalMannschaft>>();

	private Map<Verein, List<RanglistenSpieler>> ranglistenSpieler =
		new HashMap<Verein, List<RanglistenSpieler>>();
		
	
	protected void checkIntegrity( Verein verein ) {
		
		if( !pokalMannschaften.containsKey( verein ) ) {
			
			pokalMannschaften.put( verein, new ArrayList<PokalMannschaft>() );
		}

		if( !ranglistenSpieler.containsKey( verein ) ) {
			
			ranglistenSpieler.put( verein, new ArrayList<RanglistenSpieler>() );
		}
	}
	
	
	public void add( PokalMannschaft pokalMannschaft ) {

		checkIntegrity( pokalMannschaft.getVerein() );

		pokalMannschaft.setId( System.currentTimeMillis() );
		pokalMannschaften.get( pokalMannschaft.getVerein() ).add( pokalMannschaft.clone() );
	}

	public void removePokalMannschaft( long id ) {
	
		Iterator<List<PokalMannschaft>> listen = pokalMannschaften.values().iterator();   
		while( listen.hasNext() ) {
			
			List<PokalMannschaft> toBeRemoved = new ArrayList<PokalMannschaft>();
			List<PokalMannschaft> candidates = listen.next();
			
			for( PokalMannschaft pm : candidates ) {
				
				if( pm.getId() == id ) toBeRemoved.add(pm);
			}
			
			candidates.removeAll( toBeRemoved );
		}
	}
	
	
	public void add( RanglistenSpieler spieler ) {

		checkIntegrity( spieler.getVerein() );
		
		spieler.setId( System.currentTimeMillis() );
		ranglistenSpieler.get( spieler.getVerein() ).add( spieler.clone() );
	}
	
	public void removeRanglistenSpieler( long id ) {
		
		Iterator<List<RanglistenSpieler>> listen = ranglistenSpieler.values().iterator();   
		while( listen.hasNext() ) {
			
			List<RanglistenSpieler> toBeRemoved = new ArrayList<RanglistenSpieler>();
			List<RanglistenSpieler> candidates = listen.next();
			for( RanglistenSpieler rs : candidates ) {
				
				if( rs.getId() == id ) toBeRemoved.add(rs);
			}
			
			candidates.removeAll( toBeRemoved );
		}
	}
	
	public List<PokalMannschaft> getPokalMannschaften( Verein verein ) {
		
		checkIntegrity( verein );
		
		return pokalMannschaften.get( verein );
	}
	
	public List<RanglistenSpieler> getRanglistenSpieler( Verein verein ) {
		
		checkIntegrity( verein );
		
		return ranglistenSpieler.get( verein );
	}
}
