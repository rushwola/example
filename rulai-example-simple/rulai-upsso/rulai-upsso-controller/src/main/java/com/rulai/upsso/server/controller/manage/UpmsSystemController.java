package com.rulai.upsso.server.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.ruali.upsso.dao.model.UpssoSystem;
import com.ruali.upsso.dao.model.UpssoSystemExample;
import com.ruali.upsso.facade.UpssoSystemService;
import com.rulai.framework.core.controller.BaseController;
import com.rulai.framework.core.validator.LengthValidator;
import com.rulai.tool.core.util.StrUtil;
import com.rulai.upsso.common.constant.UpmsResult;
import com.rulai.upsso.common.constant.UpmsResultConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统controller
 * Created by shuzheng on 2016/12/18.
 */
@Controller
@Api(value = "系统管理", description = "系统管理")
@RequestMapping("/manage/system")
public class UpmsSystemController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(UpmsSystemController.class);

	@Autowired
	private UpssoSystemService upmsSystemService;

	@ApiOperation(value = "系统首页")
	@RequiresPermissions("upms:system:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/system/index.jsp";
	}

	@ApiOperation(value = "系统列表")
	@RequiresPermissions("upms:system:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, defaultValue = "", value = "search") String search,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		UpssoSystemExample upmsSystemExample = new UpssoSystemExample();
		if (!StrUtil.isBlank(sort) && !StrUtil.isBlank(order)) {
			upmsSystemExample.setOrderByClause(sort + " " + order);
		}
		if (StrUtil.isNotBlank(search)) {
			upmsSystemExample.or()
					.andTitleLike("%" + search + "%");
		}
		List<UpssoSystem> rows = upmsSystemService.selectByExampleForOffsetPage(upmsSystemExample, offset, limit);
		long total = upmsSystemService.countByExample(upmsSystemExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增系统")
	@RequiresPermissions("upms:system:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/system/create.jsp";
	}

	@ApiOperation(value = "新增系统")
	@RequiresPermissions("upms:system:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(UpssoSystem upmsSystem) {
		ComplexResult result = FluentValidator.checkAll()
				.on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		upmsSystem.setCtime(time);
		upmsSystem.setOrders(time);
		int count = upmsSystemService.insertSelective(upmsSystem);
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除系统")
	@RequiresPermissions("upms:system:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = upmsSystemService.deleteByPrimaryKeys(ids);
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改系统")
	@RequiresPermissions("upms:system:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		UpssoSystem system = upmsSystemService.selectByPrimaryKey(id);
		modelMap.put("system", system);
		return "/manage/system/update.jsp";
	}

	@ApiOperation(value = "修改系统")
	@RequiresPermissions("upms:system:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, UpssoSystem upmsSystem) {
		ComplexResult result = FluentValidator.checkAll()
				.on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		upmsSystem.setSystemId(id);
		int count = upmsSystemService.updateByPrimaryKeySelective(upmsSystem);
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

}