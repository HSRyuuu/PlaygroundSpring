package com.hsryuuu.base.sample.websocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    private final int port;
    private final Set<ClientHandler> clients;

    public ChatServer(int port){
        this.port = port;
        this.clients = ConcurrentHashMap.newKeySet();
    }

    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[서버 시작: port=" + port + "]");
            while(true){
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket, this);
                clients.add(client);
                new Thread(client).start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void broadcast(String message){
        for(ClientHandler client : clients){
            client.send(message);
        }
    }

    public void remove(ClientHandler client){
        clients.remove(client);
    }

}
