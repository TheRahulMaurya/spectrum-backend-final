package org.lattice.spectrum_backend_final.dao.manager;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
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
                          jsonObject.put(metaData.getColumnLabel(columnIndex) ,  rs.getString(metaData.getColumnName(columnIndex)));
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
    
    
    public static JSONArray getJSONFromMToMResultSet(ResultSet rs , int resultSetSize) {
          
        JSONArray jsonArray = new JSONArray();
        int size = resultSetSize;
        JSONObject userJson = null;
        JSONArray roleArray = null;

        int count = 0;
        
        JSONObject[] jarray = new JSONObject[size];
        if(rs!=null)
        {
          try {
              
              ResultSetMetaData metaData = rs.getMetaData();

              
              while(rs.next())
              {

                  JSONObject jsonObject = new JSONObject();
                  for(int columnIndex=1;columnIndex<=metaData.getColumnCount() ;columnIndex++)
                  {
                      if(rs.getString(metaData.getColumnName(columnIndex))!=null)
                          jsonObject.put(metaData.getColumnLabel(columnIndex) ,  rs.getString(metaData.getColumnName(columnIndex)));
                      else
                          jsonObject.put(metaData.getColumnLabel(columnIndex), "");

                  }
                  
                  jarray[count] = jsonObject;
         
                  count++;
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }catch(Exception e) {
              
              e.printStackTrace();
          }
        }
        
        try {

            for(int i=0 ; i<count ; i++)
            {   
                JSONObject jobj1 = jarray[i];   
                
                if(jobj1.getInt("user_id") != -1)
                {

                    roleArray = new JSONArray();
                    
                    userJson = new JSONObject();
                    
                    userJson.put("user_id", jobj1.getInt("user_id"));
                    userJson.put("employee_id", jobj1.getString("employee_id"));
                    userJson.put("is_active", jobj1.getString("is_active"));
                    userJson.put("username", jobj1.getString("username"));
                    userJson.put("email_id", jobj1.getString("email_id"));
                    userJson.put("contact_number", jobj1.getString("contact_number"));
                    userJson.put("last_login_date", jobj1.getString("last_login_date"));

                    roleArray.put(new JSONObject().put("role_description",jobj1.getString("role_description")));

                    for(int j=i+1 ; j<count ; j++)
                    {
                        JSONObject jobj2 = jarray[j];
                        
                        if(jobj1.getInt("user_id") == jobj2.getInt("user_id"))
                        {
    
                            roleArray.put(new JSONObject().put("role_description",jobj2.getString("role_description")));
                            jobj2.put("user_id", -1);
                            
                            break;
                        }
                        
                    }

                    userJson.put("roles", roleArray);
                    jsonArray.put(userJson);
                    
                }
            }

        }catch(Exception e) {
              
              e.printStackTrace();
          }
        
        return jsonArray;

        
    }

    
    
    public static JSONArray getSingleJSONFromMToMResultSet(ResultSet rs , int resultSetSize) {
          
        JSONArray jsonArray = new JSONArray();
        int size = resultSetSize;
        JSONObject userJson = null;
        JSONArray roleArray = null;

        int count = 0;
        
        JSONObject[] jarray = new JSONObject[size];
        if(rs!=null)
        {
          try {
              
              ResultSetMetaData metaData = rs.getMetaData();

              
              while(rs.next())
              {

                  JSONObject jsonObject = new JSONObject();
                  for(int columnIndex=1;columnIndex<=metaData.getColumnCount() ;columnIndex++)
                  {
                      if(rs.getString(metaData.getColumnName(columnIndex))!=null)
                          jsonObject.put(metaData.getColumnLabel(columnIndex) ,  rs.getString(metaData.getColumnName(columnIndex)));
                      else
                          jsonObject.put(metaData.getColumnLabel(columnIndex), "");

                  }
                  
                  jarray[count] = jsonObject;
         
                  count++;
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }catch(Exception e) {
              
              e.printStackTrace();
          }
        }
        
        try {

            for(int i=0 ; i<count ; i++)
            {   
                JSONObject jobj1 = jarray[i];   
                
                if(jobj1.getInt("user_id") != -1)
                {

                    roleArray = new JSONArray();
                    
                    userJson = new JSONObject();
                    
                    userJson.put("user_id", jobj1.getInt("user_id"));
                    userJson.put("employee_id", jobj1.getString("employee_id"));
                    userJson.put("email_id", jobj1.getString("email_id"));
                    userJson.put("prefix", jobj1.getString("prefix"));
                    userJson.put("first_name", jobj1.getString("first_name"));
                    userJson.put("middle_name", jobj1.getString("middle_name"));
                    userJson.put("last_name", jobj1.getString("last_name"));
                    userJson.put("contact_number", jobj1.getString("contact_number"));
                    roleArray.put(new JSONObject().put("role_description",jobj1.getString("role_description")));
                    
                    
                    for(int j=i+1 ; j<count ; j++)
                    {
                        JSONObject jobj2 = jarray[j];
                        if(jobj1.getInt("user_id") == jobj2.getInt("user_id"))
                        {
                            if(jobj2.getString("role_description").equals("admin") || jobj2.getString("role_description").equals("auditor"))
                            
                            roleArray.put(new JSONObject().put("role_description",jobj2.getString("role_description")));
                            jobj2.put("user_id", -1);
                            
                            break;
                        }
                        
                    }

                    userJson.put("roles", roleArray);
                    jsonArray.put(userJson);
                    
                }
            }

        }catch(Exception e) {
              
              e.printStackTrace();
          }
        
        return jsonArray;

        
    }

    
    public void dummySaveUser( ) throws Exception
    {

        try {
            Connection conn = DBConnectionManager.getInstance().getConnection();

            
            conn.setAutoCommit(false);

            PreparedStatement stmt1 = conn.prepareStatement("insert into role_master values(6,'avbcd','Y');");
            PreparedStatement stmt2 = conn.prepareStatement("insert into user_role values(1,1);");
            

           stmt1.executeUpdate();
           stmt2.executeUpdate();

           stmt1.close();
           stmt2.close();
           conn.commit();
           conn.close();
         
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

        
    
    public static int getTRoleId(int tRole)
    {
        int tRoleId;
        switch(tRole)
        {
        case 4 : tRoleId = 3; 
        break;
        case 5 : tRoleId = 4;
        break;
        default : tRoleId = 0;
        

        }
        return tRoleId;
    }
    
    
    public void saveUser(String values ) throws Exception
    {
        int aRole = 0 , tRole = 0 ; 

        int userId;
        try(Connection conn = DBConnectionManager.getInstance().getConnection();)
                
        {
            
            JSONObject userJson = new JSONObject(values);
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement("insert into user_master (employee_id , created_on,is_active)values('"+userJson.getString("employeeId")+"','"+userJson.getString("createdOn")+"','true');",Statement.RETURN_GENERATED_KEYS);
            
            int affectedRows = stmt.executeUpdate();
            
            
            
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            
            Statement stmt2 = conn.createStatement();
            String middleName = null;
            
            try {
                
                 middleName = userJson.getString("middleName");
                
            }catch(JSONException e) {
            }
           
            stmt2.addBatch("insert into user_details (user_id , prefix , first_name , middle_name , last_name , email_id , country_code , contact_number)values("+userId+",'"+userJson.getString("prefix")+"','"+userJson.getString("firstName")+"','"+middleName+"','"+userJson.getString("lastName")+"','"+userJson.getString("email")+"','"+91+"','"+userJson.getString("myPhone")+"');");
            aRole = userJson.getInt("aRole");
            
            if(aRole == 1 || aRole == 2)
            {
                stmt2.addBatch("insert into user_role values("+userId+","+aRole+")");
            }
            
            try
            {
                tRole = userJson.getInt("tRole");
                
                if(tRole == 4 || tRole == 5)
                {
                    stmt2.addBatch("insert into user_role values("+userId+","+getTRoleId(tRole)+")");
                }
            }catch(JSONException e) {
            }
    
            stmt2.executeBatch();
            
            
            
            

            stmt.close();
           stmt2.close();
           conn.commit();
           
         
        } catch (SQLException ex) {
        }
        catch(Exception ex) {
        }
        
    }
    
    
        public JSONArray showAllUser()
        {
            ResultSet userResultSet = null , roleResultSet = null;
            JSONArray jsonArray = null;
            
            final String GET_USER_DETAIL = "select um.user_id,um.employee_id , um.is_active , ud.prefix , ud.first_name , ud.middle_name , ud.last_name ,(ud.prefix || ' ' || ud.first_name || ' ' || ud.middle_name || ' ' || ud.last_name) as username , ud.email_id , ud.contact_number , ud.last_login_date , rm.role_description from user_master as um join user_details as ud ON um.user_id = ud.user_id join user_role as ur ON um.user_id = ur.user_id join role_master as rm ON rm.role_id = ur.role_id;";        
            
            try(
                    Connection conn = DBConnectionManager.getInstance().getConnection();
                    Statement stmt = conn.createStatement();
                ) 
            {
                
                conn.setAutoCommit(false);
                
             
               userResultSet = stmt.executeQuery( GET_USER_DETAIL );
               
               
               
          
               jsonArray = getJSONFromMToMResultSet(userResultSet,100);
               
               userResultSet.close();

               stmt.close();
               conn.commit();
               
               
             
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
            return jsonArray;
        }
        
        /*for testing*/
        
        public JSONArray showAllUserRole()
        {
            ResultSet userResultSet = null , roleResultSet = null;
            JSONArray jsonArray = null;
            final String GET_USER_DETAIL = "select um.user_id,um.employee_id , um.is_active , (ud.prefix || ' ' || ud.first_name || ' ' || ud.middle_name || ' ' || ud.last_name) as username , ud.email_id , ud.contact_number , ud.last_login_date , rm.role_description from user_master as um join user_details as ud ON um.user_id = ud.user_id join user_role as ur ON um.user_id = ur.user_id join role_master as rm ON rm.role_id = ur.role_id;";
                    
            
            try(
                    Connection conn = DBConnectionManager.getInstance().getConnection();
                    Statement stmt = conn.createStatement();
                ) 
            {
                
                conn.setAutoCommit(false);
                
             
               userResultSet = stmt.executeQuery( GET_USER_DETAIL );

          
               jsonArray = getJSONFromMToMResultSet(userResultSet , 100);
               
               userResultSet.close();
               stmt.close();
               conn.commit();
               
               
             
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
            return jsonArray;
        }
        
        
        public JSONArray singleUserDetail(int userId) {
            
            ResultSet resultSet = null;
            JSONArray jsonArray = null;
            final String SELECT_QUERY = "select um.user_id,um.employee_id  , ud.prefix , ud.first_name , ud.middle_name , ud.last_name , ud.contact_number ,ud.email_id ,rm.role_description from user_master as um join user_details as ud ON um.user_id = ud.user_id join user_role as ur ON um.user_id = ur.user_id join role_master as rm ON rm.role_id = ur.role_id where um.user_id = "+userId+" ; ";
            try (Connection conn = DBConnectionManager.getInstance().getConnection();)
            {
                
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
             
               resultSet = stmt.executeQuery( SELECT_QUERY );
               
               
               

               jsonArray = getSingleJSONFromMToMResultSet(resultSet,100);
               
               resultSet.close();
               stmt.close();
               conn.commit();
               
               
             
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
            return jsonArray;
            
        }
        
        private static int getRoleId(String role)
        {
            if(role.equals("Admin")) {
                return 1;
            }
            else if(role.equals("Auditor")) {
                return 2;
            }
            else if(role.equals("Manager")) {
                return 3;
            }
            else if(role.equals("Technician")) {
                return 4;
            }
            return 0;
        }
        
        public void updateUser(String values)
        {
            
            try(Connection conn = DBConnectionManager.getInstance().getConnection();) 
            {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                String currentDateTime = dateTimeFormatter.format(now);
                                 
                
                 JSONObject userJson = new JSONObject(values);
                 int userId;
                 String prefix, firstName, middleName, lastName, contactNumber, emailId ;
                 
                 int aRoleId = 0 , tRoleId = 0 , aRole2Id = 0 , tRole2Id = 0;
                 
                 
                 
                 
                 conn.setAutoCommit(false);
               
                Statement stmt = conn.createStatement();
                
                userId = userJson.getInt("user_id");
                prefix = userJson.getString("prefix");
                firstName = userJson.getString("first_name");
                middleName = userJson.getString("middle_name");
                lastName = userJson.getString("last_name");
                contactNumber = userJson.getString("contact_number");
                emailId = userJson.getString("email_id");
                
                stmt.addBatch("update user_master set modified_on = '"+currentDateTime+"' , modified_by = '"+userId+"' where user_id = "+userId+";");
                stmt.addBatch("update user_details set prefix = '"+prefix+"' , first_name = '"+firstName+"', middle_name = '"+middleName+"', last_name = '"+lastName+"' , email_id = '"+emailId+"' , contact_number = '"+contactNumber+"' where user_id = "+userId+";");
                
                
                
                
                ////assign role to user
                if((userJson.getString("aRole")).equals("Neither") && !(userJson.getString("aRole2")).equals("Neither")) {
                    aRole2Id =  getRoleId(userJson.getString("aRole2"));
                    stmt.addBatch("insert into user_role values("+userId+","+aRole2Id+");");
                    
                }
                ////update user role
                else if(!(userJson.getString("aRole")).equals("Neither") && !(userJson.getString("aRole2")).equals("Neither")) {
                    aRole2Id = getRoleId(userJson.getString("aRole2"));
                    aRoleId = getRoleId(userJson.getString("aRole"));
                    stmt.addBatch("update user_role set role_id = "+aRole2Id+" where user_id ="+userId+" and role_id = "+aRoleId+";");
                    
                }
                ////remove role from user
                else if(!(userJson.getString("aRole")).equals("Neither") && (userJson.getString("aRole2")).equals("Neither")) {
                    aRoleId = getRoleId(userJson.getString("aRole"));
                    stmt.addBatch("delete from user_role where user_id ="+userId+" and role_id = "+aRoleId+";");
                    
                }
                
                
                ////for tRole
                if((userJson.getString("tRole")).equals("Neither") && !(userJson.getString("tRole2")).equals("Neither")) {
                    tRole2Id =  getRoleId(userJson.getString("tRole2"));
                    stmt.addBatch("insert into user_role values("+userId+","+tRole2Id+");");
                    
                }
                else if(!(userJson.getString("tRole")).equals("Neither") && !(userJson.getString("tRole2")).equals("Neither")) {
                    tRole2Id =  getRoleId(userJson.getString("tRole2"));
                    tRoleId = getRoleId(userJson.getString("tRole"));
                    stmt.addBatch("update user_role set role_id = "+tRole2Id+" where user_id ="+userId+" and role_id = "+tRoleId+";");
                    
                }
              //remove role from user
                else if(!(userJson.getString("tRole")).equals("Neither") && (userJson.getString("tRole2")).equals("Neither")) {
                    tRoleId = getRoleId(userJson.getString("tRole"));
                    stmt.addBatch("delete from user_role where user_id ="+userId+" and role_id = "+tRoleId+";");
                    
                }
                
                
                 
                 
                 stmt.executeBatch();
               

               stmt.close();
               conn.commit();
               
               
             
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }catch (Exception ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }
        
        public void deleteUser(int userId)
        {
            final String DELETE_QUERY = "update user_master set is_active = 'false' where user_id = "+userId;
            
            try( Connection conn = DBConnectionManager.getInstance().getConnection();
                 Statement stmt = conn.createStatement();
                ) 
            {
                
                conn.setAutoCommit(false);
                
             
               stmt.executeUpdate( DELETE_QUERY );
               

               conn.commit();
               
             
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }catch (Exception ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
        
        
    

}
