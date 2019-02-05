package org.lattice.spectrum_backend_final.dao.manager;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


public class DatabaseManager {

	    

	
	
	public static JSONArray getJSONFromResultSet(ResultSet rs) {
  
		JSONArray jsonArray = new JSONArray();
		if(rs!=null)
		{
		  try {
		      ResultSetMetaData metaData = rs.getMetaData();
		      while(rs.next())
		      {
		          JSONObject jsonObject = new JSONObject();
		          for(int columnIndex=1;columnIndex<=metaData.getColumnCount();columnIndex++)
		          {
		              if(rs.getString(metaData.getColumnName(columnIndex))!=null)
		            	  jsonObject.put(metaData.getColumnLabel(columnIndex),     rs.getString(metaData.getColumnName(columnIndex)));
		              else
		            	  jsonObject.put(metaData.getColumnLabel(columnIndex), "");
		          }
		          jsonArray.put(jsonObject);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }
		}
		return jsonArray;
	}


	    public void save(String title) throws Exception
	    {

	        final String INSERT_QUERY = "insert into todo (title) values('"+title+"');"; 
	        

	        try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();

	            conn.setAutoCommit(false);

	            Statement stmt = conn.createStatement();

	           stmt.executeUpdate(INSERT_QUERY);

	           stmt.close();
	           conn.commit();
	           conn.close();
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	    }
	    
	    public JSONArray showAll()
	    {
	        ResultSet resultSet = null;
	        JSONArray jsonArray = null;
	        final String SELECT_QUERY = "select * from todo;";
	                try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();
	            conn.setAutoCommit(false);
	            Statement stmt = conn.createStatement();
	           
	         
	           resultSet = stmt.executeQuery( SELECT_QUERY );
	           
	          
	      
	           jsonArray = getJSONFromResultSet(resultSet);
	          
	           resultSet.close();
	           stmt.close();
	           conn.commit();
	           conn.close();
	           
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	               
	        return jsonArray;
	    }
	    
	    public void update(int taskId , String title)
	    {
	    	final String UPDATE_QUERY = "update todo set title = '"+title+"' where id = "+taskId+";";
	    	
	    	try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();
	            conn.setAutoCommit(false);
	            Statement stmt = conn.createStatement();
	            
	         
	            stmt.executeUpdate( UPDATE_QUERY );
	           
	          

			   stmt.close();
			   conn.commit();
			   conn.close();
	           
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    
	    }
	    
	    public void delete(int taskId)
	    {
	    	final String DELETE_QUERY = "delete from todo where id = "+taskId+";";
	    	
	    	try {
	            Connection conn = DBConnectionManager.getInstance().getConnection();
	            conn.setAutoCommit(false);
	            Statement stmt = conn.createStatement();
	            
	         
	           stmt.executeUpdate( DELETE_QUERY );
	           
	           

	           stmt.close();
	           conn.commit();
	           conn.close();
	           
	         
	        } catch (SQLException ex) {
	            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    
	    }
	    
	    
	

}