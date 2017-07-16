package com.ruali.upsso.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruali.upsso.dao.mapper.UpssoRoleMapper;
import com.ruali.upsso.dao.model.UpssoRole;
import com.ruali.upsso.dao.model.UpssoRoleExample;
import com.ruali.upsso.facade.UpssoRoleService;
import com.rulai.framework.core.api.service.BaseServiceImpl;

/**
* UpssoRoleService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoRoleServiceImpl extends BaseServiceImpl<UpssoRoleMapper, UpssoRole, UpssoRoleExample> implements UpssoRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpssoRoleServiceImpl.class);

    @Autowired
    UpssoRoleMapper upssoRoleMapper;

}