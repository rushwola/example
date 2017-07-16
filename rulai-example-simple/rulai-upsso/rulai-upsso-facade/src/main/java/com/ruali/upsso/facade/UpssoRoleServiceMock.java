package com.ruali.upsso.facade;

import com.ruali.upsso.dao.mapper.UpssoRoleMapper;
import com.ruali.upsso.dao.model.UpssoRole;
import com.ruali.upsso.dao.model.UpssoRoleExample;
import com.rulai.framework.core.api.service.BaseServiceMock;

/**
* 降级实现UpssoRoleService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoRoleServiceMock extends BaseServiceMock<UpssoRoleMapper, UpssoRole, UpssoRoleExample> implements UpssoRoleService {

}
