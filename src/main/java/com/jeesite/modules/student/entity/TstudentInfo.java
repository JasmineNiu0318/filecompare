/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.student.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * t_studentEntity
 * @author njh
 * @version 2019-06-14
 */
@Table(name="t_student_info", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="student_id", attrName="studentId.id", label="关联student表"),
		@Column(name="math_score", attrName="mathScore", label="数学分数"),
		@Column(name="chinese_score", attrName="chineseScore", label="语文分数"),
		@Column(name="english_score", attrName="englishScore", label="英语分数"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class TstudentInfo extends DataEntity<TstudentInfo> {
	
	private static final long serialVersionUID = 1L;
	private Tstudent studentId;		// 关联student表 父类
	private Double mathScore;		// 数学分数
	private Double chineseScore;		// 语文分数
	private Double englishScore;		// 英语分数
	
	public TstudentInfo() {
		this(null);
	}


	public TstudentInfo(Tstudent studentId){
		this.studentId = studentId;
	}
	
	@NotBlank(message="关联student表不能为空")
	@Length(min=0, max=11, message="关联student表长度不能超过 11 个字符")
	public Tstudent getStudentId() {
		return studentId;
	}

	public void setStudentId(Tstudent studentId) {
		this.studentId = studentId;
	}
	
	@NotNull(message="数学分数不能为空")
	public Double getMathScore() {
		return mathScore;
	}

	public void setMathScore(Double mathScore) {
		this.mathScore = mathScore;
	}
	
	@NotNull(message="语文分数不能为空")
	public Double getChineseScore() {
		return chineseScore;
	}

	public void setChineseScore(Double chineseScore) {
		this.chineseScore = chineseScore;
	}
	
	@NotNull(message="英语分数不能为空")
	public Double getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(Double englishScore) {
		this.englishScore = englishScore;
	}
	
}