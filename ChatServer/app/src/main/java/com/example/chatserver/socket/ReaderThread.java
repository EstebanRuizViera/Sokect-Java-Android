package com.example.chatserver.socket;

import android.app.Activity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderThread implements Runnable {

    private Activity activity;
    private Thread thread1;
    private String serverIp;
    private int serverPort;
    private BufferedReader input;
    private TextView tvMessages;


    ReaderThread(Activity activity,BufferedReader input,TextView tvMessages, String serverIp, int serverPort){
        this.activity = activity;
        this.input = input;
        this.tvMessages = tvMessages;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String message = input.readLine();
                if (message != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvMessages.append(message + "\n");
                        }
                    });
                } else {
                    thread1 = new Thread(new SocketThread(activity,tvMessages,serverIp,serverPort));
                    thread1.start();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
