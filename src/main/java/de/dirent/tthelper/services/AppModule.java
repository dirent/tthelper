// Copyright 2007 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.dirent.tthelper.services;

import java.io.IOException;

import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.userdetails.UserDetailsService;
import org.apache.tapestry.Link;
import org.apache.tapestry.internal.services.LinkFactory;
import org.apache.tapestry.internal.services.RequestPageCache;
import org.apache.tapestry.ioc.MappedConfiguration;
import org.apache.tapestry.ioc.OrderedConfiguration;
import org.apache.tapestry.ioc.ServiceBinder;
import org.apache.tapestry.ioc.annotations.InjectService;
import org.apache.tapestry.services.ApplicationStateManager;
import org.apache.tapestry.services.ComponentClassResolver;
import org.apache.tapestry.services.Request;
import org.apache.tapestry.services.RequestExceptionHandler;
import org.apache.tapestry.services.RequestFilter;
import org.apache.tapestry.services.RequestHandler;
import org.apache.tapestry.services.Response;
import org.hibernate.Session;
import org.slf4j.Logger;

import de.dirent.tap5.infrastructure.exception.RedirectException;
import de.dirent.tthelper.TTHelperDatabase;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule {
	
    public static void bind(ServiceBinder binder) {

        binder.bind( PersistenceManager.class, TTHelperDatabase.class );
        
        binder.bind( UserDetailsService.class, UserDetailsServiceImpl.class );
        
        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }
    
    public static UserDetailsService buildUserDetailsService( ApplicationStateManager asm, Session session ) {
    	
    	return new UserDetailsServiceImpl( asm, session );
    }
    
    public static void contributeProviderManager(OrderedConfiguration<AuthenticationProvider> configuration,
            @InjectService("DaoAuthenticationProvider") AuthenticationProvider daoAuthenticationProvider) {
        configuration.add("daoAuthenticationProvider", daoAuthenticationProvider);
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration) {

        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma seperated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        
        configuration.add( "tapestry.supported-locales", "de" );
        
        configuration.add("acegi.loginform.url", "/login");
        configuration.add("acegi.failure.url", "/login/failed");
        configuration.add( "acegi.password.encoder", 
        		"org.acegisecurity.providers.encoding.Md5PasswordEncoder" );
        configuration.add("acegi.password.saltsource", "org.acegisecurity.providers.dao.salt.SystemWideSaltSource");
        configuration.add("acegi.password.salt", "");
    }
    

    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Log instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     * 
     * <p>
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead. 
     * 
     * <p>
     * If this method was named "build", then the service id would be taken from the 
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */    
    public RequestFilter buildTimingFilter(final Logger log) {

        return new RequestFilter() {
        	
            public boolean service(Request request, 
            		Response response, 
            		RequestHandler handler) throws IOException {
            	
                long startTime = System.currentTimeMillis();

                try {
                	
                    // The reponsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.
                    
                    return handler.service(request, response);
                }
                finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security.
     */
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
            @InjectService("TimingFilter")
            RequestFilter filter) {

        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.
        
        configuration.add("Timing", filter);
    }
    
    
    // handle RedirectException
    public static RequestExceptionHandler decorateRequestExceptionHandler( final Object delegate, 
    		final Response response,
    		final RequestPageCache requestPageCache, 
    		final LinkFactory linkFactory,
    		final ComponentClassResolver resolver) {
    	
        return new RequestExceptionHandler() {
        	
            public void handleRequestException( Throwable exception ) throws IOException {

            	// check if wrapped
                Throwable cause = exception;
                if( exception.getCause() instanceof RedirectException ) {
                    cause = exception.getCause();
                }

                // check for redirect
                if( cause instanceof RedirectException ) {
                	
                    // check for class and string
                    RedirectException redirect = (RedirectException) cause;
                    Link pageLink = redirect.getPageLink();

                    // handle Link redirect
                    if (pageLink != null) {
                    	
                        response.sendRedirect( pageLink.toRedirectURI() );
                        return;
                    }
                }

                // no redirect so pass on the exception
                ((RequestExceptionHandler) delegate).handleRequestException(exception);
            }
        };
    }
}