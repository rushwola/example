package com.ruali.upsso.facade;

import com.ruali.upsso.dao.model.UpssoUserRole;
import com.ruali.upsso.dao.model.UpssoUserRoleExample;
import com.rulai.framework.core.api.service.BaseService;

/**
* UpssoUserRoleService接口
* Created by shuzheng on 2017/6/20.
*/
public interface UpssoUserRoleService extends BaseService<UpssoUserRole, UpssoUserRoleExample> {
	 /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);
}