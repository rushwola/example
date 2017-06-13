package com.rulai.tool.velocity.dao.mapper;

import com.rulai.tool.velocity.dao.model.UpmsRole;
import com.rulai.tool.velocity.dao.model.UpmsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsRoleMapper {
    long countByExample(UpmsRoleExample example);

    int deleteByExample(UpmsRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(UpmsRole record);

    int insertSelective(UpmsRole record);

    List<UpmsRole> selectByExample(UpmsRoleExample example);

    UpmsRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") UpmsRole record, @Param("example") UpmsRoleExample example);

    int updateByExample(@Param("record") UpmsRole record, @Param("example") UpmsRoleExample example);

    int updateByPrimaryKeySelective(UpmsRole record);

    int updateByPrimaryKey(UpmsRole record);
}