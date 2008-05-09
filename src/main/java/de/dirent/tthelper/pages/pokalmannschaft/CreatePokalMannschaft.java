package de.dirent.tthelper.pages.pokalmannschaft;


import java.util.List;

import org.apache.tapestry.ComponentResources;
import org.apache.tapestry.annotations.ApplicationState;
import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;
import org.apache.tapestry.beaneditor.BeanModel;
import org.apache.tapestry.ioc.annotations.Inject;
import org.apache.tapestry.services.BeanModelSource;

import de.dirent.tthelper.entities.UserDetailsBean;
import de.dirent.tthelper.model.PokalMannschaft;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.pages.TTHelperPage;


public class CreatePokalMannschaft extends TTHelperPage {

	public List<PokalMannschaft> getGemeldetePokalMannschaften() {
		
		return getPersistenceManager().getPokalMannschaften( getCurrentVerein() );
	}	
	@Property
	private PokalMannschaft pm;
	
	@Property @Persist
	private PokalMannschaft mannschaft;	
	
	@ApplicationState
	private UserDetailsBean user;
	
	@SetupRender
	public void validate() {
		
		if( this.mannschaft == null ) {
			
			this.mannschaft = new PokalMannschaft();
			this.mannschaft.setVerein( getCurrentVerein() );
		}
	}
	
	public Object initialize( Verein verein ) {
	
		this.mannschaft = new PokalMannschaft();
		this.mannschaft.setVerein( verein );

		return this;
	}
	
	
	public void onSuccessFromMannschaft() {

		getPersistenceManager().add( this.mannschaft );
	}
	
	
    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    private final BeanModel<PokalMannschaft> model; 
    
    {
        model = beanModelSource.create( PokalMannschaft.class, true, resources );

        model.add("delete", null);
    }    
    public BeanModel<PokalMannschaft> getModel() { return this.model; }
    
    
    
    public void onActionFromDelete( long id ) {
        getPersistenceManager().removePokalMannschaft( id );
    }  
}
