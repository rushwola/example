package com.ruali.upsso.facade;

import com.alibaba.fastjson.JSONArray;
import com.ruali.upsso.dao.model.UpssoRolePermission;
import com.ruali.upsso.dao.model.UpssoRolePermissionExample;
import com.rulai.framework.core.api.service.BaseService;

/**
* UpssoRolePermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public interface UpssoRolePermissionService extends BaseService<UpssoRolePermission, UpssoRolePermissionExample> {

	  /**
     * 角色权限
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);
}