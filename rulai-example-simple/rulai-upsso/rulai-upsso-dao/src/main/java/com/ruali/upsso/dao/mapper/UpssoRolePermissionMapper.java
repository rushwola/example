package com.ruali.upsso.dao.mapper;

import com.ruali.upsso.dao.model.UpssoRolePermission;
import com.ruali.upsso.dao.model.UpssoRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoRolePermissionMapper {
    long countByExample(UpssoRolePermissionExample example);

    int deleteByExample(UpssoRolePermissionExample example);

    int deleteByPrimaryKey(Integer rolePermissionId);

    int insert(UpssoRolePermission record);

    int insertSelective(UpssoRolePermission record);

    List<UpssoRolePermission> selectByExample(UpssoRolePermissionExample example);

    UpssoRolePermission selectByPrimaryKey(Integer rolePermissionId);

    int updateByExampleSelective(@Param("record") UpssoRolePermission record, @Param("example") UpssoRolePermissionExample example);

    int updateByExample(@Param("record") UpssoRolePermission record, @Param("example") UpssoRolePermissionExample example);

    int updateByPrimaryKeySelective(UpssoRolePermission record);

    int updateByPrimaryKey(UpssoRolePermission record);
}