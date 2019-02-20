package org.lattice.spectrum_backend_final;


import org.json.JSONArray;
import org.lattice.spectrum_backend_final.dao.manager.DatabaseManager;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;


public class SocketEvent {
    
    
    public static void events(final SocketIOServer socketServer)
    {
        
        final DatabaseManager dbManager = new DatabaseManager();

         socketServer.addConnectListener(new ConnectListener() {
                
                @Override
                public void onConnect(SocketIOClient client) {
                    System.out.println("-----------connect-----------");
                    JSONArray jsonArray = dbManager.showAllUser();
                    
                    socketServer.getBroadcastOperations().sendEvent("add", jsonArray.toString());
                    
                    
                }
            });
         
         

         
         socketServer.addEventListener("createUser", Object.class,
                 new DataListener<Object>() {
    
                        @Override
                        public void onData(SocketIOClient client, Object values, AckRequest ackSender) throws Exception {
                            
                       
                            dbManager.saveUser(values.toString());
                            JSONArray jsonArray = dbManager.showAllUser();
                                                    
                            socketServer.getBroadcastOperations().sendEvent("add", jsonArray.toString());
                            

                        }
                 });
         
         
         
         socketServer.addEventListener("singleUserDetail", Integer.class,
                 new DataListener<Integer>() {
    
                        @Override
                        public void onData(SocketIOClient client, Integer userId, AckRequest ackSender) throws Exception {
                            
                            JSONArray jsonArray = dbManager.singleUserDetail(userId);
                            
                            socketServer.getBroadcastOperations().sendEvent("singleUserDetail", jsonArray.toString());
                      
                        }
                 });
    }

}
