/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.fieldcompare.entity;

import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelFields;
import com.jeesite.modules.systemcompare.entity.TsystemCompare;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * t_field_compareEntity
 * @author njh
 * @version 2019-06-17
 */
@Table(name="t_field_compare", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="system_id", attrName="systemId.id", label="系统流向ID"),
		@Column(name="old_name", attrName="oldName", label="旧数据字段名称", queryType=QueryType.LIKE),
		@Column(name="new_name", attrName="newName", label="新数据字段名称", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
	},joinTable = {
		@JoinTable(type=Type.LEFT_JOIN, entity= TsystemCompare.class, attrName="systemId", alias="tsystemCompare",
				on="tsystemCompare.id = a.system_id", columns={
				@Column(name="name", label="系统流向名称"),
				@Column(name="id", label="系统流向ID",isPK = true)
		}),
}, orderBy="a.id DESC"
)
public class TfieldCompare extends DataEntity<TfieldCompare> {

	private Object refObj;

	public Object getRefObj() {
		return this.refObj;
	}

	public void setRefObj(Object refObj) {
		this.refObj = refObj;
	}

	@Valid
	@ExcelFields({@ExcelField(
			title = "旧数据字段名称",
			attrName = "oldName",
			align = ExcelField.Align.CENTER,
			sort = 10
	), @ExcelField(
			title = "新数据字段名称",
			attrName = "newName",
			align = ExcelField.Align.CENTER,
			sort = 20
	), @ExcelField(
			title = "描述",
			attrName = "remarks",
			align = ExcelField.Align.CENTER,
			sort = 40
	)})
	
	private static final long serialVersionUID = 1L;
	private TsystemCompare systemId;		// 系统流向ID
	/**
	 * 增加关联字段
	 */
	private String systemName;    //系统流向名称
	private String oldName;		// 旧数据字段名称
	private String newName;		// 新数据字段名称

	public TfieldCompare() {
		this(null);
	}

	public TfieldCompare(String id){
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
	
	/*@Length(min=0, max=64, message="系统流向ID长度不能超过 64 个字符")*/
	public TsystemCompare getSystemId() {
		return systemId;
	}

	public void setSystemId(TsystemCompare systemId) {
		this.systemId = systemId;
	}

	@Length(min=0, max=64, message="系统流向名称长度不能超过 64 个字符")
	public String getSystemName(){
		return systemName;
	}

	public void setSystemName(String systemName){
		this.systemName = systemName;
	}
	
	@NotBlank(message="旧数据字段名称不能为空")
	@Length(min=0, max=64, message="旧数据字段名称长度不能超过 64 个字符")
	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	
	@NotBlank(message="新数据字段名称不能为空")
	@Length(min=0, max=64, message="新数据字段名称长度不能超过 64 个字符")
	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}




}