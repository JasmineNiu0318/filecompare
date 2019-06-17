/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.fieldcompare.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.entity.Office;
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
import com.jeesite.modules.fieldcompare.entity.TfieldCompare;
import com.jeesite.modules.fieldcompare.service.TfieldCompareService;

import java.util.List;
import java.util.Map;

/**
 * t_field_compareController
 * @author njh
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/fieldcompare/tfieldCompare")
public class TfieldCompareController extends BaseController {

	@Autowired
	private TfieldCompareService tfieldCompareService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TfieldCompare get(String id, boolean isNewRecord) {
		return tfieldCompareService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("fieldcompare:tfieldCompare:view")
	@RequestMapping(value = {"list", ""})
	public String list(TfieldCompare tfieldCompare, Model model) {
		model.addAttribute("tfieldCompare", tfieldCompare);
		return "modules/fieldcompare/tfieldCompareList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("fieldcompare:tfieldCompare:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TfieldCompare> listData(TfieldCompare tfieldCompare, HttpServletRequest request, HttpServletResponse response) {
		tfieldCompare.setPage(new Page<>(request, response));
		Page<TfieldCompare> page = tfieldCompareService.findPage(tfieldCompare);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("fieldcompare:tfieldCompare:view")
	@RequestMapping(value = "form")
	public String form(TfieldCompare tfieldCompare, Model model) {
		model.addAttribute("tfieldCompare", tfieldCompare);
		return "modules/fieldcompare/tfieldCompareForm";
	}

	/**
	 * 保存t_field_compare
	 */
	@RequiresPermissions("fieldcompare:tfieldCompare:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TfieldCompare tfieldCompare) {
		tfieldCompareService.save(tfieldCompare);
		return renderResult(Global.TRUE, text("保存t_field_compare成功！"));
	}
	
	/**
	 * 删除t_field_compare
	 */
	@RequiresPermissions("fieldcompare:tfieldCompare:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TfieldCompare tfieldCompare) {
		tfieldCompareService.delete(tfieldCompare);
		return renderResult(Global.TRUE, text("删除t_field_compare成功！"));
	}

	@RequestMapping({"treeData"})
	@ResponseBody
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<TfieldCompare> list = this.tfieldCompareService.findList(new TfieldCompare());
		for(int i=0;i<list.size();i++) {
			TfieldCompare e=list.get(i);
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			String name = e.getSystemId().getName();
			map.put("name",name);
			mapList.add(map);
		}
		return mapList;
	}

	
}