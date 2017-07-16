package com.ruali.upsso.facade;

import com.alibaba.fastjson.JSONArray;
import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoPermissionExample;
import com.rulai.framework.core.api.service.BaseService;

/**
* UpssoPermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public interface UpssoPermissionService extends BaseService<UpssoPermission, UpssoPermissionExample> {
	
	 JSONArray getTreeByRoleId(Integer roleId);

	    JSONArray getTreeByUserId(Integer usereId, Byte type);

}