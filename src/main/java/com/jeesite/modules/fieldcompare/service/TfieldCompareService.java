/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.fieldcompare.service;

import java.util.Iterator;
import java.util.List;

import com.jeesite.common.service.ServiceException;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.validator.ValidatorUtils;
import com.jeesite.modules.systemcompare.entity.TsystemCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.fieldcompare.entity.TfieldCompare;
import com.jeesite.modules.fieldcompare.dao.TfieldCompareDao;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;

/**
 * t_field_compareService
 * @author njh
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly=false)
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

	/**
	 * 删除数据
	 * @param oldName
	 */
	@Transactional(readOnly=false)
	public TfieldCompare findByOldName(String oldName) {
		return tfieldCompareDao.findByOldName(oldName);
	}

	/**
	 * 通过旧数据名称更新数据
	 * @param tfieldCompare
	 */
	@Transactional(readOnly=false)
	public void updateByOldName(TfieldCompare tfieldCompare) {
		tfieldCompareDao.updateByOldName(tfieldCompare);
	}

	/**
	 * 导入文件
	 * @param file
	 * @param isUpdateSupport
	 * @return
	 */
	public String importData(MultipartFile file, Boolean isUpdateSupport, String systemId) {
		if (file == null) {
			throw new ServiceException("请选择导入的数据文件！");
		} else {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder successMsg = new StringBuilder();
			StringBuilder failureMsg = new StringBuilder();

			try {
				ExcelImport ei = new ExcelImport(file, 2, 0);
				Throwable var8 = null;

				try {
					List<TfieldCompare> list = ei.getDataList(TfieldCompare.class, new String[0]);
					Iterator iterator = list.iterator();
					while(iterator.hasNext()) {
						TfieldCompare tfieldCompare = (TfieldCompare)iterator.next();
						tfieldCompare.setSystemId(new TsystemCompare(systemId));

						try {
							ValidatorUtils.validateWithException(tfieldCompare, new Class[0]);
							TfieldCompare tfieldCompare1 = this.findByOldName(tfieldCompare.getOldName());
							if (tfieldCompare1 == null) {
								this.save(tfieldCompare);
								++successNum;
								successMsg.append("<br/>" + successNum + "、记录 " + tfieldCompare.getOldName() + " 导入成功");
							} else if (isUpdateSupport) {
								tfieldCompare.setId(tfieldCompare1.getId());
								super.save(tfieldCompare);
								++successNum;
								successMsg.append("<br/>" + successNum + "、记录 " + tfieldCompare.getOldName() + " 更新成功");
							} else {
								++failureNum;
								failureMsg.append("<br/>" + failureNum + "、记录 " + tfieldCompare.getOldName() + " 已存在");
							}
						} catch (Exception var26) {
							++failureNum;
							String msg = "<br/>" + failureNum + "、记录 " + tfieldCompare.getOldName() + " 导入失败：";
							if (var26 instanceof ConstraintViolationException) {
								List<String> messageList = ValidatorUtils.extractPropertyAndMessageAsList((ConstraintViolationException)var26, ": ");

								String message;
								for(Iterator var15 = messageList.iterator(); var15.hasNext(); msg = msg + message + "; ") {
									message = (String)var15.next();
								}
							} else {
								msg = msg + var26.getMessage();
							}

							failureMsg.append(msg);
							this.logger.error(msg, var26);
						}
					}
				} catch (Throwable var27) {
					var8 = var27;
					throw var27;
				} finally {
					if (ei != null) {
						if (var8 != null) {
							try {
								ei.close();
							} catch (Throwable var25) {
								var8.addSuppressed(var25);
							}
						} else {
							ei.close();
						}
					}

				}
			} catch (Exception var29) {
				failureMsg.append(var29.getMessage());
				this.logger.error(var29.getMessage(), var29);
			}

			if (failureNum > 0) {
				failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
				throw new ServiceException(failureMsg.toString());
			} else {
				successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
				return successMsg.toString();
			}
		}
	}
}