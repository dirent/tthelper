package de.dirent.tthelper;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	}

	public void removePokalMannschaft( long id, Verein verein ) {
	
		final Query query = session.createQuery( "SELECT x FROM PokalMannschaft x where x.id = :id" );
        query.setParameter( "id", id );

        PokalMannschaft toBeDeleted = (PokalMannschaft) query.uniqueResult();
        
        // consistency check
        if( verein == toBeDeleted.getVerein() ) session.delete( toBeDeleted );
        else logger.warn( "Blocked illegal try to delete PokalMannschaft " + toBeDeleted + " by " + verein );
	}
	
	
	public void add( RanglistenSpieler spieler ) {

		session.save( spieler );
	}
	
	public void removeRanglistenSpieler( long id, Verein verein ) {
		
		final Query query = session.createQuery( "SELECT x FROM RanglistenSpieler x where x.id = :id" );
        query.setParameter( "id", id );
		
        RanglistenSpieler toBeDeleted = (RanglistenSpieler) query.uniqueResult();
        
        // consistency check
        if( verein == toBeDeleted.getVerein() ) session.delete( toBeDeleted );
        else logger.warn( "Blocked illegal try to delete RanglistenSpieler " + toBeDeleted + " by " + verein );
	}
	
	public List<PokalMannschaft> getPokalMannschaften( Verein verein ) {
		
		return session.createQuery( "SELECT x FROM PokalMannschaft x where x.verein = " + verein.value() ).list();
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
		if( ra == null ) {
			ra = new RanglistenAusrichtung();
			ra.setVerein(verein);
		}
		ra.setDescription( ranglistenAusrichtung );
		
		session.save(ra);
	}
}
