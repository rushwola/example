package com.rulai.upsso.server.controller.manage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ruali.upsso.dao.model.UpssoLog;
import com.ruali.upsso.dao.model.UpssoLogExample;
import com.ruali.upsso.facade.UpssoLogService;
import com.rulai.framework.core.controller.BaseController;
import com.rulai.tool.core.util.StrUtil;
import com.rulai.upsso.common.constant.UpmsResult;
import com.rulai.upsso.common.constant.UpmsResultConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志controller
 * Created by shuzheng on 2017/3/14.
 */
@Controller
@Api(value = "日志管理", description = "日志管理")
@RequestMapping("/manage/log")
public class UpmsLogController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(UpmsLogController.class);

    @Autowired
    private UpssoLogService upmsLogService;

    @ApiOperation(value = "日志首页")
    @RequiresPermissions("upms:log:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/log/index.jsp";
    }

    @ApiOperation(value = "日志列表")
    @RequiresPermissions("upms:log:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpssoLogExample upmsLogExample = new UpssoLogExample();
        if (!StrUtil.isBlank(sort) && !StrUtil.isBlank(order)) {
            upmsLogExample.setOrderByClause(sort + " " + order);
        }
        if (StrUtil.isNotBlank(search)) {
            upmsLogExample.or()
                    .andDescriptionLike("%" + search + "%");
        }
        List<UpssoLog> rows = upmsLogService.selectByExampleForOffsetPage(upmsLogExample, offset, limit);
        long total = upmsLogService.countByExample(upmsLogExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "删除日志")
    @RequiresPermissions("upms:log:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsLogService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}