package com.ruali.upsso.facade;

import com.ruali.upsso.dao.model.UpssoUser;
import com.ruali.upsso.dao.model.UpssoUserExample;
import com.rulai.framework.core.api.service.BaseService;

/**
* UpssoUserService接口
* Created by shuzheng on 2017/6/20.
*/
public interface UpssoUserService extends BaseService<UpssoUser, UpssoUserExample> {

	 UpssoUser createUser(UpssoUser upmsUser);
}