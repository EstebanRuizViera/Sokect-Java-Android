package com.example.chatserver.socket;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import java.io.PrintWriter;

public class WriterThread implements Runnable {

    private String message;
    private TextView tvMessages;
    private EditText etMessage, etNick;
    private PrintWriter output;
    private Activity activity;

    public WriterThread(Activity activity,PrintWriter output, TextView tvMessages,EditText etNick, EditText etMessage, String message) {
        this.message = message+"\n";
        this.output = output;
        this.tvMessages = tvMessages;
        this.etMessage = etMessage;
        this.etNick = etNick;
        this.activity = activity;
    }

    @Override
    public void run() {

        output.write(etNick.getText().toString()+": "+message);

        output.flush();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvMessages.append(etNick.getText().toString()+": " + message );
                etMessage.setText("");
            }
        });
    }
}
