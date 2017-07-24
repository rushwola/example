package com.ruali.upsso.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruali.upsso.dao.mapper.UpssoSystemMapper;
import com.ruali.upsso.dao.model.UpssoSystem;
import com.ruali.upsso.dao.model.UpssoSystemExample;
import com.ruali.upsso.facade.UpssoSystemService;
import com.rulai.framework.common.annotation.BaseService;
import com.rulai.framework.core.api.service.BaseServiceImpl;

/**
* UpssoSystemService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
@BaseService
public class UpssoSystemServiceImpl extends BaseServiceImpl<UpssoSystemMapper, UpssoSystem, UpssoSystemExample> implements UpssoSystemService {

    private static Logger _log = LoggerFactory.getLogger(UpssoSystemServiceImpl.class);

    @Autowired
    UpssoSystemMapper upssoSystemMapper;

}