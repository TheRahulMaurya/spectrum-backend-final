package org.lattice.spectrum_backend_final.dao.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.sql.*;

public class DBConnectionManager
{
    
    private static final String DB_URL="jdbc:sqlite:S:/sqlite/spectrum.db";

    private static final String DRIVER="org.sqlite.JDBC";

    private static DBConnectionManager connectionManager ;

    private DBConnectionManager()
    {
        try{
        Class.forName(DRIVER);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Driver Not Founnd");
        }
        
        System.out.println("driver found");

    }

    public static DBConnectionManager getInstance() {
        
    	
    		synchronized (DBConnectionManager.class) {
    			if(connectionManager  == null)
    	    	{
    				connectionManager = new DBConnectionManager();
    				
    	    	}
    		}
    	
        return connectionManager ;
    }
    
    
    public Connection getConnection() {
		try {
			
			Connection conn = DriverManager.getConnection(DB_URL);
			
			return conn;		
		} catch(Exception e) {
			
			System.out.println(" Get Connection Error: " + e.getMessage());
		} finally {
		}
		return null;
	}

    public static void closeDBConnection(ResultSet rs, Statement stmt, Connection con){ 
        try { 
            if(rs!=null) {
                rs.close();
                rs =null;
            }
        } 
        catch (SQLException ex) { 
            ex.printStackTrace(); 
        }
        
        try { 
            if(stmt!=null) {
                stmt.close();
                stmt =null;
            }
        } 
        catch (SQLException ex) { 
            ex.printStackTrace(); 
        }
        
        try { 
            if(con!=null) {
                con.close();
                con =null;
            }
        } 
        catch (SQLException ex) { 
            ex.printStackTrace(); 
        }
        
    }     
}