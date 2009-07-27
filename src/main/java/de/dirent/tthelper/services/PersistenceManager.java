package de.dirent.tthelper.services;


import java.util.List;

import de.dirent.tthelper.entities.Helfer;
import de.dirent.tthelper.entities.JugendRanglistenAusrichtung;
import de.dirent.tthelper.entities.Meldung;
import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.entities.RanglistenAusrichtung;
import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.entities.Termin;
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
	
	public List<RanglistenAusrichtung> getAllRanglistenAusrichtung();
	public List<JugendRanglistenAusrichtung> getAllJugendRanglistenAusrichtung();
	
	
	// Helfermeldung
	public void add( Helfer helfer );
	public List<Helfer> getAllHelfer();
	
	
	// Terminverwaltung
	public void add( Termin termin );
	/**
	 * Gets all Termine with an toDate in the future
	 */
	public List<Termin> getTermine();
	
	// Meldungsverwaltung
	public void add( Meldung meldung );
	/**
	 * Gets the Meldungen of current month
	 */
	public List<Meldung> getMonthlyMeldungen();
}
