package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseServiceMock;
import com.ruali.upsso.dao.mapper.UpssoRolePermissionMapper;
import com.ruali.upsso.dao.model.UpssoRolePermission;
import com.ruali.upsso.dao.model.UpssoRolePermissionExample;

/**
* 降级实现UpssoRolePermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoRolePermissionServiceMock extends BaseServiceMock<UpssoRolePermissionMapper, UpssoRolePermission, UpssoRolePermissionExample> implements UpssoRolePermissionService {

}
