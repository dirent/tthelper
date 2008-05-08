package de.dirent.tthelper.pages;


import org.apache.tapestry.ioc.annotations.Inject;
import org.apache.tapestry.ioc.annotations.Value;
import org.apache.tapestry.services.Request;


public class Login {
	
    @Inject @Value("${acegi.check.url}")
    private String checkUrl;
    
    @Inject
    private Request request;
    
    
    private boolean failed = false; 

    public boolean isFailed() {
    	
        return failed;
    }
    
    public String getLoginCheckUrl() {
    	
        return request.getContextPath() + checkUrl;
    }
    
    public void onActivate( String extra ) {
    	
        if (extra.equals("failed")) {
            failed = true;
        }
    }
}