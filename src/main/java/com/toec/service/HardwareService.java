package com.toec.service;

import com.toec.po.Hardware;

import java.util.List;

public interface HardwareService {

    public int insertHardware(Hardware hardware) throws Exception;
    public int deleteHardware(Hardware hardware) throws Exception;
    public List<Hardware> selectHardwareByName(String name) throws Exception;
    public int modifyHardware(Hardware hardware) throws Exception;
}
