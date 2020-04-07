package com.toec.controller;

import com.google.gson.Gson;
import com.toec.po.Software;
import com.toec.service.SoftwareService;
import com.toec.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/software")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @RequestMapping("/add")
    public void add(Software software, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        int result = softwareService.add(software);

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

    @RequestMapping("/delete")
    public void delete(Software software, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        int result = softwareService.delete(software);

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

    @RequestMapping("/modify")
    public void modify(Software software, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        int result = softwareService.modify(software);

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

    @RequestMapping("/find")
    public void find(String name, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        String jsonObject;
        List<Software> list;

        list = softwareService.find(name);

        Gson gson = new Gson();
        jsonObject = gson.toJson(list);

        try
        {
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            printWriter.print(jsonObject);
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
        Software software =  gson.fromJson(jsonObject, Software.class);

        CodeUtil.drawQRCode(software.getQrid(), software.getName(),response);
    }
}
