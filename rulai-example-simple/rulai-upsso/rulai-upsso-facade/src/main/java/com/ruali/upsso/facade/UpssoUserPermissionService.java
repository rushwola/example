package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseService;
import com.alibaba.fastjson.JSONArray;
import com.ruali.upsso.dao.model.UpssoUserPermission;
import com.ruali.upsso.dao.model.UpssoUserPermissionExample;

/**
* UpssoUserPermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public interface UpssoUserPermissionService extends BaseService<UpssoUserPermission, UpssoUserPermissionExample> {
	  /**
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, int id);
}