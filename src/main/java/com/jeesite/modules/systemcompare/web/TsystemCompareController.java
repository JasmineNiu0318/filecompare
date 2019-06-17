/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.systemcompare.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.fieldcompare.entity.TfieldCompare;
import com.jeesite.modules.sys.web.OfficeController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.systemcompare.entity.TsystemCompare;
import com.jeesite.modules.systemcompare.service.TsystemCompareService;

import java.util.List;
import java.util.Map;

/**
 * t_system_compareController
 * @author njh
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/systemcompare/tsystemCompare")
public class TsystemCompareController extends BaseController {

	@Autowired
	private TsystemCompareService tsystemCompareService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TsystemCompare get(String id, boolean isNewRecord) {
		return tsystemCompareService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("systemcompare:tsystemCompare:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsystemCompare tsystemCompare, Model model) {
		model.addAttribute("tsystemCompare", tsystemCompare);
		return "modules/systemcompare/tsystemCompareList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("systemcompare:tsystemCompare:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TsystemCompare> listData(TsystemCompare tsystemCompare, HttpServletRequest request, HttpServletResponse response) {
		tsystemCompare.setPage(new Page<>(request, response));
		Page<TsystemCompare> page = tsystemCompareService.findPage(tsystemCompare);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("systemcompare:tsystemCompare:view")
	@RequestMapping(value = "form")
	public String form(TsystemCompare tsystemCompare, Model model) {
		model.addAttribute("tsystemCompare", tsystemCompare);
		return "modules/systemcompare/tsystemCompareForm";
	}

	/**
	 * 保存t_system_compare
	 */
	@RequiresPermissions("systemcompare:tsystemCompare:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TsystemCompare tsystemCompare) {
		tsystemCompareService.save(tsystemCompare);
		return renderResult(Global.TRUE, text("保存t_system_compare成功！"));
	}
	
	/**
	 * 删除t_system_compare
	 */
	@RequiresPermissions("systemcompare:tsystemCompare:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TsystemCompare tsystemCompare) {
		tsystemCompareService.delete(tsystemCompare);
		return renderResult(Global.TRUE, text("删除t_system_compare成功！"));
	}

	@RequestMapping({"treeData"})
	@ResponseBody
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<TsystemCompare> list = this.tsystemCompareService.findList(new TsystemCompare());
		for(int i=0;i<list.size();i++) {
			TsystemCompare e=list.get(i);
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			String name = e.getName();
			map.put("name",name);
			mapList.add(map);
		}
		return mapList;
	}
	
}