package com.rulai.tool.velocity.dao.mapper;

import com.rulai.tool.velocity.dao.model.UpssoSystem;
import com.rulai.tool.velocity.dao.model.UpssoSystemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoSystemMapper {
    long countByExample(UpssoSystemExample example);

    int deleteByExample(UpssoSystemExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(UpssoSystem record);

    int insertSelective(UpssoSystem record);

    List<UpssoSystem> selectByExample(UpssoSystemExample example);

    UpssoSystem selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") UpssoSystem record, @Param("example") UpssoSystemExample example);

    int updateByExample(@Param("record") UpssoSystem record, @Param("example") UpssoSystemExample example);

    int updateByPrimaryKeySelective(UpssoSystem record);

    int updateByPrimaryKey(UpssoSystem record);
}