package com.rulai.tool.velocity.dao.mapper;

import com.rulai.tool.velocity.dao.model.UpssoRole;
import com.rulai.tool.velocity.dao.model.UpssoRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoRoleMapper {
    long countByExample(UpssoRoleExample example);

    int deleteByExample(UpssoRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(UpssoRole record);

    int insertSelective(UpssoRole record);

    List<UpssoRole> selectByExample(UpssoRoleExample example);

    UpssoRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") UpssoRole record, @Param("example") UpssoRoleExample example);

    int updateByExample(@Param("record") UpssoRole record, @Param("example") UpssoRoleExample example);

    int updateByPrimaryKeySelective(UpssoRole record);

    int updateByPrimaryKey(UpssoRole record);
}