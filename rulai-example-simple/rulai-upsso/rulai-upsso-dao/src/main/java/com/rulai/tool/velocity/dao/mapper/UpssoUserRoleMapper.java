package com.rulai.tool.velocity.dao.mapper;

import com.rulai.tool.velocity.dao.model.UpssoUserRole;
import com.rulai.tool.velocity.dao.model.UpssoUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoUserRoleMapper {
    long countByExample(UpssoUserRoleExample example);

    int deleteByExample(UpssoUserRoleExample example);

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(UpssoUserRole record);

    int insertSelective(UpssoUserRole record);

    List<UpssoUserRole> selectByExample(UpssoUserRoleExample example);

    UpssoUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByExampleSelective(@Param("record") UpssoUserRole record, @Param("example") UpssoUserRoleExample example);

    int updateByExample(@Param("record") UpssoUserRole record, @Param("example") UpssoUserRoleExample example);

    int updateByPrimaryKeySelective(UpssoUserRole record);

    int updateByPrimaryKey(UpssoUserRole record);
}