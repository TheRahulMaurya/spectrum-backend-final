package org.lattice.spectrum_backend_final;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

//import for websocket


import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;



public class Main {

    public static void main(String[] args) {
 
    	SocketIOServer socketServer = null;
    	Server server = null ;
    	
    	try {
    		
        //code for websocket
        
        Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(9090);
        
        
 
        socketServer = new SocketIOServer(configuration);
 
        
        
        
      //code for jetty
        server = new Server(9999);

        ServletContextHandler ctx = 
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
                
        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/rest/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages", 
                "org.lattice.spectrum_backend_final.services.handler");
        
     
        SocketEvent.events(socketServer);
        

        socketServer.start();
        System.out.println("Websocket server started at "+configuration.getPort());
        
            server.start();
            System.out.println("jetty server started at "+9999);
            Thread.sleep(Integer.MAX_VALUE);
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            server.destroy();
            socketServer.stop();
        } 
            
        
    }
}