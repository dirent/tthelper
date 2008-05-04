package de.dirent.tthelper.services;


import java.util.List;

import de.dirent.tthelper.model.PokalMannschaft;
import de.dirent.tthelper.model.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;


public interface PersistenceManager {

	public void add( PokalMannschaft mannschaft );	
	public void add( RanglistenSpieler spieler );
	
	public void removePokalMannschaft( long id );
	public void removeRanglistenSpieler( long id );
	
	
	public List<PokalMannschaft> getPokalMannschaften( Verein verein );
	public List<RanglistenSpieler> getRanglistenSpieler( Verein verein );
}
