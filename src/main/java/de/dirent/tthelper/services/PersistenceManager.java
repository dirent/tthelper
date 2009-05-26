package de.dirent.tthelper.services;


import java.util.List;

import de.dirent.tthelper.entities.AbstractRanglistenAusrichtung;
import de.dirent.tthelper.entities.Helfer;
import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;


public interface PersistenceManager {

	public String getRanglistenAusrichtung( Verein verein );
	public void saveRanglistenAusrichtung( Verein verein, String ranglistenAusrichtung );
	
	public String getJugendRanglistenAusrichtung( Verein verein );
	public void saveJugendRanglistenAusrichtung( Verein verein, String ranglistenAusrichtung );
	
	
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
	
	public List<AbstractRanglistenAusrichtung> getAllRanglistenAusrichtung();
	
	
	// Helfermeldung
	public void add( Helfer helfer );
	public List<Helfer> getAllHelfer();
}
