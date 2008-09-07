package de.dirent.tthelper.services;


import java.io.File;
import java.util.Properties;

import org.apache.tapestry.hibernate.HibernateConfigurer;
import org.apache.tapestry.services.ApplicationGlobals;
import org.hibernate.cfg.Configuration;


public class TTHelperHibernateConfigurer implements HibernateConfigurer {

	private String tthelperDbPath;
	
	
	public TTHelperHibernateConfigurer( ApplicationGlobals globals ) {
	
		File tempDir = 
			(File) globals.getServletContext().getAttribute( "javax.servlet.context.tempdir" );
		
		File dbRoot = new File( tempDir, "db" );
		if( !dbRoot.exists() ) {
			
			dbRoot.mkdir();
		}
		
		this.tthelperDbPath = "jdbc:hsqldb:" + dbRoot.getAbsolutePath() + "/tthelper";
	}
	
	public void configure(Configuration configuration) {
		
		Properties extraProperties = new Properties();		
		extraProperties.setProperty( "connection.url", tthelperDbPath );
		extraProperties.setProperty( "hibernate.connection.url", tthelperDbPath );

		configuration.addProperties(extraProperties);
	}
}