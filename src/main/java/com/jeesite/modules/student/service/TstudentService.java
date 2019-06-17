/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.student.entity.Tstudent;
import com.jeesite.modules.student.dao.TstudentDao;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.student.entity.TstudentInfo;
import com.jeesite.modules.student.dao.TstudentInfoDao;

/**
 * t_studentService
 * @author njh
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly=true)
public class TstudentService extends CrudService<TstudentDao, Tstudent> {
	
	@Autowired
	private TstudentInfoDao tstudentInfoDao;
	
	/**
	 * 获取单条数据
	 * @param tstudent
	 * @return
	 */
	@Override
	public Tstudent get(Tstudent tstudent) {
		Tstudent entity = super.get(tstudent);
		if (entity != null){
			TstudentInfo tstudentInfo = new TstudentInfo(entity);
			tstudentInfo.setStatus(TstudentInfo.STATUS_NORMAL);
			entity.setTstudentInfoList(tstudentInfoDao.findList(tstudentInfo));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param tstudent 查询条件
	 * @param tstudent.page 分页对象
	 * @return
	 */
	@Override
	public Page<Tstudent> findPage(Tstudent tstudent) {
		return super.findPage(tstudent);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param tstudent
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Tstudent tstudent) {
		super.save(tstudent);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(tstudent.getId(), "tstudent_file");
		// 保存 Tstudent子表
		for (TstudentInfo tstudentInfo : tstudent.getTstudentInfoList()){
			if (!TstudentInfo.STATUS_DELETE.equals(tstudentInfo.getStatus())){
				tstudentInfo.setStudentId(tstudent);
				if (tstudentInfo.getIsNewRecord()){
					tstudentInfo.preInsert();
					tstudentInfoDao.insert(tstudentInfo);
				}else{
					tstudentInfo.preUpdate();
					tstudentInfoDao.update(tstudentInfo);
				}
			}else{
				tstudentInfoDao.delete(tstudentInfo);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param tstudent
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Tstudent tstudent) {
		super.updateStatus(tstudent);
	}
	
	/**
	 * 删除数据
	 * @param tstudent
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Tstudent tstudent) {
		super.delete(tstudent);
		TstudentInfo tstudentInfo = new TstudentInfo();
		tstudentInfo.setStudentId(tstudent);
		tstudentInfoDao.delete(tstudentInfo);
	}
	
}