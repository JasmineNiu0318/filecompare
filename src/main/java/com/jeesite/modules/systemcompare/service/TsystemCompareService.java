/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.systemcompare.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.systemcompare.entity.TsystemCompare;
import com.jeesite.modules.systemcompare.dao.TsystemCompareDao;

/**
 * t_system_compareService
 * @author njh
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly=true)
public class TsystemCompareService extends CrudService<TsystemCompareDao, TsystemCompare> {

	@Autowired
	private TsystemCompareDao tsystemCompareDao;
	
	/**
	 * 获取单条数据
	 * @param tsystemCompare
	 * @return
	 */
	@Override
	public TsystemCompare get(TsystemCompare tsystemCompare) {
		return super.get(tsystemCompare);
	}
	
	/**
	 * 查询分页数据
	 * @param tsystemCompare 查询条件
	 * @param tsystemCompare.page 分页对象
	 * @return
	 */
	@Override
	public Page<TsystemCompare> findPage(TsystemCompare tsystemCompare) {
		return super.findPage(tsystemCompare);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param tsystemCompare
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TsystemCompare tsystemCompare) {
		super.save(tsystemCompare);
	}
	
	/**
	 * 更新状态
	 * @param tsystemCompare
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TsystemCompare tsystemCompare) {
		super.updateStatus(tsystemCompare);
	}
	
	/**
	 * 删除数据
	 * @param tsystemCompare
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TsystemCompare tsystemCompare) {
		super.delete(tsystemCompare);
	}
	
}