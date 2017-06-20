package com.ruali.upsso.service.impl;


import com.rulai.framework.core.service.BaseServiceImpl;
import com.ruali.upsso.dao.mapper.UpssoUserPermissionMapper;
import com.ruali.upsso.dao.model.UpssoUserPermission;
import com.ruali.upsso.dao.model.UpssoUserPermissionExample;
import com.ruali.upsso.facade.UpssoUserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpssoUserPermissionService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoUserPermissionServiceImpl extends BaseServiceImpl<UpssoUserPermissionMapper, UpssoUserPermission, UpssoUserPermissionExample> implements UpssoUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserPermissionServiceImpl.class);

    @Autowired
    UpssoUserPermissionMapper upssoUserPermissionMapper;

}