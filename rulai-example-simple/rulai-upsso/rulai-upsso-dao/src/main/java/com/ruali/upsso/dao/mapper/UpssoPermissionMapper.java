package com.ruali.upsso.dao.mapper;

import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoPermissionMapper {
    long countByExample(UpssoPermissionExample example);

    int deleteByExample(UpssoPermissionExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(UpssoPermission record);

    int insertSelective(UpssoPermission record);

    List<UpssoPermission> selectByExample(UpssoPermissionExample example);

    UpssoPermission selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") UpssoPermission record, @Param("example") UpssoPermissionExample example);

    int updateByExample(@Param("record") UpssoPermission record, @Param("example") UpssoPermissionExample example);

    int updateByPrimaryKeySelective(UpssoPermission record);

    int updateByPrimaryKey(UpssoPermission record);
}