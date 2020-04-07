package com.toec.util;

import java.net.Socket;

public class DevInfo {

    private String ip;

    private Socket socket;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
