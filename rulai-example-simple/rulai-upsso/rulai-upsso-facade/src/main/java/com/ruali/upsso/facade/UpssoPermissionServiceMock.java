package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseServiceMock;
import com.ruali.upsso.dao.mapper.UpssoPermissionMapper;
import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoPermissionExample;

/**
* 降级实现UpssoPermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoPermissionServiceMock extends BaseServiceMock<UpssoPermissionMapper, UpssoPermission, UpssoPermissionExample> implements UpssoPermissionService {

}
