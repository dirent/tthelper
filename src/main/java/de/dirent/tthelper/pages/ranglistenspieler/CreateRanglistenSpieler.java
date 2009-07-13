package de.dirent.tthelper.pages.ranglistenspieler;


import java.text.Format;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.pages.MeldePage;
import de.dirent.tthelper.utils.BooleanFormat;


public class CreateRanglistenSpieler extends MeldePage {

	public List<RanglistenSpieler> getGemeldeteRanglistenSpieler() {
		
		if( isNotAdmin() )
			return getPersistenceManager().getRanglistenSpieler( getCurrentVerein() );
		
		return getPersistenceManager().getAllRanglistenSpieler();
	}
	
	@Property
	private RanglistenSpieler rs;
	
	@Property @Persist
	private RanglistenSpieler spieler;	

	@SetupRender
	public void validate() {
		
		if( this.spieler == null ) {
			
			this.spieler = new RanglistenSpieler();
		}
		
		this.spieler.setVerein( getCurrentVerein() );
	}
	
	public Object initialize( Verein verein ) {
	
		this.spieler = new RanglistenSpieler();
		this.spieler.setVerein(verein);

		return this;
	}
	
	
	@CommitAfter
	public void onSuccessFromSpieler() {

		getPersistenceManager().add( this.spieler );
	}
	
	
    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    private final BeanModel model; 
    
    {
        model = beanModelSource.create( RanglistenSpieler.class, true, resources.getMessages() );

        model.add( "delete", null );
    }    
    public BeanModel getModel() { 
    	return this.model; 
    }
    
    
	private Format booleanFormat = new BooleanFormat( "Ja", "Nein" );
	
    public Format getBooleanFormat() {
    	
    	return booleanFormat;
    }
    
    
    @CommitAfter
    public void onActionFromDelete( long id ) {
    	
    	if( isNotAdmin() )
    		getPersistenceManager().removeRanglistenSpieler( id, getCurrentVerein() );
    	else 
    		getPersistenceManager().removeRanglistenSpieler( id );
    }  
}