package de.dirent.tthelper.services;


import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.services.ApplicationInitializer;
import org.apache.tapestry5.services.ApplicationInitializerFilter;
import org.apache.tapestry5.services.Context;
import org.hibernate.Query;

import de.dirent.tthelper.entities.UserDetailsBean;
import de.dirent.tthelper.model.Verein;


/**
 * Sets up the initial users if not already exist
 */
public class UserInitializerImpl implements ApplicationInitializerFilter {
	
    private PasswordEncoder passwordEncoder;
    private SaltSource saltSource;
    private HibernateSessionManager manager;
    
    public UserInitializerImpl(PasswordEncoder passwordEncoder, 
    		SaltSource saltSource, 
    		HibernateSessionManager manager ) {
        
    	this.passwordEncoder = passwordEncoder;
        this.saltSource = saltSource;
        this.manager = manager;
    }
    

    public void initializeApplication( Context context, 
    		ApplicationInitializer applicationInitializer ) {

    	query = manager.getSession().createQuery( 
    			"SELECT x FROM UserDetailsBean x where x.username = :username" );
    	
    	// create the user db for vereine in Bielefeld-Halle
    	long millis = System.currentTimeMillis();
    	
		checkUser( "dirk", "test", Verein.SVG, "webmaster@dirent.de", true );
    	// add more users here...
    	
		manager.commit();
		
		System.out.println( "Initializing user db needed " + (System.currentTimeMillis()-millis)  + "ms." );
		
        applicationInitializer.initializeApplication(context);
    }
    
    
    private Query query= null; 
    
    private void checkUser( String username,
    		String password, 
    		Verein verein, 
    		String email,
    		boolean isAdmin ) {
    	
		query.setParameter( "username", username );        
        if( query.uniqueResult() == null ) {

			createUser( username, password, verein, email, isAdmin );
        }
    }
    
    
    private void createUser( String username, 
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

        manager.getSession().save(user);
    }
}