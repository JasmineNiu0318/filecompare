/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.student.web;

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
import com.jeesite.modules.student.entity.Tstudent;
import com.jeesite.modules.student.service.TstudentService;

/**
 * t_studentController
 * @author njh
 * @version 2019-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/student/tstudent")
public class TstudentController extends BaseController {

	@Autowired
	private TstudentService tstudentService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Tstudent get(String id, boolean isNewRecord) {
		return tstudentService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("student:tstudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(Tstudent tstudent, Model model) {
		model.addAttribute("tstudent", tstudent);
		return "modules/student/tstudentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("student:tstudent:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Tstudent> listData(Tstudent tstudent, HttpServletRequest request, HttpServletResponse response) {
		tstudent.setPage(new Page<>(request, response));
		Page<Tstudent> page = tstudentService.findPage(tstudent);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("student:tstudent:view")
	@RequestMapping(value = "form")
	public String form(Tstudent tstudent, Model model) {
		model.addAttribute("tstudent", tstudent);
		return "modules/student/tstudentForm";
	}

	/**
	 * 保存t_student
	 */
	@RequiresPermissions("student:tstudent:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Tstudent tstudent) {
		tstudentService.save(tstudent);
		return renderResult(Global.TRUE, text("保存t_student成功！"));
	}
	
	/**
	 * 删除t_student
	 */
	@RequiresPermissions("student:tstudent:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Tstudent tstudent) {
		tstudentService.delete(tstudent);
		return renderResult(Global.TRUE, text("删除t_student成功！"));
	}
	
}