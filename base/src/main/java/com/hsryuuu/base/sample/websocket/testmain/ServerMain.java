package com.hsryuuu.base.sample.websocket.testmain;

import com.hsryuuu.base.sample.websocket.ChatServer;

public class ServerMain {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(8080);
        chatServer.start();
    }
}
