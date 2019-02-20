package org.lattice.spectrum_backend_final;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.json.JSONArray;
import org.json.JSONObject;
import org.lattice.spectrum_backend_final.dao.manager.DatabaseManager;
import org.lattice.spectrum_backend_final.dao.manager.ValidationManager;



@Path("myresource")
public class MyResource {
    
    DatabaseManager dbManager = new DatabaseManager() ;
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getIt(String values) {
        DatabaseManager dbManager = new DatabaseManager();
        
        
        try {
            dbManager.saveUser(values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Got it!";
    }
    
    // shows all users
    @GET
    @Path("/all_users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTask() {
        
        JSONArray jsonArray = dbManager.showAllUserRole();
        return jsonArray.toString();
    }
    
    
    @GET
    @Path("/single_user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSingleUser(@PathParam ("userId") int userId) {
        
        JSONArray jsonArray = dbManager.singleUserDetail(userId);
                
        return jsonArray.toString();
    }
    
    
    //add a new task
    @POST
    @Path("/add_user")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTask(String values) throws Exception {
        
        dbManager.saveUser(values);

    }
    
    
    //update task
    @PUT
    @Path("/update_user/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTask( String values) {
        
        JSONObject jsonObject = new JSONObject(values);
          
        dbManager.updateUser(values);
        
        
    }
    
    @POST
    @Path("/check_phone/{phone}")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkPhone(@PathParam ("phone") String phone) {
        
        return ValidationManager.checkPhone(phone);
    }
    
    
    @GET
    @Path("/get_all_phones")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkEmail() {
           
        return ValidationManager.getAllPhones();
    }
    
    @GET
    @Path("/dummy")
    public void Email() {
        
        try {
            dbManager.dummySaveUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //delete a task
    @DELETE
    @Path("/delete_user/{userId}")
    public void deleteTask(@PathParam ("userId") int userId) {
        
        dbManager.deleteUser(userId);
        
    }
    
}
