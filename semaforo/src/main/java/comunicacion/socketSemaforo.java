/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.io.DataOutputStream;

/**
 *
 * @author Alfon
 */
public class socketSemaforo extends Thread {
    private SemaforoFRM semaforoFRM;
    private Socket socket;
    private InputStream in = null;
    private OutputStream out = null;
    private ByteArrayOutputStream messageBuffer;
    private String mensaje;
    private DataInputStream dataInputStream;

    public socketSemaforo(SemaforoFRM frm) {
        messageBuffer = new ByteArrayOutputStream();
        this.semaforoFRM= frm;
    }

    public void conectar() {
        try {
            // need host and port, we want to connect to the ServerSocket at port 7777
            socket = new Socket("localhost", 7777);
            in = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(in);
            this.out = socket.getOutputStream();
            //0 Para delimitado
            //1 Para fijo
            //2 Json
            out.write("0".getBytes());
            out.flush();
            int nextByte;
            String str;
            while (true) {
                nextByte = dataInputStream.readByte();
                messageBuffer.write(nextByte);
                str = new String(messageBuffer.toByteArray());
                System.out.println(str);
                this.mensaje = new String(str);
                if (mensaje.contains("/")) {
                    mensaje = mensaje.substring(0, mensaje.length() - 1);
                    this.mensaje = mensaje;
                    notificarUsuario();
                    messageBuffer.reset();
                    this.mensaje = null;
                }

            }
        } catch (IOException ex) {

        }

    }

    public void escuchar() {
        int nextByte;
        String msg;
        while (true) {
            try {
                nextByte = dataInputStream.readByte();
                messageBuffer.write(nextByte);
                msg = new String(messageBuffer.toByteArray());
                System.out.println(msg);
                while (true) {
                    if (this.dataInputStream.available() > 0) {
                        msg = msg.substring(0, msg.length() - 1);
                        this.mensaje = msg;
                    }else{
                        this.notificarUsuario();
                        messageBuffer.reset();
                        break;
                    }
                }
            } catch (IOException ex) {
            }
        }
    }

    //cambiar estado
    public void notificarUsuario() throws IOException {
        //método que llama 
        this.sendString(this.mensaje);
    }

    public void sendString(String values) throws IOException {

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create a data output stream from the output stream so we can send data through it
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        System.out.println("Sending string to the ServerSocket");

        System.out.println(values);
        // write the message we want to send
        dataOutputStream.writeUTF(values);
        dataOutputStream.flush(); // send the message
        //dataOutputStream.close(); // close the output stream when we're done.

        System.out.println("Closing socket and terminating program.");
        //socket.close();
    }

    public void sendValues(String values) throws IOException {
        this.out = socket.getOutputStream();
        Framer.frameMsg(values.getBytes(), out);
    }

    @Override
    public void run() {
        try {
            this.conectar();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
