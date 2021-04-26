/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Alfon
 */
public class Framer {
    
    
    public static void frameMsg(byte[] message, OutputStream out) throws IOException {
        out.write(message);
        out.flush();
    }
    
}
