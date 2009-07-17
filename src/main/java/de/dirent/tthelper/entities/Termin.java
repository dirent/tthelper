package de.dirent.tthelper.entities;


import java.util.Date;

import javax.persistence.Entity;

import org.apache.tapestry5.beaneditor.Validate;


@Entity
public class Termin extends AbstractEntity implements Cloneable {
	
	private static final long serialVersionUID = -1751288983818189380L;

	
	private Date fromDate;
	private Date toDate;
	
	private String body;
	
	
	public Date getFromDate() {
	
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
	
		this.fromDate = fromDate;
	}


	@Validate( "required" )
	public Date getToDate() {
	
		return toDate;
	}
	public void setToDate(Date toDate) {
	
		this.toDate = toDate;
	}


	public String getBody() {
	
		return body;
	}
	public void setBody(String body) {
	
		this.body = body;
	}



	public Termin clone() {
		
		Termin clone = new Termin();
		clone.setId( getId() );
		clone.fromDate = fromDate;
		clone.toDate = toDate;
		clone.body = body;
		
		return clone;
	}
}
