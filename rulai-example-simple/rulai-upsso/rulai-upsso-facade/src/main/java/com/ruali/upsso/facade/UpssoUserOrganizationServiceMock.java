package com.ruali.upsso.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruali.upsso.dao.mapper.UpssoUserOrganizationMapper;
import com.ruali.upsso.dao.model.UpssoUserOrganization;
import com.ruali.upsso.dao.model.UpssoUserOrganizationExample;
import com.rulai.framework.core.service.BaseServiceMock;

/**
 * 降级实现UpssoUserOrganizationService接口 Created by shuzheng on 2017/6/20.
 */
public class UpssoUserOrganizationServiceMock
		extends BaseServiceMock<UpssoUserOrganizationMapper, UpssoUserOrganization, UpssoUserOrganizationExample>
		implements UpssoUserOrganizationService {
	
	
	private static Logger _log = LoggerFactory.getLogger(UpssoUserOrganizationServiceMock.class);

	@Override
	public int organization(String[] organizationIds, int id) {
		_log.info("UpmsUserOrganizationServiceMock => organization");
		return 0;
	}
}
