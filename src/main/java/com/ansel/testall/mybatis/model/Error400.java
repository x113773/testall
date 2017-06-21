package com.ansel.testall.mybatis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "400Error")
public class Error400 {
	/**
	 * 错误反馈编码
	 */
	@ApiModelProperty(value = "错误反馈编码")
	private String respCode;

	/**
	 * 错误反馈描述
	 */
	@ApiModelProperty(value = "错误反馈描述")
	private String respDesc;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

}
