package com.ruali.upsso.service.impl;


import com.rulai.framework.core.service.BaseServiceImpl;
import com.ruali.upsso.dao.mapper.UpssoUserMapper;
import com.ruali.upsso.dao.model.UpssoUser;
import com.ruali.upsso.dao.model.UpssoUserExample;
import com.ruali.upsso.facade.UpssoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpssoUserService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoUserServiceImpl extends BaseServiceImpl<UpssoUserMapper, UpssoUser, UpssoUserExample> implements UpssoUserService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserServiceImpl.class);

    @Autowired
    UpssoUserMapper upssoUserMapper;

}