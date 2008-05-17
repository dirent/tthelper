package de.dirent.tthelper.pages.ranglistenspieler;


import java.text.Format;
import java.util.List;

import org.apache.tapestry.ComponentResources;
import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;
import org.apache.tapestry.beaneditor.BeanModel;
import org.apache.tapestry.ioc.annotations.Inject;
import org.apache.tapestry.services.BeanModelSource;

import de.dirent.tthelper.entities.RanglistenSpieler;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.pages.TTHelperPage;
import de.dirent.tthelper.utils.BooleanFormat;


public class CreateRanglistenSpieler extends TTHelperPage {

	public List<RanglistenSpieler> getGemeldeteRanglistenSpieler() {
		
		return getPersistenceManager().getRanglistenSpieler( getCurrentVerein() );
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
	
	
	public void onSuccessFromSpieler() {

		getPersistenceManager().add( this.spieler );
	}
	
	
    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    private final BeanModel model; 
    
    {
        model = beanModelSource.create( RanglistenSpieler.class, true, resources );

        model.add( "delete", null );
    }    
    public BeanModel getModel() { 
    	return this.model; 
    }
    
    
	private Format booleanFormat = new BooleanFormat( "Ja", "Nein" );
	
    public Format getBooleanFormat() {
    	
    	return booleanFormat;
    }
    
    
    
    public void onActionFromDelete( long id ) {
    	
        getPersistenceManager().removeRanglistenSpieler( id );
    }  
}
