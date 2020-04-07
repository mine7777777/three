package com.toec.controller;

import com.google.gson.Gson;
import com.toec.po.Hardware;
import com.toec.service.HardwareService;
import com.toec.util.CodeUtil;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/hardware")
public class HardwareControlller {

    @Autowired
    private HardwareService hardwareService;

    @RequestMapping("/in")
    public void in(Hardware hardware, HttpServletResponse response) throws Exception{

        PrintWriter printWriter = null;
        int result = hardwareService.insertHardware(hardware);

        try
        {
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            if (0 != result){
                printWriter.print("success");
            }
            else {
                printWriter.print("failed");
            }
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

    @RequestMapping("/out")
    public void out(Hardware hardware, HttpServletResponse response) throws Exception{

        PrintWriter printWriter = null;
        int result = hardwareService.deleteHardware(hardware);

        try
        {
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            if (0 != result){
                printWriter.print("success");
            }
            else {
                printWriter.print("failed");
            }
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

    @RequestMapping("/search")
    public void search(HttpServletResponse response, String name) throws Exception{
        String jsonObject;
        PrintWriter printWrite = null;

        List<Hardware> list = hardwareService.selectHardwareByName(name);
        Gson gson = new Gson();
        jsonObject = gson.toJson(list);

        try
        {
            response.setCharacterEncoding("UTF-8");
            printWrite = response.getWriter();
            printWrite.print(jsonObject);
        }
        catch (IOException ex)
        {
            Logger.getLogger("exception");
        }
        finally {
            if(null != printWrite)
            {
                printWrite.flush();
                printWrite.close();
            }
        }
    }

    @RequestMapping("modify")
    public void modify(Hardware hardware, HttpServletResponse response) throws Exception{

        PrintWriter printWriter = null;
        int result = hardwareService.modifyHardware(hardware);

        try
        {
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            if (0 != result){
                printWriter.print("success");
            }
            else {
                printWriter.print("failed");
            }
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

    @RequestMapping("/export")
    public void export(String jsonObject, HttpServletResponse response) throws Exception {

        Gson gson = new Gson();
        Hardware hardware =  gson.fromJson(jsonObject, Hardware.class);

        CodeUtil.drawQRCode(hardware.getQrid(), hardware.getName(),response);
    }
}
