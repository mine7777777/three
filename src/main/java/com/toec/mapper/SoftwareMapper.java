package com.toec.mapper;

import com.toec.po.Software;
import com.toec.po.SoftwareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SoftwareMapper {
    long countByExample(SoftwareExample example);

    int deleteByExample(SoftwareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Software record);

    int insertSelective(Software record);

    List<Software> selectByExample(SoftwareExample example);

    Software selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Software record, @Param("example") SoftwareExample example);

    int updateByExample(@Param("record") Software record, @Param("example") SoftwareExample example);

    int updateByPrimaryKeySelective(Software record);

    int updateByPrimaryKey(Software record);
}