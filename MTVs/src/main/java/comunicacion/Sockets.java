/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 * meter clase dentro de un hilo para el momento de ejecutarla
 */
public class Sockets {

    private ServerSocket ss;
    private Socket socket;
//    private DelimFramer delimFramer;
    private ArrayList<HiloSocket> sockets;
    private Gson gson;
    private ManejoComunicaciones mComunicaciones;

    public Sockets(ManejoComunicaciones mComunicaciones) {
        this.sockets = new ArrayList<>();
        gson = new Gson();
        this.mComunicaciones= mComunicaciones;
        this.conect();
    }

    public String cantidadSemaforos() {
       
        return String.valueOf(this.sockets.size());
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

    public void enviar(byte[] semaforo,Socket socket) throws IOException {
        Socket soc = socket;
        if (soc == null) {
             

        } else {
            this.mComunicaciones.notificarCambioEstado(semaforo);
        }
    }

    public void cambiarEstado(byte[] semaforo) throws IOException {
        JsonObject json= bytesToJson(semaforo);
        String id= json.get("id").getAsString();
        Socket sc= this.obtenerSocket(id);
        if (sc!=null) {
            OutputStream out= sc.getOutputStream();
            out.write(semaforo);
        }
    }

    public JsonObject bytesToJson(byte[] semaforo) {
        String tLight = new String(semaforo, StandardCharsets.UTF_8);
        return gson.fromJson(tLight, JsonObject.class);
    }


    public Socket obtenerSocket(String emisor) {
        for (HiloSocket soc : sockets) {
            if (soc.getIndentificador().equalsIgnoreCase(emisor)) {
                return soc.getSocket();
            }
        }
        return null;
    }
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
//    
}
