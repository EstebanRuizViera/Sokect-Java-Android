package com.example.chatserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chatserver.socket.SocketThread;
import com.example.chatserver.socket.WriterThread;

public class MainActivity extends AppCompatActivity {

    //HILO PRINCIPAL: CONEXION SOCKET
    Thread Thread1 = null;

    EditText etIP, etPort, etNick;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;

    String SERVER_IP;
    int SERVER_PORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //OBJETOS DEL XML
        etIP = findViewById(R.id.etIp);
        etPort = findViewById(R.id.etPort);
        etNick = findViewById(R.id.etNick);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        Button btnConnect = findViewById(R.id.btnConnect);


        //EVENTO BOTONES
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectSocket();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    public void connectSocket(){
        tvMessages.setText("");
        SERVER_IP = etIP.getText().toString().trim();
        SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
        Thread1 = new Thread(new SocketThread(this,tvMessages,SERVER_IP,SERVER_PORT));
        Thread1.start();

    }

    public void sendMessage(){
        String message = etMessage.getText().toString().trim();
        if (!message.isEmpty() || !etNick.getText().toString().isEmpty()) {
            new Thread(new WriterThread(this, SocketThread.output,tvMessages,etNick,etMessage,message)).start();
        }
    }
}
