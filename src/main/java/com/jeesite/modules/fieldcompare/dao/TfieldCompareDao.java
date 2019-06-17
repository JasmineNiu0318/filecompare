/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.fieldcompare.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.fieldcompare.entity.TfieldCompare;

/**
 * t_field_compareDAO接口
 * @author njh
 * @version 2019-06-17
 */
@MyBatisDao
public interface TfieldCompareDao extends CrudDao<TfieldCompare> {

    /**
     * 查询分页数据
     * @param tfieldCompare
     * @return
     */
    Page<TfieldCompare> findPage(TfieldCompare tfieldCompare);
}