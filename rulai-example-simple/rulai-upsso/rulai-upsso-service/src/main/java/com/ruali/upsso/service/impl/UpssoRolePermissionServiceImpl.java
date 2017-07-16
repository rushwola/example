package com.ruali.upsso.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruali.upsso.dao.mapper.UpssoRolePermissionMapper;
import com.ruali.upsso.dao.model.UpssoRolePermission;
import com.ruali.upsso.dao.model.UpssoRolePermissionExample;
import com.ruali.upsso.facade.UpssoRolePermissionService;
import com.rulai.framework.core.api.service.BaseServiceImpl;

/**
* UpssoRolePermissionService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoRolePermissionServiceImpl extends BaseServiceImpl<UpssoRolePermissionMapper, UpssoRolePermission, UpssoRolePermissionExample> implements UpssoRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpssoRolePermissionServiceImpl.class);

    @Autowired
    UpssoRolePermissionMapper upssoRolePermissionMapper;

	@Override
	public int rolePermission(JSONArray datas, int id) {
		 List<Integer> deleteIds = new ArrayList<>();
	        for (int i = 0; i < datas.size(); i ++) {
	            JSONObject json = datas.getJSONObject(i);
	            if (!json.getBoolean("checked")) {
	                deleteIds.add(json.getIntValue("id"));
	            } else {
	                // 新增权限
	                UpssoRolePermission upmsRolePermission = new UpssoRolePermission();
	                upmsRolePermission.setRoleId(id);
	                upmsRolePermission.setPermissionId(json.getIntValue("id"));
	                upssoRolePermissionMapper.insertSelective(upmsRolePermission);
	            }
	        }
	        // 删除权限
	        if (deleteIds.size() > 0) {
	        	UpssoRolePermissionExample upmsRolePermissionExample = new UpssoRolePermissionExample();
	            upmsRolePermissionExample.createCriteria()
	                    .andPermissionIdIn(deleteIds)
	                    .andRoleIdEqualTo(id);
	            upssoRolePermissionMapper.deleteByExample(upmsRolePermissionExample);
	        }
	        return datas.size();
	}

}