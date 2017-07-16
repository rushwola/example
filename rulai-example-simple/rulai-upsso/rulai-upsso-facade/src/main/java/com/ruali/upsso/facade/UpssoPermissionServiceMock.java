package com.ruali.upsso.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.ruali.upsso.dao.mapper.UpssoPermissionMapper;
import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoPermissionExample;
import com.rulai.framework.core.api.service.BaseServiceMock;

/**
* 降级实现UpssoPermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoPermissionServiceMock extends BaseServiceMock<UpssoPermissionMapper, UpssoPermission, UpssoPermissionExample> implements UpssoPermissionService {

	  private static Logger _log = LoggerFactory.getLogger(UpssoPermissionServiceMock.class);

	    @Override
	    public JSONArray getTreeByRoleId(Integer roleId) {
	        _log.info("UpmsPermissionServiceMock => getTreeByRoleId");
	        return null;
	    }

	    @Override
	    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
	        _log.info("UpmsPermissionServiceMock => getTreeByUserId");
	        return null;
	    }
}
