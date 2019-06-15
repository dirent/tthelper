package de.dirent.tthelper.services;


import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.tapestry5.services.ApplicationInitializer;
import org.apache.tapestry5.services.ApplicationInitializerFilter;
import org.apache.tapestry5.services.Context;
import org.hibernate.Session;

import de.dirent.tthelper.entities.UserDetailsBean;
import de.dirent.tthelper.model.Verein;


/**
 * Sets up the initial users if not already exist
 */
public class UserInitializerImpl implements ApplicationInitializerFilter {
	
    private PasswordEncoder passwordEncoder;
    private SaltSource saltSource;
    private Session session;
    
    public UserInitializerImpl(PasswordEncoder passwordEncoder, SaltSource saltSource, Session session) {
        
    	this.passwordEncoder = passwordEncoder;
        this.saltSource = saltSource;
        this.session = session;
    }
    

    public void initializeApplication( Context context, 
    		ApplicationInitializer applicationInitializer ) {

    	// create the user db for vereine in Bielefeld-Halle
		String username = "dirk";

		System.out.println( "Create initial user " + username );
		
		final UserDetailsBean dirk = new UserDetailsBean();
    	dirk.setUsername(username);
        dirk.setPassword( passwordEncoder.encodePassword( "test", saltSource.getSalt(dirk) ) );
        dirk.setVerein( Verein.SVG );
        dirk.setEmail( "webmaster@dirent.de" );
		dirk.addRole("ROLE_USER");
		dirk.addRole("ROLE_ADMIN");

		session.save( dirk );
    	
        applicationInitializer.initializeApplication(context);
    }
    
    
    void createUser( String username, 
    		String password, 
    		Verein verein, 
    		String email,
    		boolean isAdmin ) {
    	
		System.out.println( "Create initial user " + username );

		final UserDetailsBean user = new UserDetailsBean();
    	user.setUsername(username);
        user.setPassword( passwordEncoder.encodePassword( password, saltSource.getSalt(user) ) );
        user.setVerein( verein );
        user.setEmail( email );
		user.addRole("ROLE_USER");
        if( isAdmin ) user.addRole("ROLE_ADMIN");

        session.save(user);
    }
}
