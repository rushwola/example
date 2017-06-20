package com.ruali.upsso.service.impl;


import com.rulai.framework.core.service.BaseServiceImpl;
import com.ruali.upsso.dao.mapper.UpssoRolePermissionMapper;
import com.ruali.upsso.dao.model.UpssoRolePermission;
import com.ruali.upsso.dao.model.UpssoRolePermissionExample;
import com.ruali.upsso.facade.UpssoRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpssoRolePermissionService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoRolePermissionServiceImpl extends BaseServiceImpl<UpssoRolePermissionMapper, UpssoRolePermission, UpssoRolePermissionExample> implements UpssoRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpssoRolePermissionServiceImpl.class);

    @Autowired
    UpssoRolePermissionMapper upssoRolePermissionMapper;

}