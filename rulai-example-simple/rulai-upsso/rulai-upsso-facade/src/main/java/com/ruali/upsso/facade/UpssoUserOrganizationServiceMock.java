package com.ruali.upsso.facade;

import com.rulai.framework.core.service.BaseServiceMock;
import com.ruali.upsso.dao.mapper.UpssoUserOrganizationMapper;
import com.ruali.upsso.dao.model.UpssoUserOrganization;
import com.ruali.upsso.dao.model.UpssoUserOrganizationExample;

/**
* 降级实现UpssoUserOrganizationService接口
* Created by shuzheng on 2017/6/20.
*/
public class UpssoUserOrganizationServiceMock extends BaseServiceMock<UpssoUserOrganizationMapper, UpssoUserOrganization, UpssoUserOrganizationExample> implements UpssoUserOrganizationService {

}
