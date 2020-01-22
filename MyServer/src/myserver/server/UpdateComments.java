/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import myserver.myDatabase.dao.DatabaseCRUD;

/**
 *
 * @author pc
 */
public class UpdateComments extends Thread {

    private BufferedWriter bw;
    private String nick;
    private int sizeOld;

    public UpdateComments(BufferedWriter bw, String nick, int sizeOld) {
        this.bw = bw;
        this.nick = nick;
        this.sizeOld = sizeOld;
    }

    @Override
    public void run() {
        ArrayList<String> comments = DatabaseCRUD.getInfo(nick);
        int total = comments.size();
        if (total != sizeOld) {
            for (int i = sizeOld; i < total; i++) {
                try {
                    System.out.println("enviado " + comments.get(i));
                    bw.write(comments.get(i));
                    bw.newLine();
                    bw.flush();
                } catch (IOException ex) {
                    Logger.getLogger(UpdateComments.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sizeOld = total;
        }
    }

    public int getSizeOld() {
        return sizeOld;
    }
    
    

}
