package de.dirent.tthelper.services;


import java.util.List;

import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.entities.RanglistenAusrichtung;
import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;


public interface PersistenceManager {

	public String getRanglistenAusrichtung( Verein verein );
	public void saveRanglistenAusrichtung( Verein verein, String ranglistenAusrichtung );
	
	
	public void add( PokalMannschaft mannschaft );	
	public void add( RanglistenSpieler spieler );
	
	// remove methods with consistency check
	public void removePokalMannschaft( long id, Verein verein );
	public void removeRanglistenSpieler( long id, Verein verein );
	
	// remove methods without consistency check (e.g. for admin access)
	public void removePokalMannschaft( long id );
	public void removeRanglistenSpieler( long id );
	
	
	public List<PokalMannschaft> getAllPokalMannschaften();
	public List<PokalMannschaft> getPokalMannschaften( Verein verein );
	
	public List<RanglistenSpieler> getAllRanglistenSpieler();
	public List<RanglistenSpieler> getRanglistenSpieler( Verein verein );
	
	public List<RanglistenAusrichtung> getAllRanglistenAusrichtung();
}
