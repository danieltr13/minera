/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientTyrus;

import consumer.ConsumerNotificacion;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Alfon
 */
public class ClienteWebSocketNotificacion {
    private static Object waitLock = new Object();
    private ConsumerNotificacion cs ;
    private RemoteEndpoint.Basic basicRemote;
    
    public ClienteWebSocketNotificacion(){
         this.conection();
    }
    
    public void conection() {
        WebSocketContainer container = null;//
        Session session = null;
        try {
            //Tyrus is plugged via ServiceLoader API. See notes above
            container = ContainerProvider.getWebSocketContainer();
            //WS1 is the context-root of my web.app
            //ratesrv is the  path given in the ServerEndPoint annotation on server implementation
            session = container.connectToServer(EndPointNotificacion.class,
                    URI.create("ws://localhost:8080/Cliente/websocketendpointnotificacion"));
            basicRemote = session.getBasicRemote();
            this.cs = new ConsumerNotificacion(this);
            String msj = null;
            Scanner sc = new Scanner(System.in);
            do {
                msj = sc.nextLine();
                basicRemote.sendText(msj);
            } while (!msj.equals("ya me voy"));
        } catch (DeploymentException | IOException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void enviarClientes(String msj) {
        try {
            System.out.println("hola:" + msj);
            basicRemote.sendText(msj);
        } catch (IOException ex) {
            Logger.getLogger(EndPointNotificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
