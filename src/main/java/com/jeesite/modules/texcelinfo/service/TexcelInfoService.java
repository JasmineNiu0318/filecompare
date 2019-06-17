/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcelinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.texcelinfo.entity.TexcelInfo;
import com.jeesite.modules.texcelinfo.dao.TexcelInfoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * t_excel_infoService
 * @author njh
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly=true)
public class TexcelInfoService extends CrudService<TexcelInfoDao, TexcelInfo> {
	
	/**
	 * 获取单条数据
	 * @param texcelInfo
	 * @return
	 */
	@Override
	public TexcelInfo get(TexcelInfo texcelInfo) {
		return super.get(texcelInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param texcelInfo 查询条件
	 * @param texcelInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<TexcelInfo> findPage(TexcelInfo texcelInfo) {
		return super.findPage(texcelInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param texcelInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TexcelInfo texcelInfo) {
		super.save(texcelInfo);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(texcelInfo.getId(), "texcelInfo_file");
	}
	
	/**
	 * 更新状态
	 * @param texcelInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TexcelInfo texcelInfo) {
		super.updateStatus(texcelInfo);
	}
	
	/**
	 * 删除数据
	 * @param texcelInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TexcelInfo texcelInfo) {
		super.delete(texcelInfo);
	}
	
}