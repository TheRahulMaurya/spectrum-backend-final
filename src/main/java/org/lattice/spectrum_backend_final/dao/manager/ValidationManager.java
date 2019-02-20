package org.lattice.spectrum_backend_final.dao.manager;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ValidationManager {
    
    public static String checkPhone(String phone)
    {
        final String CHECK_PHONE = "select * from user_details where contact_number = '"+phone+"';";
        try (Connection conn = DBConnectionManager.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                
                )
        {
            conn.setAutoCommit(false);
         
            ResultSet resultSet = stmt.executeQuery( CHECK_PHONE );
           
          
           conn.commit();
           
           if(resultSet.next())
           {
               return "true";
           }
           
           
         
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "false";
    }
    
    
    public static String getAllPhones()
    {
        final String GET_ALL_PHONE = "select contact_number from user_details ;";
        String jsonString = null;
        try (Connection conn = DBConnectionManager.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                
                )
        {
            
            conn.setAutoCommit(false);
         
            
            ResultSet resultSet = stmt.executeQuery( GET_ALL_PHONE );
           

           jsonString = DatabaseManager.getJSONFromResultSet(resultSet).toString();
           
           
           conn.commit();

         
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jsonString;
    }
}
