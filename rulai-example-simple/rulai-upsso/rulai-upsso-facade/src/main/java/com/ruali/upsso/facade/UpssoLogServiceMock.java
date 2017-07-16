package com.ruali.upsso.facade;

import com.ruali.upsso.dao.mapper.UpssoLogMapper;
import com.ruali.upsso.dao.model.UpssoLog;
import com.ruali.upsso.dao.model.UpssoLogExample;
import com.rulai.framework.core.api.service.BaseServiceMock;

/**
* 降级实现UpssoLogService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoLogServiceMock extends BaseServiceMock<UpssoLogMapper, UpssoLog, UpssoLogExample> implements UpssoLogService {

}
