/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTyrus;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import sender.SenderSemaforo;

/**
 *
 * @author MSI GF63
 */
@ClientEndpoint
public class EndpointSemaforo {

    @OnOpen
    public void open(Session s) {

    }

    @OnMessage
    public void onMessage(String message) {
        if (!message.equalsIgnoreCase("Ya me conecte")) {
            String[] messages;
            messages = message.split(":");
            String s = messages[1].replaceAll(" ", "");
            SenderSemaforo.send(s);
        }
    }

}
