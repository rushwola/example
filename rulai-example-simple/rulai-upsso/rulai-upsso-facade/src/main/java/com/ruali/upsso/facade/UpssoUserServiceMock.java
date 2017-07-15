package com.ruali.upsso.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruali.upsso.dao.mapper.UpssoUserMapper;
import com.ruali.upsso.dao.model.UpssoUser;
import com.ruali.upsso.dao.model.UpssoUserExample;
import com.rulai.framework.core.service.BaseServiceMock;

/**
* 降级实现UpssoUserService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoUserServiceMock extends BaseServiceMock<UpssoUserMapper, UpssoUser, UpssoUserExample> implements UpssoUserService {
	
	 private static Logger _log = LoggerFactory.getLogger(UpssoUserServiceMock.class);
	 
	  @Override
	    public UpssoUser createUser(UpssoUser upmsUser) {
	        return null;
	    }
}
