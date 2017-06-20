package com.ruali.upsso.dao.mapper;

import com.ruali.upsso.dao.model.UpssoUserOrganization;
import com.ruali.upsso.dao.model.UpssoUserOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoUserOrganizationMapper {
    long countByExample(UpssoUserOrganizationExample example);

    int deleteByExample(UpssoUserOrganizationExample example);

    int deleteByPrimaryKey(Integer userOrganizationId);

    int insert(UpssoUserOrganization record);

    int insertSelective(UpssoUserOrganization record);

    List<UpssoUserOrganization> selectByExample(UpssoUserOrganizationExample example);

    UpssoUserOrganization selectByPrimaryKey(Integer userOrganizationId);

    int updateByExampleSelective(@Param("record") UpssoUserOrganization record, @Param("example") UpssoUserOrganizationExample example);

    int updateByExample(@Param("record") UpssoUserOrganization record, @Param("example") UpssoUserOrganizationExample example);

    int updateByPrimaryKeySelective(UpssoUserOrganization record);

    int updateByPrimaryKey(UpssoUserOrganization record);
}