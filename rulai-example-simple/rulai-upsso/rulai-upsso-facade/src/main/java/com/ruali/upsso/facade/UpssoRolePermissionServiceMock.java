package com.ruali.upsso.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.ruali.upsso.dao.mapper.UpssoRolePermissionMapper;
import com.ruali.upsso.dao.model.UpssoRolePermission;
import com.ruali.upsso.dao.model.UpssoRolePermissionExample;
import com.rulai.framework.core.api.service.BaseServiceMock;

/**
* 降级实现UpssoRolePermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoRolePermissionServiceMock extends BaseServiceMock<UpssoRolePermissionMapper, UpssoRolePermission, UpssoRolePermissionExample> implements UpssoRolePermissionService {

	 private static Logger _log = LoggerFactory.getLogger(UpssoRolePermissionServiceMock.class);

	    @Override
	    public int rolePermission(JSONArray datas, int id) {
	        _log.info("UpmsRolePermissionServiceMock => rolePermission");
	        return 0;
	    }
}
