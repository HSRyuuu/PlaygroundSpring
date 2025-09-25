package com.hsryuuu.base.sample.websocket.testmain;

import com.hsryuuu.base.sample.websocket.ChatClient;
import java.io.IOException;

public class ChatMain2 {

    public static void main(String[] args) throws IOException {
        new ChatClient("KIM", "localhost", 8080).start();
    }
}
