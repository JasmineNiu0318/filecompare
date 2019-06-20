/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.texcel.entity;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.modules.systemcompare.entity.TsystemCompare;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

import java.util.List;

/**
 * t_excelEntity
 * @author njh
 * @version 2019-06-13
 */
@Table(name="t_excel", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="system_id", attrName="systemId.id", label="系统流向ID", isInsert=true, isUpdate=true, isQuery=false),
		@Column(name="name", attrName="name", label="Excel名称", queryType=QueryType.LIKE),
		@Column(name="header", attrName="header", label="表头", isInsert=true, isUpdate=true, isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, joinTable = {
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= TsystemCompare.class, attrName="systemId", alias="tsystemCompare",
				on="tsystemCompare.id = a.system_id",
				columns={
				@Column(name="name", label="系统流向名称"),
				@Column(name="id", label="系统流向ID",isPK = true)
				}),
}, orderBy="a.id DESC"
)
public class Texcel extends DataEntity<Texcel> {

	private Object refObj;

	public Object getRefObj() {
		return this.refObj;
	}

	public void setRefObj(Object refObj) {
		this.refObj = refObj;
	}
	
	private static final long serialVersionUID = 1L;
	private TsystemCompare systemId;		// 系统流向ID
	private String name;		// Excel名称
	private String header;		// 表头
	private List<Header> headerList = ListUtils.newArrayList();
	public Texcel() {
		this(null);
	}

	public Texcel(String id){
		super(id);
	}

	public TsystemCompare getTsystemCompare() {
		TsystemCompare tsystemCompare = (TsystemCompare)this.getRefObj();
		if (tsystemCompare == null) {
			tsystemCompare = new TsystemCompare();
			this.setRefObj(tsystemCompare);
		}

		return tsystemCompare;
	}

	public void setTsystemCompare(TsystemCompare tsystemCompare) {
		this.setRefObj(tsystemCompare);
	}
	
	/*@Length(min=0, max=11, message="系统流向ID长度不能超过 11 个字符")*/
	public TsystemCompare getSystemId() {
		return systemId;
	}

	public void setSystemId(TsystemCompare systemId) {
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

	public List<Header> getHeaderList() {
		return headerList;
	}

	public void setHeaderList(List<Header> headerList) {
		this.headerList = headerList;
	}
}