package com.hsryuuu.base.java.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private final String name;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ChatClient(String name, String host, int port) throws IOException {
        this.name = name;
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        new Thread(this::readMessages).start();
        try (Scanner scanner = new Scanner(System.in)) {
            out.println(name);
            while (true) {
                String msg = scanner.nextLine();
                out.println(msg);
                if (msg.equalsIgnoreCase("quit")) break;
            }
        }
    }

    private void readMessages() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (IOException e) {
            System.out.println("[서버 연결 종료]");
        }
    }
}