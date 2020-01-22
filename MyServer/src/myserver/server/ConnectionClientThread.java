package myserver.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import myserver.myDatabase.dao.DatabaseCRUD;
import myserver.myDatabase.model.Commentary;

public class ConnectionClientThread extends Thread {

    Socket sk;
    public int sizeOld;
    public int nick;

    public ConnectionClientThread(Socket sk) {
        this.sk = sk;
        this.nick = Math.round((float) Math.random() * 100);
    }

    @Override
    public void run() {
        serveClient(sk);
    }

    private void serveClient(Socket socket) {
        BufferedReader br = null;
        String line = "";
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            //Proyectar mensajes antiguos
            ArrayList<String> commentsOld = DatabaseCRUD.getInfo(String.valueOf(nick));
            sizeOld = commentsOld.size();
            for (int i = 0; i < sizeOld; i++) {
                System.out.println("enviado " + commentsOld.get(i));
                bw.write(commentsOld.get(i));
                bw.newLine();
                bw.flush();
            }

            System.out.println("---- Bienvenido ----");
            do {
                if (br.ready()) {
                    line = br.readLine();
                    DatabaseCRUD.insertCommentary(new Commentary(nick, line));
                }//fin del if
                UpdateComments uptCom = new UpdateComments(bw, String.valueOf(nick), sizeOld);
                try {
                    uptCom.start();
                    Thread.sleep(3000L);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConnectionClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                sizeOld = uptCom.getSizeOld();
                System.out.println("updating...");
            } while (!line.equalsIgnoreCase("FIN"));

            System.out.println("fin del while ");

        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
