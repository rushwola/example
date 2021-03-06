package com.ruali.upsso.dao.mapper;

import com.ruali.upsso.dao.model.UpssoOrganization;
import com.ruali.upsso.dao.model.UpssoOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpssoOrganizationMapper {
    long countByExample(UpssoOrganizationExample example);

    int deleteByExample(UpssoOrganizationExample example);

    int deleteByPrimaryKey(Integer organizationId);

    int insert(UpssoOrganization record);

    int insertSelective(UpssoOrganization record);

    List<UpssoOrganization> selectByExample(UpssoOrganizationExample example);

    UpssoOrganization selectByPrimaryKey(Integer organizationId);

    int updateByExampleSelective(@Param("record") UpssoOrganization record, @Param("example") UpssoOrganizationExample example);

    int updateByExample(@Param("record") UpssoOrganization record, @Param("example") UpssoOrganizationExample example);

    int updateByPrimaryKeySelective(UpssoOrganization record);

    int updateByPrimaryKey(UpssoOrganization record);
}