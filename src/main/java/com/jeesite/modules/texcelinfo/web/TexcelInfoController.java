/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcelinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.texcelinfo.entity.TexcelInfo;
import com.jeesite.modules.texcelinfo.service.TexcelInfoService;

/**
 * t_excel_infoController
 * @author njh
 * @version 2019-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/texcelinfo/texcelInfo")
public class TexcelInfoController extends BaseController {

	@Autowired
	private TexcelInfoService texcelInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TexcelInfo get(String id, boolean isNewRecord) {
		return texcelInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("texcelinfo:texcelInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TexcelInfo texcelInfo, Model model) {
		model.addAttribute("texcelInfo", texcelInfo);
		return "modules/texcelinfo/texcelInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("texcelinfo:texcelInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TexcelInfo> listData(TexcelInfo texcelInfo, HttpServletRequest request, HttpServletResponse response) {
		texcelInfo.setPage(new Page<>(request, response));
		Page<TexcelInfo> page = texcelInfoService.findPage(texcelInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("texcelinfo:texcelInfo:view")
	@RequestMapping(value = "form")
	public String form(TexcelInfo texcelInfo, Model model) {
		model.addAttribute("texcelInfo", texcelInfo);
		return "modules/texcelinfo/texcelInfoForm";
	}

	/**
	 * 保存t_excel_info
	 */
	@RequiresPermissions("texcelinfo:texcelInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TexcelInfo texcelInfo) {
		texcelInfoService.save(texcelInfo);
		return renderResult(Global.TRUE, text("保存t_excel_info成功！"));
	}
	
	/**
	 * 删除t_excel_info
	 */
	@RequiresPermissions("texcelinfo:texcelInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TexcelInfo texcelInfo) {
		texcelInfoService.delete(texcelInfo);
		return renderResult(Global.TRUE, text("删除t_excel_info成功！"));
	}
	
}