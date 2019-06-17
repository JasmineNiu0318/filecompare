/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcelinfo.entity;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * t_excel_infoEntity
 * @author njh
 * @version 2019-06-14
 */
@Table(name="t_excel_info", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="excel_id", attrName="excelId", label="关联Excel表ID"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class TexcelInfo extends DataEntity<TexcelInfo> {
	
	private static final long serialVersionUID = 1L;
	private String excelId;		// 关联Excel表ID
	
	public TexcelInfo() {
		this(null);
	}

	public TexcelInfo(String id){
		super(id);
	}
	
	public String getExcelId() {
		return excelId;
	}

	public void setExcelId(String excelId) {
		this.excelId = excelId;
	}
	
}