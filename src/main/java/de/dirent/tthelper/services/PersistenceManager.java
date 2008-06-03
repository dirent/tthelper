package de.dirent.tthelper.services;


import java.util.List;

import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;


public interface PersistenceManager {

	public String getRanglistenAusrichtung( Verein verein );
	public void saveRanglistenAusrichtung( Verein verein, String ranglistenAusrichtung );
	
	
	public void add( PokalMannschaft mannschaft );	
	public void add( RanglistenSpieler spieler );
	
	public void removePokalMannschaft( long id, Verein verein );
	public void removeRanglistenSpieler( long id, Verein verein );
	
	
	public List<PokalMannschaft> getAllPokalMannschaften();
	public List<PokalMannschaft> getPokalMannschaften( Verein verein );
	
	public List<RanglistenSpieler> getAllRanglistenSpieler();
	public List<RanglistenSpieler> getRanglistenSpieler( Verein verein );
}
