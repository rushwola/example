package com.ruali.upsso.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruali.upsso.dao.mapper.UpssoUserPermissionMapper;
import com.ruali.upsso.dao.model.UpssoUserPermission;
import com.ruali.upsso.dao.model.UpssoUserPermissionExample;
import com.ruali.upsso.facade.UpssoUserPermissionService;
import com.rulai.framework.core.api.service.BaseServiceImpl;

/**
* UpssoUserPermissionService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoUserPermissionServiceImpl extends BaseServiceImpl<UpssoUserPermissionMapper, UpssoUserPermission, UpssoUserPermissionExample> implements UpssoUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpssoUserPermissionServiceImpl.class);

    @Autowired
    UpssoUserPermissionMapper upssoUserPermissionMapper;

	@Override
	public int permission(JSONArray datas, int id) {
		 for (int i = 0; i < datas.size(); i ++) {
	            JSONObject json = datas.getJSONObject(i);
	            if (json.getBoolean("checked")) {
	                // 新增权限
	                UpssoUserPermission upmsUserPermission = new UpssoUserPermission();
	                upmsUserPermission.setUserId(id);
	                upmsUserPermission.setPermissionId(json.getIntValue("id"));
	                upmsUserPermission.setType(json.getByte("type"));
	                upssoUserPermissionMapper.insertSelective(upmsUserPermission);
	            } else {
	                // 删除权限
	                UpssoUserPermissionExample upmsUserPermissionExample = new UpssoUserPermissionExample();
	                upmsUserPermissionExample.createCriteria()
	                        .andPermissionIdEqualTo(json.getIntValue("id"))
	                        .andTypeEqualTo(json.getByte("type"));
	                upssoUserPermissionMapper.deleteByExample(upmsUserPermissionExample);
	            }
	        }
	        return datas.size();
	}

}