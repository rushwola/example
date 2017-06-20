package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseServiceMock;
import com.ruali.upsso.dao.mapper.UpssoOrganizationMapper;
import com.ruali.upsso.dao.model.UpssoOrganization;
import com.ruali.upsso.dao.model.UpssoOrganizationExample;

/**
* 降级实现UpssoOrganizationService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoOrganizationServiceMock extends BaseServiceMock<UpssoOrganizationMapper, UpssoOrganization, UpssoOrganizationExample> implements UpssoOrganizationService {

}
