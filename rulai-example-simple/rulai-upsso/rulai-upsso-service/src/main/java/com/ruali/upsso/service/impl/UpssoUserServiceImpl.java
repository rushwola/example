package com.ruali.upsso.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruali.upsso.dao.mapper.UpssoUserMapper;
import com.ruali.upsso.dao.model.UpssoUser;
import com.ruali.upsso.dao.model.UpssoUserExample;
import com.ruali.upsso.facade.UpssoUserService;
import com.rulai.framework.common.annotation.BaseService;
import com.rulai.framework.core.api.service.BaseServiceImpl;

/**
* UpssoUserService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
@BaseService
public class UpssoUserServiceImpl extends BaseServiceImpl<UpssoUserMapper, UpssoUser, UpssoUserExample> implements UpssoUserService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserServiceImpl.class);

    @Autowired
    UpssoUserMapper upssoUserMapper;

	@Override
	public UpssoUser createUser(UpssoUser upmsUser) {
		 UpssoUserExample upmsUserExample = new UpssoUserExample();
	        upmsUserExample.createCriteria()
	                .andUsernameEqualTo(upmsUser.getUsername());
	        long count = upssoUserMapper.countByExample(upmsUserExample);
	        if (count > 0) {
	            return null;
	        }
	        upssoUserMapper.insert(upmsUser);
	        return upmsUser;
	}

}