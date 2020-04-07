package com.toec.service.impl;

import com.toec.mapper.SoftwareMapper;
import com.toec.po.Software;
import com.toec.po.SoftwareExample;
import com.toec.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SoftwareServiceImpl implements SoftwareService{

    @Autowired
    private SoftwareMapper softwareMapper;

    public int add(Software software) throws Exception {
        return softwareMapper.insert(software);
    }

    public int delete(Software software) throws Exception {
        return softwareMapper.deleteByPrimaryKey(software.getId());
    }

    public int modify(Software software) throws Exception {
        return softwareMapper.updateByPrimaryKey(software);
    }

    public List<Software> find(String name) throws Exception {

        SoftwareExample softwareExample = new SoftwareExample();
        SoftwareExample.Criteria criteria = softwareExample.createCriteria();

        if ((null != name) && (!name.equals(""))){
            criteria.andNameEqualTo(name);
        }

        List<Software> list = softwareMapper.selectByExample(softwareExample);

        return list;
    }
}
