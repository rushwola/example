package com.ruali.upsso.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruali.upsso.dao.mapper.UpssoUserOrganizationMapper;
import com.ruali.upsso.dao.model.UpssoUserOrganization;
import com.ruali.upsso.dao.model.UpssoUserOrganizationExample;
import com.ruali.upsso.facade.UpssoUserOrganizationService;
import com.rulai.framework.core.service.BaseServiceImpl;
import com.rulai.tool.core.convert.Convert;
import com.rulai.tool.core.util.StrUtil;

/**
* UpssoUserOrganizationService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoUserOrganizationServiceImpl extends BaseServiceImpl<UpssoUserOrganizationMapper, UpssoUserOrganization, UpssoUserOrganizationExample> implements UpssoUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserOrganizationServiceImpl.class);

    @Autowired
    UpssoUserOrganizationMapper upssoUserOrganizationMapper;

	@Override
	public int organization(String[] organizationIds, int id) {
		 int result = 0;
	        // 删除旧记录
	        UpssoUserOrganizationExample upmsUserOrganizationExample = new UpssoUserOrganizationExample();
	        upmsUserOrganizationExample.createCriteria()
	                .andUserIdEqualTo(id);
	        upssoUserOrganizationMapper.deleteByExample(upmsUserOrganizationExample);
	        // 增加新记录
	        if (null != organizationIds) {
	            for (String organizationId : organizationIds) {
	                if (StrUtil.isBlank(organizationId)) {
	                    continue;
	                }
	                UpssoUserOrganization upmsUserOrganization = new UpssoUserOrganization();
	                upmsUserOrganization.setUserId(id);
	                upmsUserOrganization.setOrganizationId(Convert.toInt(organizationId));
	                result = upssoUserOrganizationMapper.insertSelective(upmsUserOrganization);
	            }
	        }
	        return result;
	}

}