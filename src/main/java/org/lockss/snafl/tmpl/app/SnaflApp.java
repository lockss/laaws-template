package org.lockss.snafl.tmpl.app;


  import org.glassfish.jersey.jackson.JacksonFeature;
  import org.lockss.snafl.tmpl.api.EntityBrowser;
  import io.swagger.jaxrs.config.BeanConfig;
  import io.swagger.jaxrs.listing.ApiListingResource;
  import org.eclipse.jetty.server.Server;
  import org.eclipse.jetty.server.handler.ContextHandler;
  import org.eclipse.jetty.server.handler.HandlerList;
  import org.eclipse.jetty.server.handler.ResourceHandler;
  import org.eclipse.jetty.servlet.ServletContextHandler;
  import org.eclipse.jetty.servlet.ServletHolder;
  import org.eclipse.jetty.util.resource.Resource;
  import org.glassfish.jersey.server.ResourceConfig;
  import org.glassfish.jersey.servlet.ServletContainer;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;


/**
 * Main Class for starting the Entity Browser
 */
public class SnaflApp
{
  private static final Logger LOG = LoggerFactory.getLogger( SnaflApp.class );

  private static final int DEFAULT_PORT = 8888;
  private int serverPort;


  public SnaflApp(int serverPort) throws Exception {
    this.serverPort = serverPort;

    Server server = configureServer();
    server.start();
    server.join();
  }

  protected Server configureServer() throws Exception {
    Resource.setDefaultUseCaches( false );

    // Build the Swagger.
    buildSwagger();

    // Holds handlers
    final HandlerList handlers = new HandlerList();

    // Handler for Swagger UI, static handler.
    handlers.addHandler( buildSwaggerUI() );

    // Handler for Entity Browser and Swagger
    handlers.addHandler( buildContext() );

    // Start server
    Server server = new Server(serverPort);
    server.setHandler( handlers );
    return server;
  }

  public static void main( String[] args )
  {
    int serverPort = DEFAULT_PORT;

    if(args.length >= 1) {
      try {
        serverPort = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        LOG.warn("invalid number format:" + args[0] + " using default port.");
      }
    }

    try {
      new SnaflApp(serverPort);
    }
    catch (Exception e) {
      LOG.error("Server initialization failed!");
      e.printStackTrace();
    }
  }


  private static void buildSwagger()
  {
    // This configures Swagger
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setVersion( "1.0.0" );
    beanConfig.setResourcePackage( EntityBrowser.class.getPackage().getName() );
    beanConfig.setScan( true );
    beanConfig.setBasePath( "/" );
    beanConfig.setDescription( "Simple Browser API for SNAFL." );
    beanConfig.setTitle( "Entity Browser" );
  }


  private static ContextHandler buildContext()
  {
    ResourceConfig resourceConfig = new ResourceConfig();
    // Replace EntityBrowser with your resource class
    // io.swagger.jaxrs.listing loads up Swagger resources
    resourceConfig.packages( EntityBrowser.class.getPackage().getName(),
                             ApiListingResource.class.getPackage().getName() );
    resourceConfig.register(JacksonFeature.class);
    ServletContainer servletContainer = new ServletContainer( resourceConfig );
    ServletHolder entityBrowser = new ServletHolder( servletContainer );
    ServletContextHandler entityBrowserContext = new ServletContextHandler( ServletContextHandler.SESSIONS );
    entityBrowserContext.setContextPath( "/" );
    entityBrowserContext.addServlet( entityBrowser, "/*" );

    return entityBrowserContext;
  }


  // This starts the Swagger UI at http://localhost:8888/docs
  private static ContextHandler buildSwaggerUI() throws Exception
  {
    final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
    swaggerUIResourceHandler.setResourceBase(
      SnaflApp.class.getClassLoader().getResource( "swaggerui" ).toURI()
                                                            .toString() );
    final ContextHandler swaggerUIContext = new ContextHandler();
    swaggerUIContext.setContextPath( "/docs/" );
    swaggerUIContext.setHandler( swaggerUIResourceHandler );

    return swaggerUIContext;
  }
}