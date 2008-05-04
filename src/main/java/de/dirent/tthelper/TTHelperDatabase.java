package de.dirent.tthelper;


import java.util.ArrayList;
import java.util.HashMap;
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

		pokalMannschaften.get( pokalMannschaft.getVerein() ).add( pokalMannschaft.clone() );
	}


	public void add( RanglistenSpieler spieler ) {

		checkIntegrity( spieler.getVerein() );
		
		ranglistenSpieler.get( spieler.getVerein() ).add( spieler.clone() );
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
