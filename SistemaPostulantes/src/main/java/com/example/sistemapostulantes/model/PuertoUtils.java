package com.example.sistemapostulantes.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class PuertoUtils {
    public static boolean isPortAvailable(int port) {
        try (ServerSocket ss = new ServerSocket()) {
            ss.setReuseAddress(true);
            ss.bind(new InetSocketAddress("0.0.0.0", port));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean waitForPortToBeFree(int port, long timeoutMillis) {
        long deadline = System.currentTimeMillis() + timeoutMillis;
        while (System.currentTimeMillis() < deadline) {
            if (isPortAvailable(port)) return true;
            try { Thread.sleep(100); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); break; }
        }
        return false;
    }
}
