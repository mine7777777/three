package com.toec.service;

import com.toec.po.Software;

import java.util.List;

public interface SoftwareService {

    public int add(Software software) throws Exception;

    public int delete(Software software) throws Exception;

    public int modify(Software software) throws Exception;

    public List<Software> find(String name) throws Exception;

}
