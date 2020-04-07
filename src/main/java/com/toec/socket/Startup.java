package com.toec.socket;

import com.toec.util.DevInfo;
import com.toec.util.GlobalCon;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Startup implements ServletContextListener {

    private  Server server = null;

    public void contextDestroyed(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        // 在整个web应用销毁之前调用，将所有应用空间所设置的内容清空
        sc.removeAttribute("dataSource");
        server.interrupt();
        System.out.println("销毁工作完成...");
    }

    public void contextInitialized(ServletContextEvent event) {
        server = new Server(8088);
        if (null != server.server)
        {
            server.start();
        }
    }

    public class Server extends Thread {
        ServerSocket server = null;
        Socket sk = null;

        private Server(int port) {
            try {
                server = new ServerSocket(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {

            while (true) {
                System.out.println("Listenning...");
                try {
//                  每个请求交给一个线程去处理
                    sk = server.accept();
                    if (null != sk){
                        DevInfo dev =  new DevInfo();
                        dev.setSocket(sk);
                        dev.setIp(sk.getInetAddress().toString().substring(1));
                        GlobalCon.CONNECTIONS_DEV.add(dev);
                    }

                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }


}
