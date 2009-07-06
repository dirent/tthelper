package de.dirent.tthelper.pages;


import java.text.Format;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Translator;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import de.dirent.tthelper.entities.Helfer;
import de.dirent.tthelper.pages.helfer.CreateHelfer;
import de.dirent.tthelper.utils.BooleanFormat;
import de.dirent.tthelper.validate.DateTranslator;



/**
 * Start page of unsecured (anonymous) services
 */
public class Services extends TTHelperPage {
	
	@InjectPage
	private CreateHelfer createHelfer;

	
	Object onActionFromHelferMeldung() {

		return createHelfer;
	}
	
	@Property
	private Helfer helfer;
	
	public List<Helfer> getGemeldeteHelfer() {

		return getPersistenceManager().getAllHelfer();
	}
	
    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    private final BeanModel<Helfer> model;     
    {
        model = beanModelSource.create( Helfer.class, true, resources.getMessages() );
        //model.exclude( "anschrift", "telefonnummer", "birthDate", "size", "email", "bemerkung" );
    }
    
    public BeanModel<Helfer> getModel() { return this.model; }

    private Format booleanFormat = new BooleanFormat( "Ja", "Nein" );
	
    public Format getBooleanFormat() {
    	
    	return booleanFormat;
    }
    
    public Translator<Date> getDateTranslator() {
    	
    	return new DateTranslator( "dd.MM.yyyy" );
    }
}