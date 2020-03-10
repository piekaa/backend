package pl.piekoszek.backend.server.tcp;

import pl.piekoszek.backend.server.Connection;
import pl.piekoszek.backend.server.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    ServerSocket serverSocket;
    ConnectionHandler connectionHandler;

    public TcpServer(ConnectionHandler connectionHandler) throws IOException {
        this.connectionHandler = connectionHandler;
        serverSocket = new ServerSocket(7000);
        for (; ; ) {
            Socket socket = serverSocket.accept();
            Connection connection = new Connection(socket.getInputStream(), socket.getOutputStream());
            new Thread(() -> {
                connectionHandler.handle(connection);
                try {
                    System.out.println("Connection closed");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}