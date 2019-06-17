/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.student.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * t_studentEntity
 * @author njh
 * @version 2019-06-14
 */
@Table(name="t_student", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="age", attrName="age", label="年龄"),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Tstudent extends DataEntity<Tstudent> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private Long age;		// 年龄
	private Integer sex;		// 性别
	private List<TstudentInfo> tstudentInfoList = ListUtils.newArrayList();		// 子表列表
	
	public Tstudent() {
		this(null);
	}

	public Tstudent(String id){
		super(id);
	}
	
	@NotBlank(message="姓名不能为空")
	@Length(min=0, max=64, message="姓名长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="年龄不能为空")
	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
	
	@NotNull(message="性别不能为空")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public List<TstudentInfo> getTstudentInfoList() {
		return tstudentInfoList;
	}

	public void setTstudentInfoList(List<TstudentInfo> tstudentInfoList) {
		this.tstudentInfoList = tstudentInfoList;
	}
	
}