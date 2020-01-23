package com.piekoszek.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final String CRLF = new String(new byte[]{13, 10});

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7000);
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();

        for (; ; ) {
            if (inputStream.read() == 13) {
                if (inputStream.read() == 10) {
                    if (inputStream.read() == 13) {
                        if (inputStream.read() == 10) {
                            break;
                        }
                    }
                }
            }
        }
        String text = "No dzień dobry, jak to się mówi";
        socket.getOutputStream().write(("HTTP/1.1 200 Dla mnie się podoba" + CRLF
                + "Content-Type: text/html; charset=UTF-8" + CRLF
                + "Content-Length: " + text.getBytes().length + CRLF + CRLF
                + text).getBytes());
    }
}
