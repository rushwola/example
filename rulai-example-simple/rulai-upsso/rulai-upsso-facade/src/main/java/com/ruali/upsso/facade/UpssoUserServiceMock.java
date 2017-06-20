package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseServiceMock;
import com.ruali.upsso.dao.mapper.UpssoUserMapper;
import com.ruali.upsso.dao.model.UpssoUser;
import com.ruali.upsso.dao.model.UpssoUserExample;

/**
* 降级实现UpssoUserService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoUserServiceMock extends BaseServiceMock<UpssoUserMapper, UpssoUser, UpssoUserExample> implements UpssoUserService {

}
