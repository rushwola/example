package com.ruali.upsso.service.mapper;


import java.util.List;

import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoRole;

/**
 * @ClassName UpmsApiMapper
 * @Description 用户VOMapper
 * @author ly
 * @Date 2017年7月15日 上午9:39:31
 * @version 1.0.0
 */
public interface UpmsApiMapper {

	// 根据用户id获取所拥有的权限
	List<UpssoPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

	// 根据用户id获取所属的角色
	List<UpssoRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);
	
}