package de.dirent.tthelper;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

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

	private Session session;
	
	public TTHelperDAO( Session session ) {
		
		this.session = session;
	}
	
	
	public void add( PokalMannschaft pokalMannschaft ) {

		session.save( pokalMannschaft );
	}

	public void removePokalMannschaft( long id ) {
	
		final Query query = session.createQuery( "SELECT x FROM PokalMannschaft x where x.id = :id" );
        query.setParameter( "id", id );

        session.delete( query.uniqueResult() );
	}
	
	
	public void add( RanglistenSpieler spieler ) {

		session.save( spieler );
	}
	
	public void removeRanglistenSpieler( long id ) {
		
		final Query query = session.createQuery( "SELECT x FROM RanglistenSpieler x where x.id = :id" );
        query.setParameter( "id", id );
		
		session.delete( query.uniqueResult() );
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
