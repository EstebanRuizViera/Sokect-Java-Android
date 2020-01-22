package com.example.chatserver.socket;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread implements Runnable {

    public static PrintWriter output;
    public static BufferedReader input;
    private String serverIp;
    private int serverPort;
    private Activity activity;
    private TextView tvMessages;

    public SocketThread(Activity activity,TextView tvMessages, String serverIp, int serverPort){
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.activity = activity;
        this.tvMessages = tvMessages;
    }

    public void run() {
        Socket socket;
        try {

            socket = new Socket(serverIp, serverPort);

            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvMessages.setText("Connected\n");
                }
            });
            new Thread(new ReaderThread(activity,input,tvMessages,serverIp,serverPort)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
