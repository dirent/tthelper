package de.dirent.tthelper;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dirent.tthelper.entities.Helfer;
import de.dirent.tthelper.entities.JugendRanglistenAusrichtung;
import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.entities.RanglistenAusrichtung;
import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.services.PersistenceManager;


/**
 *  DAO for tthelper entities
 *
 */ 
public class TTHelperDAO implements PersistenceManager {

    private final Logger logger = LoggerFactory.getLogger(TTHelperDAO.class);

	private Session session;
	
	public TTHelperDAO( Session session ) {
		
		this.session = session;
	}
	
	
	public void add( PokalMannschaft pokalMannschaft ) {

		session.save( pokalMannschaft );
		logger.info( "Verein " + pokalMannschaft.getVerein() + " has added PokalMannschaft " + pokalMannschaft + "." );
	}

	public void removePokalMannschaft( long id, Verein verein ) {
		
		final Query query = session.createQuery( "SELECT x FROM PokalMannschaft x where x.id = :id" );
        query.setParameter( "id", id );

        PokalMannschaft toBeDeleted = (PokalMannschaft) query.uniqueResult();
        
        // consistency check
        if( verein == toBeDeleted.getVerein() ) {
        	
        	session.delete( toBeDeleted );
        	logger.info( "Verein " + verein + " has deleted PokalMannschaft " + toBeDeleted + "." );
        	
        } else {
        	
        	logger.warn( "Blocked illegal try to delete PokalMannschaft " + toBeDeleted + " by " + verein );
        }
	}
	
	public void removePokalMannschaft( long id ) {
		
		final Query query = session.createQuery( "SELECT x FROM PokalMannschaft x where x.id = :id" );
        query.setParameter( "id", id );

        PokalMannschaft toBeDeleted = (PokalMannschaft) query.uniqueResult();
        
    	session.delete( toBeDeleted );
    	logger.info( "Deleted PokalMannschaft " + toBeDeleted + "." );
	}
	
	
	public void add( RanglistenSpieler spieler ) {

		session.save( spieler );
		logger.info( "Verein " + spieler.getVerein() + " has added RanglistenSpieler " + spieler + "." );
	}
	
	public void removeRanglistenSpieler( long id, Verein verein ) {
		
		final Query query = session.createQuery( "SELECT x FROM RanglistenSpieler x where x.id = :id" );
        query.setParameter( "id", id );
		
        RanglistenSpieler toBeDeleted = (RanglistenSpieler) query.uniqueResult();
        
        // consistency check
        if( verein == toBeDeleted.getVerein() ) {
        	
        	session.delete( toBeDeleted );
        	logger.info( "Verein " + verein + " has deleted RanglistenSpieler " + toBeDeleted + "." );

        } else {
        	
        	logger.warn( "Blocked illegal try to delete RanglistenSpieler " + toBeDeleted + " by " + verein );
        }
	}
	
	public void removeRanglistenSpieler( long id ) {
		
		final Query query = session.createQuery( "SELECT x FROM RanglistenSpieler x where x.id = :id" );
        query.setParameter( "id", id );
		
        RanglistenSpieler toBeDeleted = (RanglistenSpieler) query.uniqueResult();

        removeRanglistenSpieler(id);
    	session.delete( toBeDeleted );
    	logger.info( "Deleted RanglistenSpieler " + toBeDeleted + "." );
	}
	
	public List<PokalMannschaft> getAllPokalMannschaften() {
		
		return session.createQuery( "SELECT x FROM PokalMannschaft x" ).list();
	}
	
	public List<PokalMannschaft> getPokalMannschaften( Verein verein ) {
		
		return session.createQuery( "SELECT x FROM PokalMannschaft x where x.verein = " + verein.value() ).list();
	}
	
	public List<RanglistenSpieler> getAllRanglistenSpieler() {
		
		return session.createQuery( "SELECT x FROM RanglistenSpieler x" ).list();
	}

	public List<RanglistenSpieler> getRanglistenSpieler( Verein verein ) {
		
		return session.createQuery( "SELECT x FROM RanglistenSpieler x where x.verein = " + verein.value() ).list();
	}
	
	public String getRanglistenAusrichtung( Verein verein ) {
		
		Query query =
			session.createQuery( "SELECT x FROM RanglistenAusrichtung  x where x.verein = " + verein.value() );
		
		RanglistenAusrichtung ra = (RanglistenAusrichtung) query.uniqueResult();
		
		if( ra == null ) return "";
		
		return ra.getDescription();
	}
	
	public void saveRanglistenAusrichtung( Verein verein, String ranglistenAusrichtung ) {
		
		Query query =
			session.createQuery( "SELECT x FROM RanglistenAusrichtung  x where x.verein = " + verein.value() );
		
		RanglistenAusrichtung ra = (RanglistenAusrichtung) query.uniqueResult();
		
		if( isBlank( ranglistenAusrichtung ) ) {
			
			if( ra != null ) {
				
				session.delete(ra);
			}
			
		} else {

			if( ra == null ) {
				ra = new RanglistenAusrichtung();
				ra.setVerein(verein);
			}
			ra.setDescription( ranglistenAusrichtung );
			
			session.save(ra);
		}

		logger.info( "Verein " + verein + " has set RanglistenAusrichtung to " + ranglistenAusrichtung + "." );
	}
	
	public String getJugendRanglistenAusrichtung( Verein verein ) {
		
		Query query =
			session.createQuery( "SELECT x FROM JugendRanglistenAusrichtung  x where x.verein = " + verein.value() );
		
		JugendRanglistenAusrichtung jra = (JugendRanglistenAusrichtung) query.uniqueResult();
		
		if( jra == null ) return "";
		
		return jra.getDescription();
	}
	
	public void saveJugendRanglistenAusrichtung( Verein verein, String ranglistenAusrichtung ) {
		
		Query query =
			session.createQuery( "SELECT x FROM JugendRanglistenAusrichtung  x where x.verein = " + verein.value() );
		
		JugendRanglistenAusrichtung jra = (JugendRanglistenAusrichtung) query.uniqueResult();
		
		if( isBlank( ranglistenAusrichtung ) ) {
			
			if( jra != null ) {
				
				session.delete(jra);
			}
			
		} else {


			if( jra == null ) {
				jra = new JugendRanglistenAusrichtung();
				jra.setVerein(verein);
			}
			jra.setDescription( ranglistenAusrichtung );
			
			session.save(jra);
		}

		logger.info( "Verein " + verein + " has set JugendRanglistenAusrichtung to " + ranglistenAusrichtung + "." );
	}
	
	
	public List<RanglistenAusrichtung> getAllRanglistenAusrichtung() {
		
		return session.createQuery( "SELECT x FROM RanglistenAusrichtung  x" ).list();
	}
	
	public List<JugendRanglistenAusrichtung> getAllJugendRanglistenAusrichtung() {
		
		return session.createQuery( "SELECT x FROM JugendRanglistenAusrichtung  x" ).list();
	}
	
	
	// Helfermeldung
	public void add( Helfer helfer ) {

		session.save( helfer );
		logger.info( "Helfer " + helfer.getName() + " of verein " + helfer.getVerein() + " was successfully added." );
	}

	public List<Helfer> getAllHelfer() {
		
		return session.createQuery( "SELECT x FROM Helfer x" ).list();
	}
	
	
	public static boolean isBlank( String value ) {
		
		return value == null  ||  value.length() == 0;
	}
}
