package de.dirent.tthelper.utils;


import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;


public class BooleanFormat extends Format {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7451955109810483640L;
	
	
	private String trueString;
	private String falseString;
	private String nullString = "";
	
	public BooleanFormat( String trueString, String falseString ) {
		
		this.trueString = trueString;
		this.falseString = falseString;
	}
	
	
	@Override
	public StringBuffer format( Object obj, StringBuffer toAppendTo,
			FieldPosition pos ) {

		if( !(obj instanceof Boolean) ) return toAppendTo;
		
		if( obj == null ) toAppendTo.append( nullString );
		else if( ((Boolean) obj).booleanValue() ) toAppendTo.append( trueString );
		else toAppendTo.append( falseString );
		
		return toAppendTo;
	}

	@Override
	public Object parseObject( String source, ParsePosition pos ) {

		if( nullString.equals(source) ) return null;
		if( trueString.equals(source) ) return Boolean.TRUE;
		
		return Boolean.FALSE;
	}
}
