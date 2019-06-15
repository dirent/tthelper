package de.dirent.tthelper.validate;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.internal.translator.AbstractTranslator;
import org.apache.tapestry5.services.FormSupport;


public class DateTranslator extends AbstractTranslator<Date> {

	private DateFormat df;
	
	public DateTranslator( String pattern ) {
		
		super( "date", Date.class, "a-date-is-a-date" );
		
		df = new SimpleDateFormat( pattern );
		df.setLenient(false);
	}
	
    public Date parseClient( Field field, 
    		String clientValue, 
    		String message ) throws ValidationException {

		try {
		
			return df.parse( clientValue );
		
		} catch( ParseException pe ) {
			
			throw new ValidationException( message );
		}
	}


	public String toClient( Date value ) {

		if( value == null ) return "";
		
		return df.format( value );
	}


	public void render( Field field, String message, 
			MarkupWriter writer,
            FormSupport formSupport) {
	}
}
