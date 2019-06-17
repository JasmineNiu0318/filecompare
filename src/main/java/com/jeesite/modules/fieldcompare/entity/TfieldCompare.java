/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.fieldcompare.entity;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.systemcompare.entity.TsystemCompare;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

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
		@Column(name="status", attrName="status", label="状态", comment="状态（0正常 1删除 2停用）", isUpdate=false),
		@Column(name="create_by", attrName="createBy", label="创建者", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新者", isQuery=false),
		@Column(name="update_time", attrName="updateTime", label="更新时间"),
		@Column(name="remarks", attrName="remarks", label="备注信息", queryType=QueryType.LIKE),
	},joinTable = {
		@JoinTable(type=Type.LEFT_JOIN, entity= TsystemCompare.class, attrName="systemId", alias="u10",
				on="u10.id = a.system_id", columns={
				@Column(name="name", label="系统流向名称"),
		}),
}, orderBy="a.id DESC"
)
public class TfieldCompare extends DataEntity<TfieldCompare> {
	
	private static final long serialVersionUID = 1L;
	private TsystemCompare systemId;		// 系统流向ID
	/**
	 * 增加关联字段
	 */
	private String systemName;    //系统流向名称
	private String oldName;		// 旧数据字段名称
	private String newName;		// 新数据字段名称
	private Date updateTime;		// 更新时间
	
	public TfieldCompare() {
		this(null);
	}

	public TfieldCompare(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="系统流向ID长度不能超过 64 个字符")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="更新时间不能为空")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}