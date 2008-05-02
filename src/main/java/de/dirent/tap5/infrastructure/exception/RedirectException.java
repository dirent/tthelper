package de.dirent.tap5.infrastructure.exception;


import org.apache.tapestry.Link;


public class RedirectException extends RuntimeException {

	private static final long serialVersionUID = -6649540929302509033L;
	
	
	protected Link pageLink;


    public RedirectException(Link link) {
        this.pageLink = link;
    }

    public Link getPageLink() {
        return pageLink;
    }
}
