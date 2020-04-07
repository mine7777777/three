package com.toec.mapper;

import com.toec.po.Hardware;
import com.toec.po.HardwareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HardwareMapper {
    long countByExample(HardwareExample example);

    int deleteByExample(HardwareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hardware record);

    int insertSelective(Hardware record);

    List<Hardware> selectByExample(HardwareExample example);

    Hardware selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hardware record, @Param("example") HardwareExample example);

    int updateByExample(@Param("record") Hardware record, @Param("example") HardwareExample example);

    int updateByPrimaryKeySelective(Hardware record);

    int updateByPrimaryKey(Hardware record);
}