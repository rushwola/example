package com.ruali.upsso.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruali.upsso.dao.mapper.UpssoUserRoleMapper;
import com.ruali.upsso.dao.model.UpssoUserRole;
import com.ruali.upsso.dao.model.UpssoUserRoleExample;
import com.rulai.framework.core.service.BaseServiceMock;

/**
 * 降级实现UpssoUserRoleService接口 Created by shuzheng on 2017/6/20.
 */
public class UpssoUserRoleServiceMock extends BaseServiceMock<UpssoUserRoleMapper, UpssoUserRole, UpssoUserRoleExample>
		implements UpssoUserRoleService {
	private static Logger _log = LoggerFactory.getLogger(UpssoUserRoleServiceMock.class);

	@Override
	public int role(String[] roleIds, int id) {
		_log.info("UpmsUserRoleServiceMock => role");
		return 0;
	}

}
