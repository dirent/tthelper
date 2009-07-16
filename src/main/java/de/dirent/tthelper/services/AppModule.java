package de.dirent.tthelper.services;


import java.io.IOException;

import nu.localhost.tapestry.acegi.services.SaltSourceService;
import nu.localhost.tapestry.acegi.services.SecurityModule;
import nu.localhost.tapestry.acegi.services.internal.AcegiWorker;
import nu.localhost.tapestry.acegi.services.internal.SecurityChecker;

import org.acegisecurity.annotation.Secured;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.acegisecurity.userdetails.UserDetailsService;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.Translator;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.hibernate.HibernateConfigurer;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.internal.services.RequestPageCache;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.services.ApplicationGlobals;
import org.apache.tapestry5.services.ApplicationInitializerFilter;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestExceptionHandler;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.hibernate.Session;
import org.slf4j.Logger;

import de.dirent.tap5.infrastructure.exception.RedirectException;
import de.dirent.tthelper.TTHelperDAO;
import de.dirent.tthelper.internal.services.LuceneIndexInitializer;
import de.dirent.tthelper.internal.services.LuceneIndexer;
import de.dirent.tthelper.validate.DateTranslator;


@SubModule(SecurityModule.class)
public class AppModule {
	
	
	// Fix while tapestry-acegi is not compatible with T5.1
    public static void contributeComponentClassTransformWorker(
            OrderedConfiguration<ComponentClassTransformWorker> configuration, 
            SecurityChecker securityChecker) {
    	
        final AcegiWorker acegiWorker = new AcegiWorker(securityChecker);
        configuration.override("Acegi", new ComponentClassTransformWorker() {
        	
            public void transform( ClassTransformation transformation, MutableComponentModel model) {
                
            	Secured annotation = transformation.getAnnotation(Secured.class);
                if (annotation != null) {
                    model.addRenderPhase(BeginRender.class);
                    model.addRenderPhase(CleanupRender.class);
                }
                acegiWorker.transform(transformation, model);
            }
        } );
    }

    
    public static void bind(ServiceBinder binder) {

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }
    
    public static FulltextIndexer buildFulltextIndexer( ApplicationGlobals globals, 
    		PersistenceManager persistenceManager ) {
    	
    	return new LuceneIndexer( globals, persistenceManager );
    }
    
    
    public static PersistenceManager buildPersistenceManager( Session session, FulltextIndexer indexer ) {
    	
    	return new TTHelperDAO( session );
    }
    
    public static UserDetailsService buildUserDetailsService( Session session ) {
    	
    	return new UserDetailsServiceImpl( session );
    }
        
    public static void contributeApplicationInitializer( OrderedConfiguration<ApplicationInitializerFilter> configuration,
            final PasswordEncoder passwordEncoder, 
            final SaltSourceService saltSource, 
            final HibernateSessionManager hibernateSessionManager,
            final FulltextIndexer indexer ) {
    	
        configuration.add( "UserInitializer", 
        		new UserInitializerImpl(passwordEncoder, saltSource, hibernateSessionManager) );

        configuration.add( "FulltextInitializer", new LuceneIndexInitializer( indexer ) );
    }
    
    public static void contributeProviderManager(OrderedConfiguration<AuthenticationProvider> configuration,
            @InjectService("DaoAuthenticationProvider") AuthenticationProvider daoAuthenticationProvider) {
        configuration.add("daoAuthenticationProvider", daoAuthenticationProvider);
    }

    public static void contributeHibernateSessionSource( OrderedConfiguration<HibernateConfigurer> config,
    		ApplicationGlobals globals ) {
    	
    	config.add( "TTHelper", new TTHelperHibernateConfigurer( globals ), "before:Default*" );
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration) {

        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma seperated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        
        configuration.add( "tapestry.supported-locales", "de" );
        configuration.add( "tapestry.response-encoding", "iso-8859-1" );
        
        configuration.add("acegi.loginform.url", "/login");
        configuration.add("acegi.failure.url", "/login/failed");
        configuration.add( "acegi.password.encoder", 
        		"org.acegisecurity.providers.encoding.Md5PasswordEncoder" );
    }
    
    public static void contributeTranslatorSource( Configuration<Translator> configuration ) {

    	configuration.add( new DateTranslator( "dd.MM.yyyy" ) );
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