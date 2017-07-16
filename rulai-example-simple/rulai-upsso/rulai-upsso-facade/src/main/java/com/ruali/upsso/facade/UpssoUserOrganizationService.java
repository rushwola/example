package com.ruali.upsso.facade;

import com.ruali.upsso.dao.model.UpssoUserOrganization;
import com.ruali.upsso.dao.model.UpssoUserOrganizationExample;
import com.rulai.framework.core.api.service.BaseService;

/**
* UpssoUserOrganizationService接口
* Created by shuzheng on 2017/6/20.
*/
public interface UpssoUserOrganizationService extends BaseService<UpssoUserOrganization, UpssoUserOrganizationExample> {

    /**
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);
}