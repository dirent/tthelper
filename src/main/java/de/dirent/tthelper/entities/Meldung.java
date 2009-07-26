package de.dirent.tthelper.entities;


import java.util.Date;

import javax.persistence.Entity;

import org.apache.tapestry5.beaneditor.Validate;


@Entity
public class Meldung extends AbstractEntity implements Cloneable {
	
	private static final long serialVersionUID = 5958258084351980541L;

	
	private Date publishDate;
	private String headline;
	private String body;
	
	
	@Validate( "required" )
	public Date getPublishDate() {
	
		return publishDate;
	}
	public void setPublishDate( Date publishDate ) {
	
		this.publishDate = publishDate;
	}

	
	@Validate( "required" )
	public String getHeadline() {
		
		return this.headline;
	}
	public void setHeadline( String headline ) {
		
		this.headline = headline;
	}

	
	public String getBody() {
	
		return body;
	}
	public void setBody(String body) {
	
		this.body = body;
	}



	public Meldung clone() {
		
		Meldung clone = new Meldung();
		clone.setId( getId() );
		clone.publishDate = publishDate;
		clone.headline = headline;
		clone.body = body;
		
		return clone;
	}
}
