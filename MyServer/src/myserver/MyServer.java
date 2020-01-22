package myserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import myserver.server.ConnectionClientThread;

public class MyServer {

    final static int PORT = 40080;

    public static void main(String[] args) {

        try {
            ServerSocket sk = new ServerSocket(PORT);

            while (true) {
                Socket socket = sk.accept();

                new ConnectionClientThread(socket).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

//        DatabaseCRUD data = new DatabaseCRUD();
//        data.insertUser(new User("Pepito"));
//        data.insertCommentary(new Commentary("primer comentario de pepito", 2));
//        data.insertCommentary(new Commentary("segundo comentario de pepito", 2));
//        data.deleteUser(new User("Pepito"));
//        data.editar_modelo(new User("admin"), "farruco");
//        data.getInfo();
    }

}
