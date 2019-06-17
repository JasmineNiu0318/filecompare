/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.fieldcompare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.fieldcompare.entity.TfieldCompare;
import com.jeesite.modules.fieldcompare.dao.TfieldCompareDao;

/**
 * t_field_compareService
 * @author njh
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly=true)
public class TfieldCompareService extends CrudService<TfieldCompareDao, TfieldCompare> {

	@Autowired
	private TfieldCompareDao tfieldCompareDao;

	/**
	 * 获取单条数据
	 * @param tfieldCompare
	 * @return
	 */
	@Override
	public TfieldCompare get(TfieldCompare tfieldCompare) {
		return super.get(tfieldCompare);
	}
	
	/**
	 * 查询分页数据
	 * @param tfieldCompare 查询条件
	 * @param tfieldCompare.page 分页对象
	 * @return
	 */
	@Override
	public Page<TfieldCompare> findPage(TfieldCompare tfieldCompare) {
		return super.findPage(tfieldCompare);
		/*Page<TfieldCompare> page = new Page<>();
		return tfieldCompareDao.findPage(tfieldCompare);*/
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param tfieldCompare
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TfieldCompare tfieldCompare) {
		super.save(tfieldCompare);
	}
	
	/**
	 * 更新状态
	 * @param tfieldCompare
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TfieldCompare tfieldCompare) {
		super.updateStatus(tfieldCompare);
	}
	
	/**
	 * 删除数据
	 * @param tfieldCompare
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TfieldCompare tfieldCompare) {
		super.delete(tfieldCompare);
	}
	
}