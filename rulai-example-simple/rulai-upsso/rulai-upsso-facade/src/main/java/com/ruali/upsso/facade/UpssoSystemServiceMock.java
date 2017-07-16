package com.ruali.upsso.facade;

import com.ruali.upsso.dao.mapper.UpssoSystemMapper;
import com.ruali.upsso.dao.model.UpssoSystem;
import com.ruali.upsso.dao.model.UpssoSystemExample;
import com.rulai.framework.core.api.service.BaseServiceMock;

/**
* 降级实现UpssoSystemService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoSystemServiceMock extends BaseServiceMock<UpssoSystemMapper, UpssoSystem, UpssoSystemExample> implements UpssoSystemService {

}
