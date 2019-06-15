package de.dirent.tthelper.services;


import org.acegisecurity.userdetails.UserDetails;

import nu.localhost.tapestry.acegi.services.internal.SaltSourceImpl;


public class SimpleSaltSource extends SaltSourceImpl {

    public Object getSalt(UserDetails user) {
    	
        return "";
    }
}
