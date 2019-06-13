/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcel.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * t_excelEntity
 * @author njh
 * @version 2019-06-13
 */
@Table(name="t_excel", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="system_id", attrName="systemId", label="系统流向ID", isInsert=false, isUpdate=false, isQuery=false),
		@Column(name="name", attrName="name", label="Excel名称", queryType=QueryType.LIKE),
		@Column(name="header", attrName="header", label="表头", isInsert=false, isUpdate=false, isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Texcel extends DataEntity<Texcel> {
	
	private static final long serialVersionUID = 1L;
	private String systemId;		// 系统流向ID
	private String name;		// Excel名称
	private String header;		// 表头
	
	public Texcel() {
		this(null);
	}

	public Texcel(String id){
		super(id);
	}
	
	@Length(min=0, max=11, message="系统流向ID长度不能超过 11 个字符")
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
	@Length(min=0, max=255, message="Excel名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1024, message="表头长度不能超过 1024 个字符")
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
}