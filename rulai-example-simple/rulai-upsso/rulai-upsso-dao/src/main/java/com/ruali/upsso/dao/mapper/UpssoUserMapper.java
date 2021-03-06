package com.ruali.upsso.dao.mapper;

import com.ruali.upsso.dao.model.UpssoUser;
import com.ruali.upsso.dao.model.UpssoUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoUserMapper {
    long countByExample(UpssoUserExample example);

    int deleteByExample(UpssoUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UpssoUser record);

    int insertSelective(UpssoUser record);

    List<UpssoUser> selectByExample(UpssoUserExample example);

    UpssoUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UpssoUser record, @Param("example") UpssoUserExample example);

    int updateByExample(@Param("record") UpssoUser record, @Param("example") UpssoUserExample example);

    int updateByPrimaryKeySelective(UpssoUser record);

    int updateByPrimaryKey(UpssoUser record);
}