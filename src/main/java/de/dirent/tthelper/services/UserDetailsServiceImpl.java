package de.dirent.tthelper.services;


import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import de.dirent.tthelper.entities.UserDetailsBean;


public class UserDetailsServiceImpl implements UserDetailsService {

	private final Session session;
	
	public UserDetailsServiceImpl( Session session ) {
		
		this.session = session;
	}
	
	public UserDetails loadUserByUsername( String username ) 
	
			throws UsernameNotFoundException, DataAccessException {
		
		final Query query = session.createQuery( "SELECT x FROM UserDetailsBean x where x.username = :username" );
        query.setParameter( "username", username );
		final UserDetailsBean bean = (UserDetailsBean) query.uniqueResult();
		if( bean == null ) {
			throw new UsernameNotFoundException(username);
		}
		return bean;
	}
}
