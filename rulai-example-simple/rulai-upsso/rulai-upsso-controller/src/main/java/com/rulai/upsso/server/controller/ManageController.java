package com.rulai.upsso.server.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruali.upsso.facade.UpmsApiService;
import com.ruali.upsso.facade.UpssoSystemService;
import com.ruali.upsso.dao.model.UpssoPermission;
import com.ruali.upsso.dao.model.UpssoSystem;
import com.ruali.upsso.dao.model.UpssoSystemExample;
import com.ruali.upsso.dao.model.UpssoUser;
import com.rulai.framework.core.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台controller
 * Created by ZhangShuzheng on 2017/01/19.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private UpssoSystemService upmsSystemService;

	@Autowired
	private UpmsApiService upmsApiService;

	@ApiOperation(value = "后台首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		// 已注册系统
		UpssoSystemExample upmsSystemExample = new UpssoSystemExample();
		upmsSystemExample.createCriteria()
				.andStatusEqualTo((byte) 1);
		List<UpssoSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
		modelMap.put("upmsSystems", upmsSystems);
		// 当前登录用户权限
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		UpssoUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
		List<UpssoPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
		modelMap.put("upmsPermissions", upmsPermissions);
		return "/manage/index.jsp";
	}

}