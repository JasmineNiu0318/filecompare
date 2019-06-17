/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.systemcompare.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * t_system_compareEntity
 * @author njh
 * @version 2019-06-17
 */
@Table(name="t_system_compare", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="系统流向名称", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class TsystemCompare extends DataEntity<TsystemCompare> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 系统流向名称
	
	public TsystemCompare() {
		this(null);
	}

	public TsystemCompare(String id){
		super(id);
	}
	
	@NotBlank(message="系统流向名称不能为空")
	@Length(min=0, max=64, message="系统流向名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}