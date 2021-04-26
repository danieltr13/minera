/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;

/**
 *
 * @author Alfon
 */
public class HiloSocket extends Thread {
    private Sockets servidor;
    private Socket socket;
    private String identificador;
    private boolean first = true;
    private int i = 0;
    private int aux;
    private int nextByte;
    private String str;
    private ByteArrayOutputStream messageBuffer;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    @Override
    public void run() {
        try {
            OutputStream out = null;
            InputStream in = null;
            String mensaje = null;
            String inputLine, outputLine;
            in = socket.getInputStream();
            dataInputStream = new DataInputStream(in);
            out = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(out);
            /*
             * En dado caso de que escuche algo
             * //dataInputStream.readUTF()
             */
            messageBuffer = new ByteArrayOutputStream();
            while (true) {
                if (first == true) {
                    nextByte = dataInputStream.readByte();
                    messageBuffer.write(nextByte);
                    str = new String(messageBuffer.toByteArray());
                    System.out.println(str);
                    this.identificar(str);
                    //limpiar
                    messageBuffer.reset();
                    first = false;
                    System.out.println(this.identificador);
                } else {
                    
                }
            }
//            out.close();
//            in.close();
//            socket.close();
        } catch (IOException ex) {
            // Logger.getLogger(HiloSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void notificarEstado(){
    
    
    }
    
    public int numero() throws IOException {
        int nextByte;
        String numero;
        while (true) {
            nextByte = dataInputStream.readByte();
            messageBuffer.write(nextByte);
            numero = new String(messageBuffer.toByteArray());
            if (numero.contains("-")) {
                numero = numero.substring(0, numero.length() - 1);
                return Integer.parseInt(numero);
            }
        }
    }

    public void leerJSON() {
        while (true) {
//            nextByte = dataInputStream.readByte();
            messageBuffer.write(nextByte);
            str = new String(messageBuffer.toByteArray());
            if (str.contains("}")) {
                break;
            }
        }
        System.out.println(str);
        messageBuffer.reset();
    }

    public void identificar(String valor) {
        this.identificador= servidor.cantidadSemaforos()+1;
    }

    public String obtenerMensaje(String[]valores) {
        String mensaje = "";
        for (int i = 0; i < valores.length; i++) {
            if (i > 1) {
                mensaje += "-" + valores[i];
            }
        }
        return mensaje.substring(1, mensaje.length());
    }

    public void crearContextoDelimitador() {
        //asignar id al conectar
        String emisor = this.identificador;
        String receptor = str.substring(str.length() - 2, str.length() - 1);
        String mensaje = str.substring(0, str.length() - 3);
//        Context context = new Context(mensaje, emisor, receptor);
//        System.out.println(context.toString());
//        servidor.ajustarContexto(context);
    }

    public void crearContextoFijo(int numFijo) {
        //asignar id al conectar

        String emisor = this.identificador;
        String receptor = str.substring(str.length() - 1, str.length());
        String mensaje = str.substring(numFijo, str.length() - 2);
//        Context context = new Context(mensaje, emisor, receptor) {};
//        System.out.println(context.toString());
//        servidor.ajustarContexto(context);
    }

    public HiloSocket(Socket socket, Sockets servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

    public String getIndentificador() {
        return identificador;
    }

    public void setIndentificador(String indentificador) {
        this.identificador = indentificador;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
