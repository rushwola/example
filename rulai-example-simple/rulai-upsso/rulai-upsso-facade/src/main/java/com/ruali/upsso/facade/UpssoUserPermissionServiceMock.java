package com.ruali.upsso.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.ruali.upsso.dao.mapper.UpssoUserPermissionMapper;
import com.ruali.upsso.dao.model.UpssoUserPermission;
import com.ruali.upsso.dao.model.UpssoUserPermissionExample;
import com.rulai.framework.core.service.BaseServiceMock;

/**
* 降级实现UpssoUserPermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoUserPermissionServiceMock extends BaseServiceMock<UpssoUserPermissionMapper, UpssoUserPermission, UpssoUserPermissionExample> implements UpssoUserPermissionService {
	 private static Logger _log = LoggerFactory.getLogger(UpssoUserPermissionServiceMock.class);

	    @Override
	    public int permission(JSONArray datas, int id) {
	        _log.info("UpmsUserPermissionServiceMock => permission");
	        return 0;
	    }
}
