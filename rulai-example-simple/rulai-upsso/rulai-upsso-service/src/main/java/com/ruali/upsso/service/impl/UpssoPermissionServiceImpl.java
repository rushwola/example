package com.ruali.upsso.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruali.upsso.dao.mapper.UpssoPermissionMapper;
import com.ruali.upsso.dao.mapper.UpssoSystemMapper;
import com.ruali.upsso.dao.mapper.UpssoUserPermissionMapper;
import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoPermissionExample;
import com.ruali.upsso.dao.model.UpssoRolePermission;
import com.ruali.upsso.dao.model.UpssoSystem;
import com.ruali.upsso.dao.model.UpssoSystemExample;
import com.ruali.upsso.dao.model.UpssoUserPermission;
import com.ruali.upsso.dao.model.UpssoUserPermissionExample;
import com.ruali.upsso.facade.UpmsApiService;
import com.ruali.upsso.facade.UpssoPermissionService;
import com.rulai.framework.core.service.BaseServiceImpl;

/**
* UpssoPermissionService实现
* Created by shuzheng on 2017/6/20.
*/
@Service
@Transactional
public class UpssoPermissionServiceImpl extends BaseServiceImpl<UpssoPermissionMapper, UpssoPermission, UpssoPermissionExample> implements UpssoPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpssoPermissionServiceImpl.class);

    @Autowired
    UpssoSystemMapper upmsSystemMapper;

    @Autowired
    UpssoPermissionMapper upmsPermissionMapper;

    @Autowired
    UpmsApiService upmsApiService;

    @Autowired
    UpssoUserPermissionMapper upmsUserPermissionMapper;

	@Override
	public JSONArray getTreeByRoleId(Integer roleId) {
		 // 角色已有权限
        List<UpssoRolePermission> rolePermissions = upmsApiService.selectUpmsRolePermisstionByUpmsRoleId(roleId);

        JSONArray systems = new JSONArray();
        // 系统
        UpssoSystemExample upmsSystemExample = new UpssoSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpssoSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        for (UpssoSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
            	UpssoPermissionExample upmsPermissionExample = new UpssoPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpssoPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);
                if (upmsPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (UpssoPermission upmsPermission: upmsPermissions) {
                    if (upmsPermission.getPid().intValue() != 0 || upmsPermission.getType() != 1) continue;
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getPermissionId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpssoRolePermission rolePermission : rolePermissions) {
                        if (rolePermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpssoPermission upmsPermission2: upmsPermissions) {
                            if (upmsPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || upmsPermission2.getType() != 2) continue;
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getPermissionId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpssoRolePermission rolePermission : rolePermissions) {
                                if (rolePermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpssoPermission upmsPermission3: upmsPermissions) {
                                    if (upmsPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || upmsPermission3.getType() != 3) continue;
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getPermissionId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpssoRolePermission rolePermission : rolePermissions) {
                                        if (rolePermission.getPermissionId().intValue() == upmsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
	}

	@Override
	public JSONArray getTreeByUserId(Integer usereId, Byte type) {
		 // 角色权限
		UpssoUserPermissionExample upmsUserPermissionExample = new UpssoUserPermissionExample();
        upmsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(usereId)
                .andTypeEqualTo(type);
        List<UpssoUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);

        JSONArray systems = new JSONArray();
        // 系统
        UpssoSystemExample upmsSystemExample = new UpssoSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpssoSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        for (UpssoSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                UpssoPermissionExample upmsPermissionExample = new UpssoPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpssoPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);
                if (upmsPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (UpssoPermission upmsPermission: upmsPermissions) {
                    if (upmsPermission.getPid().intValue() != 0 || upmsPermission.getType() != 1) continue;
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getPermissionId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpssoUserPermission upmsUserPermission : upmsUserPermissions) {
                        if (upmsUserPermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpssoPermission upmsPermission2: upmsPermissions) {
                            if (upmsPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || upmsPermission2.getType() != 2) continue;
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getPermissionId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpssoUserPermission upmsUserPermission : upmsUserPermissions) {
                                if (upmsUserPermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpssoPermission upmsPermission3: upmsPermissions) {
                                    if (upmsPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || upmsPermission3.getType() != 3) continue;
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getPermissionId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpssoUserPermission upmsUserPermission : upmsUserPermissions) {
                                        if (upmsUserPermission.getPermissionId().intValue() == upmsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
	}

}