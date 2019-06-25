/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.fieldcompare.entity.TfieldCompare;
import com.jeesite.modules.texcel.entity.Header;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		return page;
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("texcel:texcel:view")
	@RequestMapping(value = "listExData")
	@ResponseBody
	public Page<Texcel> listExData(Texcel texcel, HttpServletRequest request, HttpServletResponse response) {
		Texcel texce = new Texcel();
		texce.setPage(new Page<>(request, response));
		Page<Texcel> page = texcelService.findPage(texce);

		//将当前的数据移除list
		if(CollectionUtils.isNotEmpty(page.getList())){
			List<Texcel> texcelList = page.getList();
			Texcel texcel1 = new Texcel();
			for(Texcel excel : texcelList){
				if(excel.getId().equals(texcel.getId())){
					BeanUtils.copyProperties(excel, texcel1);
				}
			}
			texcelList.remove(texcel1);
			page.setList(texcelList);
		}
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
     * 查看原始数据页面
     */
    @RequiresPermissions("texcel:texcel:view")
    @RequestMapping(value = "allinfoform")
    public String allinfoform(Texcel texcel, Model model) {
		//查询该Excel所有列
		Texcel t = this.texcelService.get(texcel.getId());
		List<Header> list = JSONArray.parseArray(t.getHeader(), Header.class);
		List<String> nameList = new ArrayList<>();
		for(Header header : list){
			nameList.add(JSON.toJSONString(header));
		}
		List<String> dataList = this.texcelService.findTexcelInfoByExcelId(t.getId());
        model.addAttribute("texcel", texcel);
		model.addAttribute("nameList", t.getHeader());
		model.addAttribute("dataList", dataList);
        return "modules/texcel/texcelallinfoForm";
    }

	/**
	 * 进入数据对比页面
	 */
	@RequiresPermissions("texcel:texcel:edit")
	@RequestMapping(value = "compareList")
	public String compareList(Texcel texcel, Model model) {
		model.addAttribute("texcel", texcel);
		return "modules/texcel/texcelCompareList";
	}

	/**
	 * 对比数据
	 */
	@RequiresPermissions("texcel:texcel:edit")
	@RequestMapping(value = "exportData")
	public String exportData(Texcel texcel, Model model) {
		if(texcel != null){
			String id = texcel.getId();
		}
		//texcelService.delete(texcel);
		return "modules/texcel/roleList";
	}

	@RequestMapping({"treeData"})
	@ResponseBody
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<Texcel> list = this.texcelService.findList(new Texcel());
		for(int i=0;i<list.size();i++) {
			Texcel e=list.get(i);
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			String name = e.getRelationId().getName();
			map.put("name",name);
			mapList.add(map);
		}
		return mapList;
	}
}