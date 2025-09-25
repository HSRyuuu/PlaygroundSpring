package com.hsryuuu.base.java.websocket.testmain;

import com.hsryuuu.base.java.websocket.ChatClient;
import java.io.IOException;

public class ChatMain1 {

    public static void main(String[] args) throws IOException {
        new ChatClient("PARK", "localhost", 8080).start();
    }
}
