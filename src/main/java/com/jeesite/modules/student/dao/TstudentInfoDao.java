/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.student.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.student.entity.TstudentInfo;

/**
 * t_studentDAO接口
 * @author njh
 * @version 2019-06-14
 */
@MyBatisDao
public interface TstudentInfoDao extends CrudDao<TstudentInfo> {
	
}