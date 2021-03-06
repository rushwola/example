package com.ruali.upsso.service.impl;


import com.rulai.framework.core.service.BaseServiceImpl;
import com.ruali.upsso.dao.mapper.UpssoLogMapper;
import com.ruali.upsso.dao.model.UpssoLog;
import com.ruali.upsso.dao.model.UpssoLogExample;
import com.ruali.upsso.facade.UpssoLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpssoLogService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoLogServiceImpl extends BaseServiceImpl<UpssoLogMapper, UpssoLog, UpssoLogExample> implements UpssoLogService {

    private static Logger _log = LoggerFactory.getLogger(UpssoLogServiceImpl.class);

    @Autowired
    UpssoLogMapper upssoLogMapper;

}