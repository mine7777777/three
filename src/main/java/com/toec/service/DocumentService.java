package com.toec.service;

import com.toec.po.Document;
import com.toec.po.Software;

import java.util.List;

public interface DocumentService {

    public int add(Document document) throws Exception;

    public int delete(Document document) throws Exception;

    public int modify(Document document) throws Exception;

    public List<Document> find(String name) throws Exception;

}
