package com.ruali.upsso.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruali.upsso.dao.mapper.UpssoOrganizationMapper;
import com.ruali.upsso.dao.model.UpssoOrganization;
import com.ruali.upsso.dao.model.UpssoOrganizationExample;
import com.ruali.upsso.facade.UpssoOrganizationService;
import com.rulai.framework.common.annotation.BaseService;
import com.rulai.framework.core.api.service.BaseServiceImpl;

/**
* UpssoOrganizationService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
@BaseService
public class UpssoOrganizationServiceImpl extends BaseServiceImpl<UpssoOrganizationMapper, UpssoOrganization, UpssoOrganizationExample> implements UpssoOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpssoOrganizationServiceImpl.class);

    @Autowired
    UpssoOrganizationMapper upssoOrganizationMapper;

}