package de.dirent.tthelper.validate;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry.Translator;
import org.apache.tapestry.ValidationException;
import org.apache.tapestry.ioc.Messages;


public class DateTranslator implements Translator<Date> {

	private DateFormat df;
	
	public DateTranslator( String pattern ) {
	
		df = new SimpleDateFormat( pattern );
		df.setLenient(false);
	}
	
	@Override
	public Class<Date> getType() {

		return Date.class;
	}

	@Override
	public Date parseClient( String clientValue, Messages messages ) throws ValidationException {

		try {
		
			return df.parse( clientValue );
		
		} catch( ParseException pe ) {
			
			throw new ValidationException( messages.format( "date-format", clientValue ) );
		}
	}

	@Override
	public String toClient( Date value ) {

		if( value == null ) return "";
		
		return df.format( value );
	}

}
