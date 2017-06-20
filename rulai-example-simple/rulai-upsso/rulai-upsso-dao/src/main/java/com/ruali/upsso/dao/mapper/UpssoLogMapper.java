package com.ruali.upsso.dao.mapper;

import com.ruali.upsso.dao.model.UpssoLog;
import com.ruali.upsso.dao.model.UpssoLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoLogMapper {
    long countByExample(UpssoLogExample example);

    int deleteByExample(UpssoLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(UpssoLog record);

    int insertSelective(UpssoLog record);

    List<UpssoLog> selectByExampleWithBLOBs(UpssoLogExample example);

    List<UpssoLog> selectByExample(UpssoLogExample example);

    UpssoLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") UpssoLog record, @Param("example") UpssoLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UpssoLog record, @Param("example") UpssoLogExample example);

    int updateByExample(@Param("record") UpssoLog record, @Param("example") UpssoLogExample example);

    int updateByPrimaryKeySelective(UpssoLog record);

    int updateByPrimaryKeyWithBLOBs(UpssoLog record);

    int updateByPrimaryKey(UpssoLog record);
}