package com.toec.controller;

import com.toec.util.DevInfo;
import com.toec.util.GlobalCon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;

@Controller
public class PerformanceController
{
    @RequestMapping(value = "/performance", method = RequestMethod.GET)
    public void dev(String ip, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        DevInfo destdev = null;
        String res = "";

        for (DevInfo dev : GlobalCon.CONNECTIONS_DEV) {
            if (dev.getIp().equals(ip)) {
                destdev = dev;
            }
        }

        if (null != destdev) {
            try {
                Socket socket = destdev.getSocket();
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);

                InputStream is = socket.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                pw.write("performance\n");
                pw.flush();

                String reply = null;
                while(!((reply = br.readLine())==null)) {
                    if (reply.equals("end")){
                        break;
                    }
                    res += (reply + "\n");
                }

                System.out.println(res);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            res = "Ip invalid";
        }


        try
        {
            response.setContentType("text/text;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            printWriter.print(res);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != printWriter)
            {
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}
