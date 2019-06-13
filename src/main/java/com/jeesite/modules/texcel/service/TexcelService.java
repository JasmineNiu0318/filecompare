/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.texcel.entity.Texcel;
import com.jeesite.modules.texcel.dao.TexcelDao;

/**
 * t_excelService
 * @author njh
 * @version 2019-06-13
 */
@Service
@Transactional(readOnly=true)
public class TexcelService extends CrudService<TexcelDao, Texcel> {
	
	/**
	 * 获取单条数据
	 * @param texcel
	 * @return
	 */
	@Override
	public Texcel get(Texcel texcel) {
		return super.get(texcel);
	}
	
	/**
	 * 查询分页数据
	 * @param texcel 查询条件
	 * @param texcel.page 分页对象
	 * @return
	 */
	@Override
	public Page<Texcel> findPage(Texcel texcel) {
		return super.findPage(texcel);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param texcel
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Texcel texcel) {
		super.save(texcel);
	}
	
	/**
	 * 更新状态
	 * @param texcel
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Texcel texcel) {
		super.updateStatus(texcel);
	}
	
	/**
	 * 删除数据
	 * @param texcel
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Texcel texcel) {
		super.delete(texcel);
	}
	
}