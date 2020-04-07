package com.toec.service.impl;

import com.toec.mapper.DocumentMapper;
import com.toec.po.Document;
import com.toec.po.DocumentExample;
import com.toec.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private DocumentMapper documentMapper;

    public int add(Document document) throws Exception {
        return documentMapper.insert(document);
    }

    public int delete(Document document) throws Exception {
        return documentMapper.deleteByPrimaryKey(document.getId());
    }

    public int modify(Document document) throws Exception {
        return documentMapper.updateByPrimaryKey(document);
    }

    public List<Document> find(String name) throws Exception {

        DocumentExample documentExample = new DocumentExample();
        DocumentExample.Criteria criteria = documentExample.createCriteria();

        if ((null != name) && (!name.equals(""))){
            criteria.andNameEqualTo(name);
        }

        List<Document> list = documentMapper.selectByExample(documentExample);

        return list;
    }
}
