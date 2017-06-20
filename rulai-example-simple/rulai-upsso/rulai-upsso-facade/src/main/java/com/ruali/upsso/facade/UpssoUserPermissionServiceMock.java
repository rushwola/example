package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseServiceMock;
import com.ruali.upsso.dao.mapper.UpssoUserPermissionMapper;
import com.ruali.upsso.dao.model.UpssoUserPermission;
import com.ruali.upsso.dao.model.UpssoUserPermissionExample;

/**
* 降级实现UpssoUserPermissionService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoUserPermissionServiceMock extends BaseServiceMock<UpssoUserPermissionMapper, UpssoUserPermission, UpssoUserPermissionExample> implements UpssoUserPermissionService {

}
