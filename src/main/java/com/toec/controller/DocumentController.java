package com.toec.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toec.po.Document;
import com.toec.service.DocumentService;
import com.toec.util.CodeUtil;
import com.toec.util.ExcelException;
import com.toec.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping("/add")
    public void add(Document document, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        int result = documentService.add(document);

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
    public void delete(Document document, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        int result = documentService.delete(document);

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
    public void modify(Document document, HttpServletResponse response) throws Exception {

        PrintWriter printWriter = null;
        int result = documentService.modify(document);

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
        List<Document> list;

        list = documentService.find(name);

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
        Document document =  gson.fromJson(jsonObject, Document.class);

        CodeUtil.drawQRCode(document.getQrid(), document.getName(),response);
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(String jsonObject, HttpServletResponse response) throws Exception {

        Gson gson = new Gson();
        List<Document> documents =  gson.fromJson(jsonObject, new TypeToken<List<Document>>(){}.getType());

        try {
            // excel表格的表头，map
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
            fieldMap.put("name","名称");
            fieldMap.put("version","版本");
            fieldMap.put("createtime","创建时间");
            fieldMap.put("modifytime","修改时间");
            fieldMap.put("publish","发布者");
            fieldMap.put("application","用途");
            fieldMap.put("secret","密级");
            fieldMap.put("cipher","密钥");
            fieldMap.put("location","存放位置");

            // excel的sheetName
            String sheetName = "data";

            // 导出
            //将list集合转化为excel
            ExcelUtil.listToExcel(documents, fieldMap, sheetName, response);
        } catch (ExcelException e) {
            e.printStackTrace();
        }

    }
}
