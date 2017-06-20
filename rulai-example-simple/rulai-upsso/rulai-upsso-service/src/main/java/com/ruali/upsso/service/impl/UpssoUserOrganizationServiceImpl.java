package com.ruali.upsso.service.impl;


import com.rulai.framework.core.service.BaseServiceImpl;
import com.ruali.upsso.dao.mapper.UpssoUserOrganizationMapper;
import com.ruali.upsso.dao.model.UpssoUserOrganization;
import com.ruali.upsso.dao.model.UpssoUserOrganizationExample;
import com.ruali.upsso.facade.UpssoUserOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpssoUserOrganizationService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoUserOrganizationServiceImpl extends BaseServiceImpl<UpssoUserOrganizationMapper, UpssoUserOrganization, UpssoUserOrganizationExample> implements UpssoUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserOrganizationServiceImpl.class);

    @Autowired
    UpssoUserOrganizationMapper upssoUserOrganizationMapper;

}