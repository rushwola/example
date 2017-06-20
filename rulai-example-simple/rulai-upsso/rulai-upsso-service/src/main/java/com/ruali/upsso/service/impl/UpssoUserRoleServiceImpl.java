package com.ruali.upsso.service.impl;


import com.rulai.framework.core.service.BaseServiceImpl;
import com.ruali.upsso.dao.mapper.UpssoUserRoleMapper;
import com.ruali.upsso.dao.model.UpssoUserRole;
import com.ruali.upsso.dao.model.UpssoUserRoleExample;
import com.ruali.upsso.facade.UpssoUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpssoUserRoleService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoUserRoleServiceImpl extends BaseServiceImpl<UpssoUserRoleMapper, UpssoUserRole, UpssoUserRoleExample> implements UpssoUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserRoleServiceImpl.class);

    @Autowired
    UpssoUserRoleMapper upssoUserRoleMapper;

}