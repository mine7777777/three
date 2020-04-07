package com.toec.service.impl;

import com.toec.mapper.HardwareMapper;
import com.toec.po.Hardware;
import com.toec.po.HardwareExample;
import com.toec.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class HardwareServiceImpl implements HardwareService {
    @Autowired
    private HardwareMapper hardwareMapper;

    @Override
    public int insertHardware(Hardware hardware) throws Exception{
        return hardwareMapper.insert(hardware);
    }

    @Override
    public int deleteHardware(Hardware hardware) throws Exception{
        return hardwareMapper.deleteByPrimaryKey(hardware.getId());
    }

    @Override
    public List<Hardware> selectHardwareByName(String name) throws Exception{
        HardwareExample hardwareExample = new HardwareExample();
        HardwareExample.Criteria criteria = hardwareExample.createCriteria();
        if((null == name) || (true == name.equals(""))){

        }
        else{
            criteria.andNameEqualTo(name);
        }

        List<Hardware> list = hardwareMapper.selectByExample(hardwareExample);
        return list;
    }

    @Override
    public int modifyHardware(Hardware hardware) throws Exception{
        return hardwareMapper.updateByPrimaryKey(hardware);
    }
}
