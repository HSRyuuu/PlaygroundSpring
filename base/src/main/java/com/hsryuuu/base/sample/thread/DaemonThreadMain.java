package com.hsryuuu.base.sample.thread;


import static com.hsryuuu.base.sample.thread.util.ThreadLogger.log;

import com.hsryuuu.base.sample.thread.threads.DaemonThread;

public class DaemonThreadMain {
    public static void main(String[] args) {
        log("main() start");
        // daemon thread 가 종료되지 않아도 main()은 종료된다.
        DaemonThread daemonThread = new DaemonThread(true, 5000);
        daemonThread.start();

        // daemon thread 가 아닌 user thread 가 종료되지 않으면 main() 은 종료되지 않는다.
        DaemonThread notDaemonThread = new DaemonThread(false, 3000);
        notDaemonThread.start();
        log("main() end");
    }
}
