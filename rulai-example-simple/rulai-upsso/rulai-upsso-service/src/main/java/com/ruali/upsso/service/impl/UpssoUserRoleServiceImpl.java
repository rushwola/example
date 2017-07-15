package com.ruali.upsso.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;

import com.ruali.upsso.dao.mapper.UpssoUserRoleMapper;
import com.ruali.upsso.dao.model.UpssoUserRole;
import com.ruali.upsso.dao.model.UpssoUserRoleExample;
import com.ruali.upsso.facade.UpssoUserRoleService;
import com.rulai.framework.core.service.BaseServiceImpl;
import com.rulai.tool.core.convert.Convert;
import com.rulai.tool.core.util.StrUtil;

/**
* UpssoUserRoleService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoUserRoleServiceImpl extends BaseServiceImpl<UpssoUserRoleMapper, UpssoUserRole, UpssoUserRoleExample> implements UpssoUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserRoleServiceImpl.class);

    @Autowired
    UpssoUserRoleMapper upssoUserRoleMapper;

	@Override
	public int role(String[] roleIds, int id) {
		 int result = 0;
	        // 删除旧记录
	        UpssoUserRoleExample upmsUserRoleExample = new UpssoUserRoleExample();
	        upmsUserRoleExample.createCriteria()
	                .andUserIdEqualTo(id);
	        upssoUserRoleMapper.deleteByExample(upmsUserRoleExample);
	        // 增加新记录
	        if (null != roleIds) {
	            for (String roleId : roleIds) {
	                if (StrUtil.isBlank(roleId)) {
	                    continue;
	                }
	                UpssoUserRole upmsUserRole = new UpssoUserRole();
	                upmsUserRole.setUserId(id);
	                upmsUserRole.setRoleId(Convert.toInt(roleId));
	                result = upssoUserRoleMapper.insertSelective(upmsUserRole);
	            }
	        }
	        return result;
	}

}