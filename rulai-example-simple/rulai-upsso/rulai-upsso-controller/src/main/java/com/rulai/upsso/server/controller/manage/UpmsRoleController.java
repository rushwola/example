package com.rulai.upsso.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.ruali.upsso.dao.model.UpssoRole;
import com.ruali.upsso.dao.model.UpssoRoleExample;
import com.ruali.upsso.facade.UpssoRolePermissionService;
import com.ruali.upsso.facade.UpssoRoleService;
import com.rulai.framework.core.controller.BaseController;
import com.rulai.framework.core.validator.LengthValidator;
import com.rulai.tool.core.util.StrUtil;
import com.rulai.upsso.common.constant.UpmsResult;
import com.rulai.upsso.common.constant.UpmsResultConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "角色管理", description = "角色管理")
@RequestMapping("/manage/role")
public class UpmsRoleController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(UpmsRoleController.class);

    @Autowired
    private UpssoRoleService upmsRoleService;

    @Autowired
    private UpssoRolePermissionService upmsRolePermissionService;

    @ApiOperation(value = "角色首页")
    @RequiresPermissions("upms:role:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/role/index.jsp";
    }

    @ApiOperation(value = "角色权限")
    @RequiresPermissions("upms:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        UpssoRole role = upmsRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/permission.jsp";
    }

    @ApiOperation(value = "角色权限")
    @RequiresPermissions("upms:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsRolePermissionService.rolePermission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "角色列表")
    @RequiresPermissions("upms:role:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpssoRoleExample upmsRoleExample = new UpssoRoleExample();
        if (!StrUtil.isBlank(sort) && !StrUtil.isBlank(order)) {
            upmsRoleExample.setOrderByClause(sort + " " + order);
        }
        if (StrUtil.isNotBlank(search)) {
            upmsRoleExample.or()
                    .andTitleLike("%" + search + "%");
        }
        List<UpssoRole> rows = upmsRoleService.selectByExampleForOffsetPage(upmsRoleExample, offset, limit);
        long total = upmsRoleService.countByExample(upmsRoleExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增角色")
    @RequiresPermissions("upms:role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/role/create.jsp";
    }

    @ApiOperation(value = "新增角色")
    @RequiresPermissions("upms:role:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpssoRole upmsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsRole.setCtime(time);
        upmsRole.setOrders(time);
        int count = upmsRoleService.insertSelective(upmsRole);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除角色")
    @RequiresPermissions("upms:role:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsRoleService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改角色")
    @RequiresPermissions("upms:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpssoRole role = upmsRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/update.jsp";
    }

    @ApiOperation(value = "修改角色")
    @RequiresPermissions("upms:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpssoRole upmsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsRole.setRoleId(id);
        int count = upmsRoleService.updateByPrimaryKeySelective(upmsRole);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
