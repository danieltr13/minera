/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyrusRabbitCliente;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author MSI GF63
 */
@ClientEndpoint
public class EndPoint {
      
    @OnOpen
    public void open(Session s){
        
    }
    

    @OnMessage
    public void onMessage(String message) { 
       System.out.println("Received msg: "+message);        
    }    
}
