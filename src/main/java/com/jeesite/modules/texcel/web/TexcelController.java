/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.texcel.entity.Header;
import org.apache.commons.collections.CollectionUtils;
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
import com.jeesite.modules.texcel.entity.Texcel;
import com.jeesite.modules.texcel.service.TexcelService;

import java.util.ArrayList;
import java.util.List;

/**
 * t_excelController
 * @author njh
 * @version 2019-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/texcel/texcel")
public class TexcelController extends BaseController {

	@Autowired
	private TexcelService texcelService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Texcel get(String id, boolean isNewRecord) {
		return texcelService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("texcel:texcel:view")
	@RequestMapping(value = {"list", ""})
	public String list(Texcel texcel, Model model) {
		model.addAttribute("texcel", texcel);
		return "modules/texcel/texcelList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("texcel:texcel:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Texcel> listData(Texcel texcel, HttpServletRequest request, HttpServletResponse response) {
		texcel.setPage(new Page<>(request, response));
		Page<Texcel> page = texcelService.findPage(texcel);
		List<Texcel> list = page.getList();
		List<Texcel> texcelList = new ArrayList<>();
		if(!list.isEmpty()){
			for(Texcel texcel1 : list){
				String header = texcel1.getHeader();
				List<Header> headers = JSONObject.parseArray(header, Header.class);
				texcel1.setHeaderList(headers);
				texcelList.add(texcel1);
			}
		}
		page.setList(texcelList);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("texcel:texcel:view")
	@RequestMapping(value = "form")
	public String form(Texcel texcel, Model model) {
		if(StringUtils.isNotEmpty(texcel.getHeader())){
			List<Header> headers = JSONObject.parseArray(texcel.getHeader(), Header.class);
			texcel.setHeaderList(headers);
		}
		model.addAttribute("texcel", texcel);
		return "modules/texcel/texcelForm";
	}

	/**
	 * 保存t_excel
	 */
	@RequiresPermissions("texcel:texcel:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Texcel texcel) {
		if(CollectionUtils.isNotEmpty(texcel.getHeaderList())){
			texcel.setHeader(JSONArray.toJSONString(texcel.getHeaderList()));
		}
		texcelService.save(texcel);
		return renderResult(Global.TRUE, text("保存t_excel成功！"));
	}
	
	/**
	 * 删除t_excel
	 */
	@RequiresPermissions("texcel:texcel:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Texcel texcel) {
		texcelService.delete(texcel);
		return renderResult(Global.TRUE, text("删除t_excel成功！"));
	}

    /**
     * 返回表头信息
     * @return
     */
	public String selectHeader(){
        List<String> list = new ArrayList<>();
	    return renderResult(Global.TRUE, "success", list);
    }

    /**
     * 查看原始数据
     */
    @RequiresPermissions("texcel:texcel:view")
    @RequestMapping(value = "allinfoform")
    public String updateInfoForm(Texcel texcel, Model model) {
        model.addAttribute("texcel", texcel);
        return "modules/texcel/texcelallinfoForm";
    }
}