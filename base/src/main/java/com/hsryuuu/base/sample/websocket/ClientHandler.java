package com.hsryuuu.base.sample.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final ChatServer chatServer;
    private String name;
    private PrintWriter out;
    private BufferedReader in;


    public ClientHandler(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
    }

    @Override
    public void run() {
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            name = in.readLine();
            System.out.println("[접속: " + this.name + "]");
            chatServer.broadcast("[알림] " + this.name + " 님이 입장했습니다.");

            String msg;
            while ((msg = in.readLine()) != null) {
                if (msg.equalsIgnoreCase("quit")) break;
                chatServer.broadcast(name + ": " + msg);
            }


        }catch(IOException e){

        }finally {
            try { socket.close(); } catch (IOException ignored) {}
            chatServer.remove(this);
            chatServer.broadcast("[알림] " + name + " 님이 퇴장했습니다.");
        }
    }

    public void send(String message) {
        out.println(message);
    }
}
