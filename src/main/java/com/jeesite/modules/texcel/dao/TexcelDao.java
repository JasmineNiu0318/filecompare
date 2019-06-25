/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcel.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.texcel.entity.Texcel;

import java.util.List;

/**
 * t_excelDAO接口
 * @author njh
 * @version 2019-06-13
 */
@MyBatisDao
public interface TexcelDao extends CrudDao<Texcel> {

    /**
     * 通过ExcelID查询Excel内容
     * @param id
     * @return
     */
    List<String> findTexcelInfoByExcelId(String id);
}