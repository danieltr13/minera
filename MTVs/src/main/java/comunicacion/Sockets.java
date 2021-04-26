/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alfon
 */
public class Sockets {
    
    private ServerSocket ss;
    private Socket socket;
//    private DelimFramer delimFramer;
    private ArrayList<HiloSocket> sockets;

    public static void main(String[] args) throws IOException {
        Sockets md = new Sockets();
        md.conect();
    }

    public  Sockets() {
        this.sockets = new ArrayList<>();
     }

    public void conect() {
        // don't need to specify a hostname, it will be the current machine
        try {
            this.ss = new ServerSocket(7777);
            System.out.println("ServerSocket awaiting connections...");
            while (true) {
                socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
                System.out.println("Connection from " + socket + "!");
                //quitar izquierda
                HiloSocket hilo = new HiloSocket(socket, this);
                hilo.start();
                sockets.add(hilo);
                //this.listen();
            }
        } catch (IOException ex) {
            Logger.getLogger(Sockets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listen() {
        // get the input stream from the connected socket
        InputStream inputStream;
        try {
            inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            // read the message from the socket
            String message = dataInputStream.readUTF();
            System.out.println("The message sent from the socket was: " + message);

            //this.enviarJsonString(this.obtenerStringJSon(message));
            System.out.println("Closing sockets.");
            ss.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Sockets.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void ajustarContexto(Context context) {
//        this.expression.interpret(context);
//        try {
//            this.enviar(context);
//        } catch (IOException ex) {
//            Logger.getLogger(MiddlewareSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void enviar(Context context) throws IOException {
//        Sockets soc = obtenerSocket(context.getReceptor());
//        if (soc == null) {
//            try {
//                receptorNoEncontrado(context);
//            } catch (JSONException ex) {
//                Logger.getLogger(MiddlewareSocket.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            InputStream inputStream = soc.getInputStream();
//            OutputStream out = soc.getOutputStream();
//
//            if (context.receptor.equalsIgnoreCase("F")) {
//                this.fijoFramer = new FijoFramer(inputStream);
//                this.fijoFramer.frameMsg(context.getOutput(), out);
//            }
//            if (context.receptor.equalsIgnoreCase("D")) {
//                this.delimFramer = new DelimFramer(inputStream);
//                this.delimFramer.frameMsg(context.getOutput(), out);
//            }
//            if (context.receptor.equalsIgnoreCase("J")) {
//                this.jsonFramer = new JsonFramer(inputStream);
//                this.jsonFramer.frameMsg(context.getOutput(), out);
//            }
//        }
//    }
//
//    public Sockets obtenerSocket(String emisor) {
//        for (HiloSocket soc : sockets) {
//            if (soc.getIndentificador().equalsIgnoreCase(emisor)) {
//                return soc.getSocket();
//            }
//        }
//        return null;
//    }
//
//    public void receptorNoEncontrado(Context context) throws IOException, JSONException {
//        //String mensaje = "Cliente-no-encontrado/";
//        String mensaje = "Cliente-no-encontrado";
//        Sockets soc = obtenerSocket(context.getEmisor());
//        InputStream is = soc.getInputStream();
//        OutputStream os = soc.getOutputStream();
//        if (context.getEmisor().equalsIgnoreCase("F")) {
//            mensaje = mensaje.length() + 1 + "-" + mensaje;
//            this.fijoFramer = new FijoFramer(is);
//            this.fijoFramer.frameMsg(mensaje.getBytes(), os);
//        } else if (context.getEmisor().equalsIgnoreCase("D")) {
//            mensaje = mensaje + "/";
//            this.delimFramer = new DelimFramer(is);
//            this.delimFramer.frameMsg(mensaje.getBytes(), os);
//        } else if (context.getEmisor().equalsIgnoreCase("J")) {
//            try {
//                JSONObject js = new JSONObject();
//                js.put("mensaje", mensaje);
//                this.jsonFramer = new JsonFramer(is);
//                this.jsonFramer.frameMsg(js.toString().getBytes(), os);
//            } catch (JSONException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
    
}
