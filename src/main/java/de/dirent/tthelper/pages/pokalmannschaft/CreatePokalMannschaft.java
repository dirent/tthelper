package de.dirent.tthelper.pages.pokalmannschaft;


import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.pages.MeldePage;


public class CreatePokalMannschaft extends MeldePage {

	public List<PokalMannschaft> getGemeldetePokalMannschaften() {

		if( isNotAdmin() )
			return getPersistenceManager().getPokalMannschaften( getCurrentVerein() );
		
		return getPersistenceManager().getAllPokalMannschaften();
	}
	
	@Property
	private PokalMannschaft pm;
	
	@Property @Persist
	private PokalMannschaft mannschaft;	
	

	@SetupRender
	public void validate() {
		
		if( this.mannschaft == null ) {
			
			this.mannschaft = new PokalMannschaft();
		}
		
		this.mannschaft.setVerein( getCurrentVerein() );
	}
	
	public Object initialize( Verein verein ) {
	
		this.mannschaft = new PokalMannschaft();
		this.mannschaft.setVerein( verein );

		return this;
	}
	
	
	@CommitAfter
	public void onSuccessFromMannschaft() {

		getPersistenceManager().add( this.mannschaft );
	}
	
	
    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    private final BeanModel<PokalMannschaft> model; 
    
    {
        model = beanModelSource.create( PokalMannschaft.class, true, resources.getMessages() );

        model.add("delete", null);
    }    
    public BeanModel<PokalMannschaft> getModel() { return this.model; }
    
    
    
    @CommitAfter
    public void onActionFromDelete( long id ) {
    	
    	if( isNotAdmin() )
    		getPersistenceManager().removePokalMannschaft( id, getCurrentVerein() );
    	else
    		getPersistenceManager().removePokalMannschaft( id );
    }  
}
