package com.ruali.upsso.service.impl;


import com.rulai.framework.core.service.BaseServiceImpl;
import com.ruali.upsso.dao.mapper.UpssoPermissionMapper;
import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoPermissionExample;
import com.ruali.upsso.facade.UpssoPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpssoPermissionService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoPermissionServiceImpl extends BaseServiceImpl<UpssoPermissionMapper, UpssoPermission, UpssoPermissionExample> implements UpssoPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpssoPermissionServiceImpl.class);

    @Autowired
    UpssoPermissionMapper upssoPermissionMapper;

}