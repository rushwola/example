package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseServiceMock;
import com.ruali.upsso.dao.mapper.UpssoUserRoleMapper;
import com.ruali.upsso.dao.model.UpssoUserRole;
import com.ruali.upsso.dao.model.UpssoUserRoleExample;

/**
* 降级实现UpssoUserRoleService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoUserRoleServiceMock extends BaseServiceMock<UpssoUserRoleMapper, UpssoUserRole, UpssoUserRoleExample> implements UpssoUserRoleService {

}
