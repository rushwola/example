package com.ruali.upsso.dao.mapper;

import com.ruali.upsso.dao.model.UpssoUserPermission;
import com.ruali.upsso.dao.model.UpssoUserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoUserPermissionMapper {
    long countByExample(UpssoUserPermissionExample example);

    int deleteByExample(UpssoUserPermissionExample example);

    int deleteByPrimaryKey(Integer userPermissionId);

    int insert(UpssoUserPermission record);

    int insertSelective(UpssoUserPermission record);

    List<UpssoUserPermission> selectByExample(UpssoUserPermissionExample example);

    UpssoUserPermission selectByPrimaryKey(Integer userPermissionId);

    int updateByExampleSelective(@Param("record") UpssoUserPermission record, @Param("example") UpssoUserPermissionExample example);

    int updateByExample(@Param("record") UpssoUserPermission record, @Param("example") UpssoUserPermissionExample example);

    int updateByPrimaryKeySelective(UpssoUserPermission record);

    int updateByPrimaryKey(UpssoUserPermission record);
}